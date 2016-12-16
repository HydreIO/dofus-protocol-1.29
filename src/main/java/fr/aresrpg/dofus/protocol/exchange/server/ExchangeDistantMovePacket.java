package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.EquipmentPosition;
import fr.aresrpg.dofus.structures.item.Item;
import fr.aresrpg.dofus.util.Convert;

/**
 * 
 * @since
 */
public class ExchangeDistantMovePacket implements ServerPacket {

	private Item moved;
	private boolean add; // TODO verif que c'est bien un + / -
	private int remainingHours = -1; // dans le cas d'une mise en vente j'imagine
	private int kamas = -1;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read().substring(1); // remove bSuccess
		char c = data.charAt(0);
		switch (c) {
			case 'O':
				this.add = data.charAt(1) == '+';
				data = data.substring(2);
				int uuid = Integer.parseInt(data);
				int amount = stream.readInt();
				int typeid = stream.readInt();
				String effs = stream.read();
				this.moved = new Item(uuid, typeid, amount, EquipmentPosition.NO_EQUIPED, Item.parseEffects(effs));
				break;
			case 'G':
				this.kamas = Integer.parseInt(data.substring(1));
				break;
			default:
				this.add = data.charAt(0) == '+';
				data = data.substring(1);
				int uid = Integer.parseInt(data);
				int quantity = stream.readInt();
				int itemtype = stream.readInt();
				String eff = stream.read();
				int price = stream.readInt();
				this.remainingHours = Convert.toInt(stream.read());
				this.moved = new Item(uid, itemtype, quantity, EquipmentPosition.NO_EQUIPED, Item.parseEffects(eff), price, -1);
				break;
		}
	}

	private String getAddValue() {
		if (isAdd()) return "+";
		else return "-";
	}

	@Override
	public void write(DofusStream stream) {
		if (getMoved() == null) stream.allocate(1).write("KG" + String.valueOf(getKamas()));
		else if (getRemainingHours() == -1)
			stream.allocate(4).write("KO" + getAddValue() + moved.getUid()).writeInt(moved.getQuantity()).writeInt(moved.getItemTypeId()).write(Item.serializeEffects(moved.getEffects()));
		else stream.allocate(6).write("K" + getAddValue() + moved.getUid()).writeInt(moved.getQuantity()).writeInt(moved.getItemTypeId()).write(Item.serializeEffects(moved.getEffects()))
				.writeInt(moved.getPrice()).writeInt(getRemainingHours());
	}

	/**
	 * @return the moved
	 */
	public Item getMoved() {
		return moved;
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
	 * @param moved
	 *            the moved to set
	 */
	public void setMoved(Item moved) {
		this.moved = moved;
	}

	/**
	 * @return the remainingHours
	 */
	public int getRemainingHours() {
		return remainingHours;
	}

	/**
	 * @param remainingHours
	 *            the remainingHours to set
	 */
	public void setRemainingHours(int remainingHours) {
		this.remainingHours = remainingHours;
	}

	/**
	 * @return the kamas
	 */
	public int getKamas() {
		return kamas;
	}

	/**
	 * @param kamas
	 *            the kamas to set
	 */
	public void setKamas(int kamas) {
		this.kamas = kamas;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeDistantMovePacket [moved=" + moved + ", add=" + add + ", remainingHours=" + remainingHours + ", kamas=" + kamas + "]";
	}

}
