package fr.aresrpg.dofus.protocol.server.account;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountNamePacket implements Packet{
	private String name;

	@Override
	public void read(DofusStream stream) {
		name = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(name);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public String getName() {
		return name;
	}

	public AccountNamePacket setName(String name) {
		this.name = name;
		return this;
	}
}
