package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.*;

public class AccountListServersPacket implements Packet {

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
		return "AccountListServersPacket()[" + getId() + "]";
	}
}
