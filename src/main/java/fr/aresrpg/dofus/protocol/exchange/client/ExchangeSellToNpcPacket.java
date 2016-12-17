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

/**
 * 
 * @since
 */
public class ExchangeSellToNpcPacket implements ClientPacket {

	private int itemUid;
	private int amount;

	/**
	 * @param itemUid
	 * @param amount
	 */
	public ExchangeSellToNpcPacket(int itemUid, int amount) {
		this.itemUid = itemUid;
		this.amount = amount;
	}

	public ExchangeSellToNpcPacket() {
	}

	@Override
	public void read(DofusStream stream) {
		this.itemUid = stream.readInt();
		this.amount = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(itemUid).writeInt(amount);
	}

	/**
	 * @return the itemUid
	 */
	public int getItemUid() {
		return itemUid;
	}

	/**
	 * @param itemUid
	 *            the itemUid to set
	 */
	public void setItemUid(int itemUid) {
		this.itemUid = itemUid;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeSellToNpcPacket [itemUid=" + itemUid + ", amount=" + amount + "]";
	}

}
