/*******************************************************************************
 * BotFather (C) - Dofus 1.29
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.game;

/**
 * 
 * @since
 */
public enum GameMovementAction {

	DEFAULT(0), // none id
	CREATE_INVOCATION(-1),
	CREATE_MONSTER(-2),
	CREATE_MONSTER_GROUP(-3),
	CREATE_NPC(-4),
	CREATE_OFFLINE_PLAYER(-5), // mode marchand ?
	CREATE_PERCO(-6),
	CREATE_MUTANT_WITH_NAME(-7),
	CREATE_MUTANT_WITHOUT_NAME(-8),
	CREATE_PARK_MOUNT(-9),
	CREATE_PRISM(-10);

	private int id;

	private GameMovementAction(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static GameMovementAction fromId(int id) {
		for (GameMovementAction a : values())
			if (a.getId() == id) return a;
		return GameMovementAction.DEFAULT;
	}

}
