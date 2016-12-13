package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.protocol.account.AccountKeyPacket;

public class AccountTicketOkPacket extends AccountKeyPacket implements ServerPacket{

	@Override
	public void handle(PacketHandler handler) {
		ServerPacket.super.handle(handler);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public AccountTicketOkPacket setData(String data) {
		super.data = data;
		return this;
	}

	@Override
	public AccountTicketOkPacket setKey(int key) {
		super.key = key;
		return this;
	}

	@Override
	public String toString() {
		return "AccountTicketOkPacket(key:" + super.key + "|data:'" + super.data + "\')[" + getId() + "]";
	}
}
