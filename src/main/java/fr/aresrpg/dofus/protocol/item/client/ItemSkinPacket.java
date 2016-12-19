package fr.aresrpg.dofus.protocol.item.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.EquipmentPosition;

/**
 * 
 * @since
 */
public class ItemSkinPacket implements ClientPacket {

	private int itemId;
	private EquipmentPosition position;
	private int skin;

	@Override
	public void read(DofusStream stream) {
		this.itemId = stream.readInt();
		this.position = EquipmentPosition.valueOf(stream.readInt());
		this.skin = stream.readInt();
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
	 * @return the position
	 */
	public EquipmentPosition getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(EquipmentPosition position) {
		this.position = position;
	}

	/**
	 * @return the skin
	 */
	public int getSkin() {
		return skin;
	}

	/**
	 * @param skin
	 *            the skin to set
	 */
	public void setSkin(int skin) {
		this.skin = skin;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(3).writeInt(itemId).writeInt(position.getPosition()).writeInt(skin);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ItemSkinPacket [itemId=" + itemId + ", position=" + position + ", skin=" + skin + "]";
	}

}
