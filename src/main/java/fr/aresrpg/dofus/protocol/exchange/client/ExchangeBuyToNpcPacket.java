package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since 
 */
public class ExchangeBuyToNpcPacket implements ClientPacket {
	
	private int itemUid;
	private int amount;

	/**
	 * @param itemUid
	 * @param amount
	 */
	public ExchangeBuyToNpcPacket(int itemUid, int amount) {
		this.itemUid = itemUid;
		this.amount = amount;
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
		return "ExchangeBuyToNpcPacket [itemUid=" + itemUid + ", amount=" + amount + "]";
	}


}
