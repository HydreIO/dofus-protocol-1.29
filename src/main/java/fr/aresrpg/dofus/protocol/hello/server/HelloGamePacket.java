package fr.aresrpg.dofus.protocol.hello.server;

import fr.aresrpg.dofus.protocol.*;

public class HelloGamePacket implements ServerPacket {

	@Override
	public void read(DofusStream stream) {
	}

	@Override
	public void write(DofusStream stream) {
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "HelloGamePacket()[" + getId() + "]";
	}
}
