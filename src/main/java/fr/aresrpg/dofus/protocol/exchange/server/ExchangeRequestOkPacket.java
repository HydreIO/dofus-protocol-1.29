/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Exchange;

/**
 * 
 * @since
 */
public class ExchangeRequestOkPacket implements ServerPacket {

	private long playerId;
	private long targetId;
	private Exchange exchange;

	/**
	 * @param playerId
	 * @param targetId
	 * @param exchange
	 */
	public ExchangeRequestOkPacket(long playerId, long targetId, Exchange exchange) {
		this.playerId = playerId;
		this.targetId = targetId;
		this.exchange = exchange;
	}

	public ExchangeRequestOkPacket() {
	}

	@Override
	public void read(DofusStream stream) {
		this.playerId = stream.readLong();
		this.targetId = stream.readLong();
		this.exchange = Exchange.valueOf(stream.readInt());
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	/**
	 * @param targetId
	 *            the targetId to set
	 */
	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}

	/**
	 * @param exchange
	 *            the exchange to set
	 */
	public void setExchange(Exchange exchange) {
		this.exchange = exchange;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(3).writeLong(playerId).writeLong(targetId).writeInt(exchange.getCode());
	}

	/**
	 * @return the playerId
	 */
	public long getPlayerId() {
		return playerId;
	}

	/**
	 * @return the targetId
	 */
	public long getTargetId() {
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

	@Override
	public String toString() {
		return "ExchangeRequestOkPacket [playerId=" + playerId + ", targetId=" + targetId + ", exchange=" + exchange + "]";
	}

}
