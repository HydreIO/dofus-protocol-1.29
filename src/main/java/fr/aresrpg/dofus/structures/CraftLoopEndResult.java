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
public enum CraftLoopEndResult {

	OK,
	INTERRUPT,
	FAIL,
	INVALID;
	public static CraftLoopEndResult valueOf(int code) {
		return values()[code - 1];
	}

}
