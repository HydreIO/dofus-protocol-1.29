package fr.aresrpg.dofus.protocol.basic.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

public class BasicConfirmPacket implements ServerPacket {
	@Override
	public void read(DofusStream stream)  {}

	@Override
	public void write(DofusStream stream) {}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "BasicConfirmPacket()[" + getId() + "]";
	}
}
