package fr.aresrpg.dofus.protocol.item.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemDestroyPacket implements ClientPacket {

	private int itemId;
	private int amount;

	public ItemDestroyPacket() {
	}

	/**
	 * @param itemId
	 * @param amount
	 */
	public ItemDestroyPacket(int itemId, int amount) {
		this.itemId = itemId;
		this.amount = amount;
	}

	@Override
	public void read(DofusStream stream) {
		this.itemId = stream.readInt();
		this.amount = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(itemId).writeInt(amount);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
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
	public String toString() {
		return "ItemDestroyPacket [itemId=" + itemId + ", amount=" + amount + "]";
	}

}
