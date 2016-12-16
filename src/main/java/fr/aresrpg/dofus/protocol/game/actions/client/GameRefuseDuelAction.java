/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

/**
 * 
 * @since
 */
public class GameRefuseDuelAction implements GameAction {

	private int targetId;

	/**
	 * @param targetId
	 */
	public GameRefuseDuelAction(int targetId) {
		this.targetId = targetId;
	}

	public GameRefuseDuelAction() {
	}

	@Override
	public void read(DofusStream stream) {
		this.targetId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(targetId);
	}

	/**
	 * @return the targetId
	 */
	public int getTargetId() {
		return targetId;
	}

	/**
	 * @param targetId
	 *            the targetId to set
	 */
	public void setTargetId(int targetId) {
		this.targetId = targetId;
	}

	@Override
	public String toString() {
		return "GameRefuseDuelAction [targetId=" + targetId + "]";
	}

}
