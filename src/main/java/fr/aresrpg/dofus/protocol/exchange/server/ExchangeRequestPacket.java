package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Exchange;

/**
 * 
 * @since
 */
public class ExchangeRequestPacket implements Packet {

	private int playerId;
	private int targetId;
	private Exchange exchange;

	/**
	 * @param playerId
	 * @param targetId
	 * @param exchange
	 */
	public ExchangeRequestPacket(int playerId, int targetId, Exchange exchange) {
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
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
