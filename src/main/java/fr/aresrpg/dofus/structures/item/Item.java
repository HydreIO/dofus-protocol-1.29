/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.item;

import fr.aresrpg.dofus.structures.EquipmentPosition;
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
	private int remainingHours;

	public Item(int uid, int itemTypeId, int quantity, int position, Effect[] effects, int price, int skin) {
		this.uid = uid;
		this.itemTypeId = itemTypeId;
		this.quantity = quantity;
		this.position = position;
		this.effects = effects;
		this.price = price;
		this.skin = skin;
	}

	public Item(int typeId, int quantity) {
		this(0, typeId, quantity, EquipmentPosition.NO_EQUIPED.getPosition(), null);
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
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

	/**
	 * @param itemTypeId
	 *            the itemTypeId to set
	 */
	public void setItemTypeId(int itemTypeId) {
		this.itemTypeId = itemTypeId;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @param effects
	 *            the effects to set
	 */
	public void setEffects(Effect[] effects) {
		this.effects = effects;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * @param skin
	 *            the skin to set
	 */
	public void setSkin(int skin) {
		this.skin = skin;
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
		int pos = data[3].isEmpty() ? -1 : Integer.parseInt(data[3], 16);
		return new Item(
				Integer.parseInt(data[0], 16),
				Integer.parseInt(data[1], 16),
				Integer.parseInt(data[2], 16),
				pos,
				parseEffects(data[4]));
	}

	public String serialize() {
		return Integer.toHexString(getUid()) + "~" +
				Integer.toHexString(getItemTypeId()) + "~" +
				Integer.toHexString(getQuantity()) + "~" +
				(getPosition() == -1 ? "" : Integer.toHexString(position)) + "~" +
				serializeEffects(getEffects());
	}

	public static String serializeItem(Item i) {
		return i.serialize();
	}

	public static Effect[] parseEffects(String d) {
		String[] rawEffects = d.split(",");
		if (rawEffects[0].isEmpty()) return null;
		Effect[] effects = new Effect[rawEffects.length];
		for (int i = 0; i < rawEffects.length; i++)
			effects[i] = parseEffect(rawEffects[i]);
		return effects;
	}

	public static Effect parseEffect(String d) {
		return Effect.parse(d);
	}

	public String serializeEffects() {
		return serializeEffects(getEffects());
	}

	public static String serializeEffects(Effect[] effects) {
		StringJoiner rawEffects = new StringJoiner(",");
		if (effects == null) return "";
		for (Effect e : effects)
			rawEffects.add(serializeEffect(e));
		return rawEffects.toString();
	}

	public static String serializeEffect(Effect e) {
		return (e.getTypeId() == -1 ? "-1" : Integer.toHexString(e.getTypeId())) + "#" +
				Integer.toHexString(e.getParam1()) + "#" +
				Long.toHexString(e.getParam2()) + "#" +
				Integer.toHexString(e.getParam3()) + (e.getParam4() == null ? "" : "#" + e.getParam4());
	}

}
