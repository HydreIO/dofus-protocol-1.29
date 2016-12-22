package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;
import fr.aresrpg.dofus.structures.game.JoinError;

/**
 * 
 * @since
 */
public class GameJoinErrorAction implements GameAction {

	private JoinError error;

	@Override
	public void read(DofusStream stream) {
		this.error = JoinError.fromValue(stream.readChar());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeChar(error.getValue());
	}

	/**
	 * @return the error
	 */
	public JoinError getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(JoinError error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "GameJoinErrorAction [error=" + error + "]";
	}

}
