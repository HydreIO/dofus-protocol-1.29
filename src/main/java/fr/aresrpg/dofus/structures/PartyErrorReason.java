/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures;

/**
 * 
 * @since
 */
public enum PartyErrorReason {

	ALREADY_IN_GROUP('a'),
	FULL('f'),
	UNKNOW_PLAYER('n');

	private char c;

	/**
	 * @param c
	 */
	private PartyErrorReason(char c) {
		this.c = c;
	}

	/**
	 * @return the c
	 */
	public char getCode() {
		return c;
	}

	public static PartyErrorReason valueOf(char code) {
		if (code == 'a') return ALREADY_IN_GROUP;
		else if (code == 'f') return FULL;
		else if (code == 'n') return UNKNOW_PLAYER;
		else throw new IllegalArgumentException("The char '" + code + "' is invalid !");
	}

}
