/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.item.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.util.Convert;

/**
 * 
 * @since
 */
public class ItemUsePacket implements ClientPacket {

	private long itemId;
	private int sprite;
	private int cellnum;

	public ItemUsePacket() {
	}

	/**
	 * @param itemId
	 * @param sprite
	 * @param cellnum
	 */
	public ItemUsePacket(int itemId, int sprite, int cellnum) {
		this.itemId = itemId;
		this.sprite = sprite;
		this.cellnum = cellnum;
	}

	/**
	 * @return the itemId
	 */
	public long getItemId() {
		return itemId;
	}

	/**
	 * @param itemuid
	 *            the itemId to set
	 */
	public void setItemId(long itemuid) {
		this.itemId = itemuid;
	}

	/**
	 * @return the sprite
	 */
	public int getSprite() {
		return sprite;
	}

	/**
	 * @param sprite
	 *            the sprite to set
	 */
	public void setSprite(int sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the cellnum
	 */
	public int getCellnum() {
		return cellnum;
	}

	/**
	 * @param cellnum
	 *            the cellnum to set
	 */
	public void setCellnum(int cellnum) {
		this.cellnum = cellnum;
	}

	@Override
	public void read(DofusStream stream) {
		this.itemId = stream.readInt();
		this.sprite = stream.available() > 0 ? Convert.toInt(stream.read(), -1) : -1; // optional
		this.cellnum = stream.available() > 0 ? stream.readInt() : -1;
	}

	int allocate() {
		return cellnum == -1 ? 2 : 3;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(allocate()).writeLong(itemId).write(sprite == -1 ? "" : String.valueOf(sprite));
		if (allocate() == 3) stream.writeInt(cellnum);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ItemUsePacket [itemId=" + itemId + ", sprite=" + sprite + ", cellnum=" + cellnum + "]";
	}

}
