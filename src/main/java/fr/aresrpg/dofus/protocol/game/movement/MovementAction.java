/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.movement;

/**
 * 
 * @since
 */
public interface MovementAction {

	long getId();

	void setCellId(int id);

	int getCellId();

	@Override
	boolean equals(Object obj);

}
