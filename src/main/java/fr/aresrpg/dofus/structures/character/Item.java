package fr.aresrpg.dofus.structures.character;

public class Item {
	private int data;
	private int amount;

	public Item(int data, int amount) {
		this.data = data;
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Item [data=" + data + ", amount=" + amount + "]";
	}

	public int getAmount() {
		return amount;
	}

	public int getData() {
		return data;
	}
}
