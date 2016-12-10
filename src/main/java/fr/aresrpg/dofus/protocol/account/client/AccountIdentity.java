package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountIdentity implements Packet {
	private String identity;

	@Override
	public void read(DofusStream stream) {
		identity = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
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
		return "AccountIdentity(" + "identity:'" + identity + "\')[" + getId() + "]";
	}
}
