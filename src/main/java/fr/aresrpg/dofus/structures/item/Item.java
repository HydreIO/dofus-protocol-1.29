package fr.aresrpg.dofus.structures.item;

import fr.aresrpg.dofus.structures.game.Effect;
import fr.aresrpg.dofus.util.StringUtils;

import java.util.Arrays;
import java.util.StringJoiner;

public class Item {
	private int uid;
	private int itemTypeId;
	private int quantity;
	private int position;
	private Effect[] effects;
	private int price;
	private int skin;
	// private int mood;

	public Item(int uid, int itemTypeId, int quantity, int position, Effect[] effects, int price, int skin) {
		this.uid = uid;
		this.itemTypeId = itemTypeId;
		this.quantity = quantity;
		this.position = position;
		this.effects = effects;
		this.price = price;
		this.skin = skin;
	}

	public Item(int uid, int itemTypeId, int quantity, int position, Effect[] effects) {
		this(uid, itemTypeId, quantity, position, effects, -1, -1);
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @return the itemTypeId
	 */
	public int getItemTypeId() {
		return itemTypeId;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getPosition() {
		return position;
	}

	public Effect[] getEffects() {
		return effects;
	}

	public int getSkin() {
		return skin;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public String toString() {
		return "Item [uid=" + uid + ", itemTypeId=" + itemTypeId + ", quantity=" + quantity + ", position=" + position + ", effects=" + Arrays.toString(effects) + ", price=" + price + ", skin=" + skin
				+ "]";
	}

	public static Item parseItem(String d) {
		String[] data = StringUtils.split(d, "~");
		return new Item(
				Integer.parseInt(data[0], 16),
				Integer.parseInt(data[1], 16),
				Integer.parseInt(data[2], 16),
				data[3].isEmpty() ? -1 : Integer.parseInt(data[3], 16),
				parseEffects(data[4]));
	}

	public static String serializeItem(Item i) {
		return Integer.toHexString(i.getUid()) + "~" +
				Integer.toHexString(i.getItemTypeId()) + "~" +
				Integer.toHexString(i.getQuantity()) + "~" +
				(i.getPosition() == -1 ? "" : Integer.toHexString(i.getPosition())) + "~" +
				serializeEffects(i.getEffects());
	}

	public static Effect[] parseEffects(String d) {
		String[] rawEffects = d.split(",");
		Effect[] effects = new Effect[rawEffects.length];
		for (int i = 0; i < rawEffects.length; i++)
			effects[i] = parseEffect(rawEffects[i]);
		return effects;
	}

	public static Effect parseEffect(String d) {
		if (d.isEmpty())
			return null;
		String[] data = d.split("#");
		return new Effect(
				Integer.parseInt(data[0], 16),
				Integer.parseInt(data[1], 16),
				Integer.parseInt(data[2], 16),
				Integer.parseInt(data[3], 16),
				/* Integer.parseInt(data[4]) */ -1, // TODO
				-1,
				-1);
	}

	public static String serializeEffects(Effect[] effects) {
		StringJoiner rawEffects = new StringJoiner(",");
		for (Effect e : effects)
			rawEffects.add(serializeEffect(e));
		return rawEffects.toString();
	}

	public static String serializeEffect(Effect e) {
		return e.getType() + "#" +
				e.getParam1() + "#" +
				e.getParam2() + "#" +
				e.getParam3() + "#" +
				e.getParam4();
	}
}
