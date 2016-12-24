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

import fr.aresrpg.dofus.util.Convert;

import java.util.StringJoiner;

public class FightEntity {
	private int id;
	private int lp;
	private int lpMax;
	private int ap;
	private int mp;
	private int unknowNumber;
	private boolean dead;

	public FightEntity(int id, int lp, int lpMax, int ap, int mp, int unknowNumber) {
		this.id = id;
		this.lp = lp;
		this.lpMax = lpMax;
		this.ap = ap;
		this.mp = mp;
		this.unknowNumber = unknowNumber;
	}

	public FightEntity(int id) {
		this(id, 0, 0, 0, 0, 0);
	}

	public static FightEntity parse(String data) {
		String[] datas = data.split(";", -1);
		// GTM|2397625;0;555;7;3;226;;555|2221954;0;2650;11;7;124;;2681|-4;0;42;8;4;94;;221|-3;0;210;8;4;177;;210|-2;1|-1;0;50;4;4;148;;50
		if (datas.length == 2) return new FightEntity(Convert.toInt(datas[0]));
		return new FightEntity(
				Convert.toInt(datas[0]),
				Convert.toInt(datas[2]),
				Convert.toInt(datas[7]),
				Convert.toInt(datas[3]),
				Convert.toInt(datas[4]),
				Convert.toInt(datas[5]));
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param lp
	 *            the lp to set
	 */
	public void setLp(int lp) {
		this.lp = lp;
	}

	/**
	 * @param lpMax
	 *            the lpMax to set
	 */
	public void setLpMax(int lpMax) {
		this.lpMax = lpMax;
	}

	/**
	 * @param ap
	 *            the ap to set
	 */
	public void setAp(int ap) {
		this.ap = ap;
	}

	/**
	 * @param mp
	 *            the mp to set
	 */
	public void setMp(int mp) {
		this.mp = mp;
	}

	/**
	 * @param unknowNumber
	 *            the unknowNumber to set
	 */
	public void setUnknowNumber(int unknowNumber) {
		this.unknowNumber = unknowNumber;
	}

	public String serialize() {
		return new StringJoiner(";").add("" + id).add("0").add("" + lp).add("" + ap)
				.add("" + mp).add("" + unknowNumber).add("").add("" + lpMax).toString();
	}

	public int getId() {
		return id;
	}

	public int getLp() {
		return lp;
	}

	/**
	 * @return the dead
	 */
	public boolean isDead() {
		return dead;
	}

	/**
	 * @param dead
	 *            the dead to set
	 */
	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public int getLpMax() {
		return lpMax;
	}

	public int getAp() {
		return ap;
	}

	public int getMp() {
		return mp;
	}

	/**
	 * @return the unknowNumber
	 */
	public int getUnknowNumber() {
		return unknowNumber;
	}

	@Override
	public String toString() {
		return "FightEntity [id=" + id + ", lp=" + lp + ", lpMax=" + lpMax + ", ap=" + ap + ", mp=" + mp + ", unknowNumber=" + unknowNumber + ", dead=" + dead + "]";
	}

}
