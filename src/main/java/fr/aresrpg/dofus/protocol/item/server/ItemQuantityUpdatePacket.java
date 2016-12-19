/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemQuantityUpdatePacket implements ServerPacket {

	private int itemUid;
	private int amount;

	public ItemQuantityUpdatePacket() {
	}

	/**
	 * @param intemUid
	 * @param amount
	 */
	public ItemQuantityUpdatePacket(int intemUid, int amount) {
		this.itemUid = intemUid;
		this.amount = amount;
	}

	/**
	 * @return the intemUid
	 */
	public int getItemUid() {
		return itemUid;
	}

	/**
	 * @param intemUid
	 *            the intemUid to set
	 */
	public void setIntemUid(int intemUid) {
		this.itemUid = intemUid;
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
	public void read(DofusStream stream) {
		this.itemUid = stream.readInt();
		this.amount = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(itemUid).writeInt(amount);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ItemQuantityUpdatePacket [intemUid=" + itemUid + ", amount=" + amount + "]";
	}

}
