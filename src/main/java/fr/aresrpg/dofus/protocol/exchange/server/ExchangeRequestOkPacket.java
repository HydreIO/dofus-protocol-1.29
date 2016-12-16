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
import fr.aresrpg.dofus.structures.Exchange;

/**
 * 
 * @since
 */
public class ExchangeRequestOkPacket implements ServerPacket {

	private int playerId;
	private int targetId;
	private Exchange exchange;

	/**
	 * @param playerId
	 * @param targetId
	 * @param exchange
	 */
	public ExchangeRequestOkPacket(int playerId, int targetId, Exchange exchange) {
		this.playerId = playerId;
		this.targetId = targetId;
		this.exchange = exchange;
	}

	@Override
	public void read(DofusStream stream) {
		this.playerId = stream.readInt();
		this.targetId = stream.readInt();
		this.exchange = Exchange.valueOf(stream.readInt());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(3).writeInt(playerId).writeInt(targetId).writeInt(exchange.getCode());
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @return the targetId
	 */
	public int getTargetId() {
		return targetId;
	}

	/**
	 * @return the exchange
	 */
	public Exchange getExchange() {
		return exchange;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

}
