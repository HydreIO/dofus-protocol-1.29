/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

/**
 * 
 * @since
 */
public class GameDuelAction implements GameAction {

	private long targetId;

	/**
	 * @param targetId
	 */
	public GameDuelAction(long targetId) {
		this.targetId = targetId;
	}

	public GameDuelAction() {
	}

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
		return "GameDuelAction [targetId=" + targetId + "]";
	}

}
