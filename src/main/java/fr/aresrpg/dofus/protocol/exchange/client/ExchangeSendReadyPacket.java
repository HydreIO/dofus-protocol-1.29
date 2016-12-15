package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ExchangeSendReadyPacket implements ClientPacket {

	@Override
	public void read(DofusStream stream) {

	}

	@Override
	public void write(DofusStream stream) {

	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

}
