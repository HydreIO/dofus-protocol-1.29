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

public class FightChallenge {
	private Challenge type;
	private boolean showTarget;
	private int targetId;
	private int basicXpBonus;
	private int teamXpBonus;
	private int basicDropBonus;
	private int teamDropBonus;

	public FightChallenge(Challenge id, boolean showTarget, int targetId, int basicXpBonus, int teamXpBonus, int basicDropBonus, int teamDropBonus) {
		this.type = id;
		this.showTarget = showTarget;
		this.targetId = targetId;
		this.basicXpBonus = basicXpBonus;
		this.teamXpBonus = teamXpBonus;
		this.basicDropBonus = basicDropBonus;
		this.teamDropBonus = teamDropBonus;
	}

	public static FightChallenge parse(String datas) {
		String[] data = datas.split(";");
		return new FightChallenge(
				Challenge.fromId(Integer.parseInt(data[0])),
				data[1].equals("1"),
				Convert.toInt(data[2]),
				Convert.toInt(data[3]),
				Convert.toInt(data[4]),
				Convert.toInt(data[5]),
				Convert.toInt(data[6]));
	}

	public String serialize() {
		return new StringJoiner(";").add(Convert.toDofusString(getType().getId())).add(isShowTarget() ? "1" : "0").add(Convert.toDofusString(targetId)).add(Convert.toDofusString(basicXpBonus))
				.add(Convert.toDofusString(teamXpBonus)).add(Convert.toDofusString(basicDropBonus)).add(Convert.toDofusString(teamDropBonus)).toString();
	}

	/**
	 * @return the type
	 */
	public Challenge getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Challenge type) {
		this.type = type;
	}

	/**
	 * @return the showTarget
	 */
	public boolean isShowTarget() {
		return showTarget;
	}

	/**
	 * @param showTarget
	 *            the showTarget to set
	 */
	public void setShowTarget(boolean showTarget) {
		this.showTarget = showTarget;
	}

	/**
	 * @return the targetId
	 */
	public int getTargetId() {
		return targetId;
	}

	/**
	 * @param targetId
	 *            the targetId to set
	 */
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	/**
	 * @return the basicXpBonus
	 */
	public int getBasicXpBonus() {
		return basicXpBonus;
	}

	/**
	 * @param basicXpBonus
	 *            the basicXpBonus to set
	 */
	public void setBasicXpBonus(int basicXpBonus) {
		this.basicXpBonus = basicXpBonus;
	}

	/**
	 * @return the teamXpBonus
	 */
	public int getTeamXpBonus() {
		return teamXpBonus;
	}

	/**
	 * @param teamXpBonus
	 *            the teamXpBonus to set
	 */
	public void setTeamXpBonus(int teamXpBonus) {
		this.teamXpBonus = teamXpBonus;
	}

	/**
	 * @return the basicDropBonus
	 */
	public int getBasicDropBonus() {
		return basicDropBonus;
	}

	/**
	 * @param basicDropBonus
	 *            the basicDropBonus to set
	 */
	public void setBasicDropBonus(int basicDropBonus) {
		this.basicDropBonus = basicDropBonus;
	}

	/**
	 * @return the teamDropBonus
	 */
	public int getTeamDropBonus() {
		return teamDropBonus;
	}

	/**
	 * @param teamDropBonus
	 *            the teamDropBonus to set
	 */
	public void setTeamDropBonus(int teamDropBonus) {
		this.teamDropBonus = teamDropBonus;
	}

	@Override
	public String toString() {
		return "FightChallenge [type=" + type + ", showTarget=" + showTarget + ", targetId=" + targetId + ", basicXpBonus=" + basicXpBonus + ", teamXpBonus=" + teamXpBonus + ", basicDropBonus="
				+ basicDropBonus + ", teamDropBonus=" + teamDropBonus + "]";
	}

}
