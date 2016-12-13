package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

/**
 * 
 * @since
 */
public class ExchangeHdvPacket implements ClientPacket { // EH

	@Override
	public void read(DofusStream stream) {}

	@Override
	public void write(DofusStream stream) {}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

}
