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

/**
 * 
 * @since
 */
public enum FightType {

	DUEL(0),
	AGRESSION(1),
	MONSTER(4),
	PERCO(5),
	CROCO(6);

	private int id;

	private FightType(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static FightType fromId(int id) {
		for (FightType t : values())
			if (id == t.getId()) return t;
		return null;
	}

}
