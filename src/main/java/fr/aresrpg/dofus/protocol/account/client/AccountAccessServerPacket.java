package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class AccountAccessServerPacket implements Packet {
	private int serverId;

	@Override
	public void read(DofusStream stream) throws IOException {
		serverId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).writeInt(serverId);
	}

	public int getServerId() {
		return serverId;
	}

	public AccountAccessServerPacket setServerId(int serverId) {
		this.serverId = serverId;
		return this;
	}

	@Override
	public String toString() {
		return "AccountAccessServerPacket(serverid:" + serverId + ")[" + getId() + "]";
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}
}
