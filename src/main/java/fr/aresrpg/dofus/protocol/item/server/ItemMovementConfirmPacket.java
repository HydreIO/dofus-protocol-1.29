/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemMovementConfirmPacket implements ServerPacket {

	private long itemUid;
	private int position = -1;

	public ItemMovementConfirmPacket() {
	}

	/**
	 * @param itemUid
	 * @param position
	 */
	public ItemMovementConfirmPacket(long itemUid, int position) {
		this.itemUid = itemUid;
		this.position = position;
	}

	@Override
	public void read(DofusStream stream) {
		this.itemUid = stream.readInt();
		if (stream.available() > 0) this.position = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeLong(itemUid).writeInt(position);
	}

	/**
	 * @return the itemUid
	 */
	public long getItemUid() {
		return itemUid;
	}

	/**
	 * @param itemUid
	 *            the itemUid to set
	 */
	public void setItemUid(long itemUid) {
		this.itemUid = itemUid;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ItemMovementConfirmPacket [itemUid=" + itemUid + ", position=" + position + "]";
	}

}
