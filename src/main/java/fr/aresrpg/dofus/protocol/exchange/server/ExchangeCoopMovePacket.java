/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.EquipmentPosition;
import fr.aresrpg.dofus.structures.item.Item;

/**
 * 
 * @since
 */
public class ExchangeCoopMovePacket implements ServerPacket {
	private Item moved;
	private int kamas = -1;
	private boolean add; // TODO verif que c'est bien un + / -

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
		}
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
	public void write(DofusStream stream) {
		if (getMoved() == null) stream.allocate(1).write("KG" + getKamas());
		else stream.allocate(4).write("KO" + getAddValue() + moved.getUid()).writeInt(moved.getQuantity()).writeInt(moved.getItemTypeId()).write(Item.serializeEffects(moved.getEffects()));

	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeCoopMovePacket [moved=" + moved + ", kamas=" + kamas + ", add=" + add + "]";
	}

}
