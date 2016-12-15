package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

/**
 * 
 * @since
 */
public class GameKillAction implements GameAction {

	private int killed;

	public GameKillAction() {
	}

	/**
	 * @param killed
	 */
	public GameKillAction(int killed) {
		super();
		this.killed = killed;
	}

	/**
	 * @return the killed
	 */
	public int getKilled() {
		return killed;
	}

	/**
	 * @param killed
	 *            the killed to set
	 */
	public void setKilled(int killed) {
		this.killed = killed;
	}

	@Override
	public void read(DofusStream stream) {
		this.killed = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(killed);
	}

	@Override
	public String toString() {
		return "GameKillAction [killed=" + killed + "]";
	}

}
