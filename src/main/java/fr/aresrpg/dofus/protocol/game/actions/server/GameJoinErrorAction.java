package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;
import fr.aresrpg.dofus.structures.game.FightJoinError;

/**
 * 
 * @since 
 */
public class GameJoinErrorAction implements GameAction {
	
	private FightJoinError error;
	
	@Override
	public void read(DofusStream stream) {
		this.error = FightJoinError.valueOf(stream.readChar());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeChar(error.getCode());	
	}
	/**
	 * @return the error
	 */
	public FightJoinError getError() {
		return error;
	}/**
	 * @param error the error to set
	 */
	public void setError(FightJoinError error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "GameJoinErrorAction [error=" + error + "]";
	}

}
