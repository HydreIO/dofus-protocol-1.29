/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.ExchangeMove;

/**
 * 
 * @since
 */
public class ExchangeMountStoragePacket implements ServerPacket {

	private ExchangeMove moveType;
	private int moundId;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		this.moveType = ExchangeMove.fromSymbol(data.charAt(0));
		if (moveType == null) return;
		boolean newborn = false;
		switch (moveType) {
			case UPDATE:
				newborn = true;
			case ADD:
				// Mount.parseMount(data.substring(1),newborn);
				break;
			case REMOVE:
				this.moundId = Integer.parseInt(data.substring(1));
				break;
		}
	}

	@Override
	public void write(DofusStream stream) {
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		// handler.handle(this);
	}

}
