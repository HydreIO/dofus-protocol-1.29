package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

public class AccountGetQueuePosition implements Packet {

	@Override
	public void read(DofusStream stream) throws IOException {
	}

	@Override
	public void write(DofusStream stream) throws IOException {
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "AccountGetQueuePosition()[" + getId() + "]";
	}
}
