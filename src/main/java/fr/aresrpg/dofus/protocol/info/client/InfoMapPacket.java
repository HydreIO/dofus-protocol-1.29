package fr.aresrpg.dofus.protocol.info.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class InfoMapPacket implements ClientPacket {
	@Override
	public void read(DofusStream stream) {}

	@Override
	public void write(DofusStream stream) {}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}
	@Override
	public String toString() {
		return "InfoMapPacket()[" + getId() + ']';
	}
}
