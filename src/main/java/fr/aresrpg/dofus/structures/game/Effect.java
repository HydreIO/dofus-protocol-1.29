/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.game;

import fr.aresrpg.dofus.structures.item.EffectType;

public class Effect {
	private int type;
	private int param1;
	private long param2;
	private int param3;
	private String param4;
	private int remainingTurn;
	private int spellId;

	public Effect(int type, int param1, long param2, int param3, String param4, int remainingTurn, int spellId) {
		this.type = type;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		this.param4 = param4;
		this.remainingTurn = remainingTurn;
		this.spellId = spellId;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Effect)) return false;
		Effect f = (Effect) obj;
		return f.type == type && f.param1 == param1 && f.param2 == param2 && f.param3 == param3 && f.param4.equals(param4) && f.remainingTurn == remainingTurn && f.spellId == spellId;
	}

	public static Effect parse(String data) {
		if (data.isEmpty()) return null;
		String[] datas = data.split("#");
		int id = Integer.parseInt(datas[0], 16);
		int param1 = Integer.parseInt(datas[1], 16);
		long param2 = Long.parseLong(datas[2], 16);
		int param3 = Integer.parseInt(datas[3], 16);
		String param4 = datas.length > 4 ? datas[4] : null;
		return new Effect(id, param1, param2, param3, param4, -1, -1);
	}

	/**
	 * @return the type
	 */
	public EffectType getType() {
		return EffectType.valueOf(getTypeId());
	}

	public int getTypeId() {
		return this.type;
	}

	public int getParam1() {
		return param1;
	}

	public long getParam2() {
		return param2;
	}

	public int getParam3() {
		return param3;
	}

	/**
	 * @return the param4
	 */
	public String getParam4() {
		return param4;
	}

	/**
	 * @return the remainingTurn
	 */
	public int getRemainingTurn() {
		return remainingTurn;
	}

	public int getSpellId() {
		return spellId;
	}

	@Override
	public String toString() {
		return "Effect [type=" + getType() + "(" + getTypeId() + "), param1=" + param1 + ", param2=" + param2 + ", param3=" + param3 + ", param4=" + param4 + ", remainingTurn=" + remainingTurn
				+ ", spellId=" + spellId + "]";
	}

}
