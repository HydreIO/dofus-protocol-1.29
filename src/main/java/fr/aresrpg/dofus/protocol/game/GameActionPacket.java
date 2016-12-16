/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game;

import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

public interface GameActionPacket extends Packet{

	GameActionPacket setAction(GameAction action);

	GameAction getAction();

}
