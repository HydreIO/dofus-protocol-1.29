/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

public class GameErrorAction implements GameAction{
	@Override
	public void read(DofusStream stream) {}

	@Override
	public void write(DofusStream stream) {stream.allocate(1).write(""); }

	@Override
	public String toString() {
		return "GameErrorAction()[" + getId() + ']';
	}
}
