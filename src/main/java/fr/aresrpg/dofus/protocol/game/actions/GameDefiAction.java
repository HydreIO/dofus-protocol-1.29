package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

/**
 * 
 * @since
 */
public class GameDefiAction extends GameAction {

	private int attacker, target;
	private boolean cancel;

	public GameDefiAction(boolean canceling) {
		this.cancel = canceling;
	}

	@Override
	public void readClient(DofusStream stream) {
		this.target = Integer.parseInt(stream.read().substring(3));
	}

	@Override
	public void writeClient(DofusStream stream) {
		GameActionEnum ac = isCancel() ? GameActionEnum.DUEL_CANCEL : GameActionEnum.DUEL_ASK;
		stream.allocate(1).write(toDofusId(ac.getId()) + getTarget());
	}

	@Override
	public void readServer(DofusStream stream) {
		// TODO

	}

	@Override
	public void writeServer(DofusStream stream) {
		// TODO

	}

	/**
	 * @return the cancel
	 */
	public boolean isCancel() {
		return cancel;
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
