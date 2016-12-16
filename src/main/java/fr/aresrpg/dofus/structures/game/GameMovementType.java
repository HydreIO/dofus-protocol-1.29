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
public enum GameMovementType {

	SHOW('+'),
	UPDATE('~'),
	REMOVE('-');

	private char value;

	private GameMovementType(char c) {
		this.value = c;
	}

	/**
	 * @return the value
	 */
	public char getValue() {
		return value;
	}

	public static GameMovementType fromChar(char c) {
		for (GameMovementType t : values())
			if (t.getValue() == c) return t;
		return null;
	}

}
