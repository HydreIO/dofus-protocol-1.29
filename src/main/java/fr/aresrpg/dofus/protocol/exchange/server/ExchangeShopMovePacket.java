package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.EquipmentPosition;
import fr.aresrpg.dofus.structures.item.Item;

/**
 * 
 * @since
 */
public class ExchangeShopMovePacket implements ServerPacket {
	private Item moved;
	private boolean add; // TODO verif que c'est bien un + / -

	@Override
	public void read(DofusStream stream) {
		String data = stream.read().substring(1); // remove bSuccess
		this.add = data.charAt(1) == '+';
		data = data.substring(1);
		int uuid = Integer.parseInt(data);
		int amount = stream.readInt();
		int typeid = stream.readInt();
		String effs = stream.read();
		int price = stream.readInt();
		this.moved = new Item(uuid, typeid, amount, EquipmentPosition.NO_EQUIPED, Item.parseEffects(effs), price, -1);
	}

	private String getAddValue() {
		if (isAdd()) return "+";
		else return "-";
	}

	/**
	 * @return the add
	 */
	public boolean isAdd() {
		return add;
	}

	/**
	 * @param add
	 *            the add to set
	 */
	public void setAdd(boolean add) {
		this.add = add;
	}

	/**
	 * @return the moved
	 */
	public Item getMoved() {
		return moved;
	}

	/**
	 * @param moved
	 *            the moved to set
	 */
	public void setMoved(Item moved) {
		this.moved = moved;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(4).write("K" + getAddValue() + moved.getUid()).writeInt(moved.getQuantity()).writeInt(moved.getItemTypeId()).write(Item.serializeEffects(moved.getEffects()))
				.writeInt(moved.getPrice());

	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeShopMovePacket [moved=" + moved + ", add=" + add + "]";
	}

}
