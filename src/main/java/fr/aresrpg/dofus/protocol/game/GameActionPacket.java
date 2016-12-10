package fr.aresrpg.dofus.protocol.game;

import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

public interface GameActionPacket extends Packet{

	GameActionPacket setAction(GameAction action);

	GameAction getAction();

}
