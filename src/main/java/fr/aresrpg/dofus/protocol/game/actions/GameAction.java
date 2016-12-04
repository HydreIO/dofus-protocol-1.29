package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

public interface GameAction {
	void read(DofusStream stream);
	void write(DofusStream stream);
}
