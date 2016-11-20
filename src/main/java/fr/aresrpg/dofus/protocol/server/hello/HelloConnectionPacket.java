package fr.aresrpg.dofus.protocol.server.hello;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class HelloConnectionPacket implements Packet{
	private String hashKey;

	@Override
	public void read(DofusStream stream) {
		hashKey = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(hashKey);
	}

	public String getHashKey() {
		return hashKey;
	}

	public HelloConnectionPacket setHashKey(String hashKey) {
		this.hashKey = hashKey;
		return this;
	}

	@Override
	public String toString() {
		return "HelloConnectionPacket{" +
				"hashKey='" + hashKey + '\'' +
				'}';
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}
}
