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
public enum JoinError {

	FIGHT_FULL('c'),
	TEAM_FULL('t'),
	TEAM_DIFFERENT_ALIGNEMENT('a'),
	ERROR_GUILD('g'),
	TOO_LATE('l'),
	MUTANT('m'),
	MAP('p'),
	RESPAWN('r'),
	OCCUPED('o'),
	TARGET_OCCUPED('z'),
	CANT_FIGHT('h'),
	NO_RIGHTS('i'),
	ERROR_21('s'),
	SUBSCRIPTION_OUT('n'),
	NO_SUBSCRIPTION('b'),
	TEAM_CLOSED('f'),
	ZOMBIE('d');

	private char value;

	private JoinError(char c) {
		this.value = c;
	}

	/**
	 * @return the value
	 */
	public char getValue() {
		return value;
	}

	public static JoinError fromValue(char c) {
		for (JoinError e : values())
			if (c == e.getValue()) return e;
		return null;
	}
}
