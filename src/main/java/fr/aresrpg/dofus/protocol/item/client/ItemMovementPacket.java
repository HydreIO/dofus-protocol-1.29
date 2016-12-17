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
import fr.aresrpg.dofus.structures.EquipmentPosition;

/**
 * 
 * @since
 */
public class ItemMovementPacket implements ClientPacket {

	private int itemid;
	private int position;
	private int quantity;

	public ItemMovementPacket() {
	}

	/**
	 * @param itemid
	 * @param position
	 * @param quantity
	 */
	public ItemMovementPacket(int itemid, int position, int quantity) {
		this.itemid = itemid;
		this.position = position;
		this.quantity = quantity;
	}

	@Override
	public void read(DofusStream stream) {
		this.itemid = stream.readInt();
		this.position = stream.readInt();
		this.quantity = stream.available() > 0 ? stream.readInt() : 1;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(3).writeInt(itemid).writeInt(position).writeInt(quantity);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	public EquipmentPosition getEquipPosition() {
		return EquipmentPosition.valueOf(position);
	}

	/**
	 * @return the itemid
	 */
	public int getItemid() {
		return itemid;
	}

	/**
	 * @param itemid
	 *            the itemid to set
	 */
	public void setItemid(int itemid) {
		this.itemid = itemid;
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

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "ItemMovementPacket [itemid=" + itemid + ", position=" + getEquipPosition() + "(" + position + "), quantity=" + quantity + "]";
	}

}
