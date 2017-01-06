package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

/**
 * 
 * @since
 */
public class GameTacleAction implements GameAction {

	@Override
	public void read(DofusStream stream) {

	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1);
	}

	@Override
	public String toString() {
		return "GameTacleAction []";
	}

}
