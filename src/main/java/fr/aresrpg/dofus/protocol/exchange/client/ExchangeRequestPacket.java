/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Exchange;

/**
 * 
 * @since
 */
public class ExchangeRequestPacket implements ClientPacket {

	private int targetId = -1000;
	private Exchange exchange;
	private int cellid = -1000;

	public ExchangeRequestPacket(Exchange exchange, int targetId, int cellnum) {
		this.targetId = targetId;
		this.exchange = exchange;
		this.cellid = cellnum;
	}

	@Override
	public void read(DofusStream stream) {
		this.exchange = Exchange.valueOf(stream.readInt());
		this.targetId = stream.readInt();
		if (stream.available() > 0) this.cellid = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(hasCellId() ? 3 : 2).writeInt(exchange.getCode()).write(hasTarget() ? String.valueOf(getTargetId()) : "");
		if (hasCellId()) stream.writeInt(getCellid());
	}

	public ExchangeRequestPacket() {
	}

	public ExchangeRequestPacket(Exchange type, int targetId) {
		this(type, targetId, -1000);
	}

	public ExchangeRequestPacket(int cellid, Exchange type) {
		this(type, -1000, cellid);
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
		return getTargetId() != -1000;
	}

	public boolean hasCellId() {
		return getCellid() != -1000;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeRequestPacket [targetId=" + targetId + ", exchange=" + exchange + ", cellid=" + cellid + "]";
	}

}
