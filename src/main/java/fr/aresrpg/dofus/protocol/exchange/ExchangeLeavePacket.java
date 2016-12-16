package fr.aresrpg.dofus.protocol.exchange;

import fr.aresrpg.dofus.protocol.*;

public class ExchangeLeavePacket implements ClientPacket{
	@Override
	public void read(DofusStream stream) {}

	@Override
	public void write(DofusStream stream) {}


	@Override
	public String toString() {
		return "ExchangeLeavePacket()[" + getId() + ']';
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}
}
