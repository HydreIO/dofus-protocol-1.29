package fr.aresrpg.dofus.structures.game;

public class Effect {
	private int type;
	private int param1;
	private int param2;
	private int param3;
	private int param4;
	private int remainingTurn;
	private int spellId;

	public Effect(int type, int param1, int param2, int param3, int param4, int remainingTurn, int spellId) {
		this.type = type;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		this.param4 = param4;
		this.remainingTurn = remainingTurn;
		this.spellId = spellId;
	}

	public int getType() {
		return type;
	}

	public int getParam1() {
		return param1;
	}

	public int getParam2() {
		return param2;
	}

	public int getParam3() {
		return param3;
	}

	public int getParam4() {
		return param4;
	}

	public int getRemainingTurn() {
		return remainingTurn;
	}

	public int getSpellId() {
		return spellId;
	}

	@Override
	public String toString() {
		return "Effect{" +
				"type=" + type +
				", param1=" + param1 +
				", param2=" + param2 +
				", param3=" + param3 +
				", param4=" + param4 +
				", remainingTurn=" + remainingTurn +
				", spellId=" + spellId +
				'}';
	}
}
