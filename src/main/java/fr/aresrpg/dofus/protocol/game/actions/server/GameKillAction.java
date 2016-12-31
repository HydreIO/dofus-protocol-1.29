/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

/**
 * 
 * @since
 */
public class GameKillAction implements GameAction {

	private long killed;

	public GameKillAction() {
	}

	/**
	 * @param killed
	 */
	public GameKillAction(long killed) {
		this.killed = killed;
	}

	/**
	 * @return the killed
	 */
	public long getKilled() {
		return killed;
	}

	/**
	 * @param killed
	 *            the killed to set
	 */
	public void setKilled(long killed) {
		this.killed = killed;
	}

	@Override
	public void read(DofusStream stream) {
		this.killed = stream.readLong();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeLong(killed);
	}

	@Override
	public String toString() {
		return "GameKillAction [killed=" + killed + "]";
	}

}
