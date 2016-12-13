package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Exchange;

/**
 * 
 * @since
 */
public class ExchangeAskPacket implements Packet {

	private int targetId;
	private Exchange exchange;

	/**
	 * @param targetId
	 * @param exchange
	 */
	public ExchangeAskPacket(int targetId, Exchange exchange) {
		this.targetId = targetId;
		this.exchange = exchange;
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
	public void read(DofusStream stream) {
		this.exchange = Exchange.valueOf(stream.readInt());
		this.targetId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(exchange.getCode()).writeInt(targetId);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}