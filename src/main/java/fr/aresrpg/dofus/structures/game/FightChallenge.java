/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.game;

public class FightChallenge {
	private int id;
	private boolean showTarget;
	private int targetId;
	private int basicXpBonus;
	private int teamXpBonus;
	private int basicDropBonus;
	private int teamDropBonus;

	public FightChallenge(int id, boolean showTarget, int targetId, int basicXpBonus, int teamXpBonus, int basicDropBonus, int teamDropBonus) {
		this.id = id;
		this.showTarget = showTarget;
		this.targetId = targetId;
		this.basicXpBonus = basicXpBonus;
		this.teamXpBonus = teamXpBonus;
		this.basicDropBonus = basicDropBonus;
		this.teamDropBonus = teamDropBonus;
	}
}
