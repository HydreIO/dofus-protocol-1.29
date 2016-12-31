package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

/**
 * 
 * @since
 */
public class GameDuelServerAction implements GameAction {

	private long targetId;

	@Override
	public void read(DofusStream stream) {
		this.targetId = stream.readLong();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeLong(targetId);
	}

	/**
	 * @return the targetId
	 */
	public long getTargetId() {
		return targetId;
	}

	/**
	 * @param targetId
	 *            the targetId to set
	 */
	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}

	@Override
	public String toString() {
		return "GameDuelServerAction [targetId=" + targetId + "]";
	}
}
