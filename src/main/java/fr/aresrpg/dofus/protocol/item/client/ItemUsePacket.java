package fr.aresrpg.dofus.protocol.item.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.util.Convert;

/**
 * 
 * @since
 */
public class ItemUsePacket implements ClientPacket {

	private int itemId;
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
		super();
		this.itemId = itemId;
		this.sprite = sprite;
		this.cellnum = cellnum;
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
		this.sprite = Convert.toInt(stream.read(), -1); // optional
		this.cellnum = stream.available() > 0 ? stream.readInt() : -1;
	}

	int allocate() {
		return cellnum == -1 ? 2 : 3;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(allocate()).writeInt(itemId).write(sprite == -1 ? "" : String.valueOf(sprite));
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
