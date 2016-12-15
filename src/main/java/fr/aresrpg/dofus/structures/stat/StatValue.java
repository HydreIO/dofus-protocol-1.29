package fr.aresrpg.dofus.structures.stat;

public class StatValue {
	private int equipment;
	private int gift;
	private int context;
	private int total;

	public StatValue(int equipment, int gift, int context, int total) {
		this.equipment = equipment;
		this.gift = gift;
		this.context = context;
		this.total = total;
	}

	public int getEquipment() {
		return equipment;
	}

	public int getGift() {
		return gift;
	}

	public int getContext() {
		return context;
	}

	public int getTotal() {
		return total;
	}
}
