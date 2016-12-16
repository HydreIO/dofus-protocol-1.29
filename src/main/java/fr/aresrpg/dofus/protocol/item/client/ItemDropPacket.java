/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.item.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemDropPacket implements ClientPacket {

	private int itemId;
	private int amount;

	public ItemDropPacket() {
	}

	/**
	 * @param itemId
	 * @param amount
	 */
	public ItemDropPacket(int itemId, int amount) {
		super();
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
		return "ItemDropPacket [itemId=" + itemId + ", amount=" + amount + "]";
	}

}
