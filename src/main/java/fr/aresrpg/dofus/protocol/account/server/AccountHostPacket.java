package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.structures.server.DofusServer;
import fr.aresrpg.dofus.structures.server.ServerState;

import java.util.Arrays;
import java.util.StringJoiner;

public class AccountHostPacket implements ServerPacket {
	private DofusServer[] servers;

	@Override
	public void read(DofusStream stream) {
		int available = stream.available();
		servers = new DofusServer[available];
		for (int i = 0; i < available; i++) {
			String[] data = stream.read().split(";");
			int id = Integer.parseInt(data[0]);
			int state = Integer.parseInt(data[1]);
			int population = Integer.parseInt(data[2]);
			boolean requireSubscription = Integer.parseInt(data[3]) == 1;
			servers[i] = new DofusServer(id, ServerState.valueOf(state), population, requireSubscription);
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(servers.length);
		for (DofusServer s : servers) {
			StringJoiner joiner = new StringJoiner(";");
			joiner.add(String.valueOf(s.getId()))
					.add(String.valueOf(s.getState().ordinal()))
					.add(String.valueOf(s.getServerPopulation()))
					.add(String.valueOf(s.needSubscription() ? 1 : 0));
			stream.write(joiner.toString());
		}
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public DofusServer[] getServers() {
		return servers;
	}

	@Override
	public String toString() {
		return "AccountHostPacket(" + "servers:" + Arrays.toString(servers) + ")[" + getId() + "]";
	}
}
