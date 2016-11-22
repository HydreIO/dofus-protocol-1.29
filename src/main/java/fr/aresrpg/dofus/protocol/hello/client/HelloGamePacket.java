package fr.aresrpg.dofus.protocol.hello.client;

import fr.aresrpg.dofus.protocol.*;

public class HelloGamePacket implements Packet {

	@Override
	public void read(DofusStream stream) {
	}

	@Override
	public void write(DofusStream stream) {
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "HelloGamePacket()[" + getId() + "]";
	}
}
