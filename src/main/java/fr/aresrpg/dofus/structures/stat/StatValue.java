package fr.aresrpg.dofus.structures.stat;

public class StatValue {
	private int base;
	private int equipment;
	private int gift;
	private int context;

	public StatValue(int base, int equipment, int gift, int context) {
		this.base = base;
		this.equipment = equipment;
		this.gift = gift;
		this.context = context;
	}

	public int getBase() {
		return base;
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
}
