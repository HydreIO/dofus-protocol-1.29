package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.structures.game.DuelResponse;

/**
 * 
 * @since
 */
public class GameDefiAction extends GameAction {

	private int attacker, target;
	private DuelResponse response;

	public GameDefiAction(DuelResponse response) {
		this.response = response;
	}

	@Override
	public void readClient(DofusStream stream) {
		this.target = Integer.parseInt(stream.read().substring(3));
	}

	@Override
	public void writeClient(DofusStream stream) {
		stream.allocate(1).write(toDofusId(getResponse().toAction().getId()) + getTarget());
	}

	@Override
	public void readServer(DofusStream stream) {
		String[] data = stream.read().split(";");
		this.attacker = Integer.parseInt(data[2]);
		this.target = Integer.parseInt(data[3]);
	}

	@Override
	public void writeServer(DofusStream stream) {
		stream.allocate(1).write(";" + toDofusId(getResponse().toAction().getId()) + ";" + getAttacker() + getTarget());
	}

	@Override
	public String toString() {
		return "GameDefiAction [attacker=" + attacker + ", target=" + target + ", response=" + response + "]";
	}

	/**
	 * @return the response
	 */
	public DuelResponse getResponse() {
		return response;
	}

	/**
	 * @return the attacker
	 */
	public int getAttacker() {
		return attacker;
	}

	/**
	 * @return the target
	 */
	public int getTarget() {
		return target;
	}

}
