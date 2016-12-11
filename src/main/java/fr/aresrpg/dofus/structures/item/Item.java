package fr.aresrpg.dofus.structures.item;

import fr.aresrpg.dofus.structures.game.Effect;

import java.util.Arrays;
import java.util.StringJoiner;

public class Item {
	private int id;
	private int uniqueId;
	private int quantity;
	private int position;
	private Effect[] effects;
	/*private int price;
	private int skin;
	private int mood;*/

	public Item(int id, int uniqueId, int quantity, int position, Effect[] effects) {
		this.id = id;
		this.uniqueId = uniqueId;
		this.quantity = quantity;
		this.position = position;
		this.effects = effects;
	}

	public int getId() {
		return id;
	}

	public int getUniqueId() {
		return uniqueId;
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

	@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", uniqueId=" + uniqueId +
				", quantity=" + quantity +
				", position=" + position +
				", effects=" + Arrays.toString(effects) +
				'}';
	}

	public static Item parseItem(String d){
		String[] data = d.split(";");
		String[] rawEffects = data[4].split(",");
		Effect[] effects = new Effect[rawEffects.length];
		for(int i = 0 ; i < rawEffects.length ; i++)
			effects[i] = parseEffect(rawEffects[i]);
		return new Item(
				Integer.parseInt(data[0] , 16),
				Integer.parseInt(data[1] , 16),
				Integer.parseInt(data[2] , 16),
				data[3].isEmpty() ? -1 : Integer.parseInt(data[3] , 16),
				effects
		);
	}

	public static String serializeItem(Item i){
		StringJoiner rawEffects = new StringJoiner(",");
		for(Effect e : i.getEffects())
			rawEffects.add(serializeEffect(e));
		return Integer.toHexString(i.getId()) + ";" +
				Integer.toHexString(i.getUniqueId()) + ";" +
				Integer.toHexString(i.getQuantity()) + ";" +
				(i.getPosition() == -1 ? "" : Integer.toHexString(i.getPosition())) + ";" +
				rawEffects;
	}

	private static Effect parseEffect(String d){
		String[] data = d.split("#");
		return new Effect(
				Integer.parseInt(data[0]),
				Integer.parseInt(data[1]),
				Integer.parseInt(data[2]),
				Integer.parseInt(data[3]),
				Integer.parseInt(data[4]),
				-1,
				-1
		);
	}

	private static String serializeEffect(Effect e){
		return e.getType() + "#" +
				e.getParam1() + "#" +
				e.getParam2() + "#" +
				e.getParam3() + "#" +
				e.getParam4();
	}
}
