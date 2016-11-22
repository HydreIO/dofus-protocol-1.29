package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.server.DofusServer;
import fr.aresrpg.dofus.structures.server.ServerState;

import java.util.Arrays;

public class AccountHostPacket implements Packet {
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

	}

	@Override
	public void handle(PacketHandler handler) {
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
