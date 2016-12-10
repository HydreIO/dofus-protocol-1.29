package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

public class GameErrorAction implements GameAction{
	@Override
	public void read(DofusStream stream) {}

	@Override
	public void write(DofusStream stream) {stream.allocate(1).write(""); }

	@Override
	public String toString() {
		return "GameErrorAction()[" + getId() + ']';
	}
}
