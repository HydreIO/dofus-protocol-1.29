package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Exchange;
import fr.aresrpg.dofus.util.Convert;

/**
 * 
 * @since
 */
public class ExchangeRequestPacket implements Packet {

	private int targetId;
	private Exchange exchange;
	private int cellid;

	public ExchangeRequestPacket(Exchange exchange, int targetId, int cellnum) {
		this.targetId = targetId;
		this.exchange = exchange;
		this.cellid = cellnum;
	}

	public ExchangeRequestPacket(Exchange type, int targetId) {
		this(type, targetId, -1);
	}

	public ExchangeRequestPacket(int cellid, Exchange type) {
		this(type, -1, cellid);
	}

	/**
	 * @return the cellid
	 */
	public int getCellid() {
		return cellid;
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

	public boolean hasTarget() {
		return getTargetId() != -1;
	}

	public boolean hasCellId() {
		return getCellid() != -1;
	}

	@Override
	public void read(DofusStream stream) {
		this.exchange = Exchange.valueOf(stream.readInt());
		this.targetId = Convert.toInt(stream.read());
		if (stream.available() > 0) this.cellid = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(hasCellId() ? 3 : 2).writeInt(exchange.getCode()).write(hasTarget() ? String.valueOf(getTargetId()) : "");
		if (hasCellId()) stream.writeInt(getCellid());
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
