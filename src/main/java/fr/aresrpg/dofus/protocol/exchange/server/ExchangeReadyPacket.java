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

/**
 * 
 * @since
 */
public class ExchangeReadyPacket implements ServerPacket {

	private int extraData;

	/**
	 * @param extraData
	 */
	public ExchangeReadyPacket(int extraData) {
		this.extraData = extraData;
	}

	@Override
	public void read(DofusStream stream) {
		this.extraData = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(extraData);
	}

	/**
	 * @return the extraData
	 */
	public int getExtraData() {
		return extraData;
	}

	/**
	 * @param extraData
	 *            the extraData to set
	 */
	public void setExtraData(int extraData) {
		this.extraData = extraData;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeReadyPacket [extraData=" + extraData + "]";
	}

}
