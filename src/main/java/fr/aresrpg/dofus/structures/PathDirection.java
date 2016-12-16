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

public enum PathDirection {
	DOWN_RIGHT,
	DOWN,
	DOWN_LEFT,
	LEFT,
	UP_LEFT,
	UP,
	UP_RIGHT,
	RIGHT;

	public static PathDirection valueOf(int code) {
		if(code >= 0 && code < values().length)
			return values()[code];
		else
			return null;
	}

}
