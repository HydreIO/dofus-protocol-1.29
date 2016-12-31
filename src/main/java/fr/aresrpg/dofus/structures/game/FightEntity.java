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
	private long id;
	private int life;
	private int lifeMax;
	private int pa;
	private int pm;
	private int unknowNumber;
	private boolean dead;

	/**
	 * @param id
	 * @param life
	 * @param lifeMax
	 * @param pa
	 * @param pm
	 * @param unknowNumber
	 */
	public FightEntity(long id, int life, int lifeMax, int pa, int pm, int unknowNumber) {
		this.id = id;
		this.life = life;
		this.lifeMax = lifeMax;
		this.pa = pa;
		this.pm = pm;
		this.unknowNumber = unknowNumber;
	}

	public FightEntity(long id) {
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
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @param unknowNumber
	 *            the unknowNumber to set
	 */
	public void setUnknowNumber(int unknowNumber) {
		this.unknowNumber = unknowNumber;
	}

	public String serialize() {
		return new StringJoiner(";").add("" + id).add("0").add("" + life).add("" + pa)
				.add("" + pm).add("" + unknowNumber).add("").add("" + lifeMax).toString();
	}

	public long getId() {
		return id;
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

	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life
	 *            the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * @return the lifeMax
	 */
	public int getLifeMax() {
		return lifeMax;
	}

	/**
	 * @param lifeMax
	 *            the lifeMax to set
	 */
	public void setLifeMax(int lifeMax) {
		this.lifeMax = lifeMax;
	}

	/**
	 * @return the pa
	 */
	public int getPa() {
		return pa;
	}

	/**
	 * @param pa
	 *            the pa to set
	 */
	public void setPa(int pa) {
		this.pa = pa;
	}

	/**
	 * @return the pm
	 */
	public int getPm() {
		return pm;
	}

	/**
	 * @param pm
	 *            the pm to set
	 */
	public void setPm(int pm) {
		this.pm = pm;
	}

	/**
	 * @return the unknowNumber
	 */
	public int getUnknowNumber() {
		return unknowNumber;
	}

	@Override
	public String toString() {
		return "FightEntity [id=" + id + ", lp=" + life + ", lpMax=" + lifeMax + ", ap=" + pa + ", mp=" + pm + ", unknowNumber=" + unknowNumber + ", dead=" + dead + "]";
	}

}
