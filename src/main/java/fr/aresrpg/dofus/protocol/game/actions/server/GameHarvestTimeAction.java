package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

/**
 * 
 * @since
 */
public class GameHarvestTimeAction implements GameAction {

	private long time;
	private int unknowId;

	@Override
	public void read(DofusStream stream) {
		String[] datas = stream.read().split(",");
		this.unknowId = Integer.parseInt(datas[0]);
		this.time = Integer.parseInt(datas[1]);
	}

	/**
	 * @return the time
	 */
	public long getTime() {
		return time;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(long time) {
		this.time = time;
	}

	/**
	 * @return the unknowId
	 */
	public int getUnknowId() {
		return unknowId;
	}

	/**
	 * @param unknowId
	 *            the unknowId to set
	 */
	public void setUnknowId(int unknowId) {
		this.unknowId = unknowId;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(unknowId + "," + time);
	}

	@Override
	public String toString() {
		return "GameHarvestTimeAction [time=" + time + ", unknowId=" + unknowId + "]";
	}

}
