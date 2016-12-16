/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.fight.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class FightRestrictGroupPacket implements ClientPacket {

	@Override
	public void read(DofusStream stream) {
	}

	@Override
	public void write(DofusStream stream) {
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

}
