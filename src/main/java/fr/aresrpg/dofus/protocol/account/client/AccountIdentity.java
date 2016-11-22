package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class AccountIdentity implements Packet{
	private String identity;
	@Override
	public void read(DofusStream stream) throws IOException {
		identity = stream.read();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).write(identity);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public String getIdentity() {
		return identity;
	}

	public AccountIdentity setIdentity(String identity) {
		this.identity = identity;
		return this;
	}

	@Override
	public String toString() {
		return "AccountIdentity{" +
				"identity='" + identity + '\'' +
				'}';
	}
}
