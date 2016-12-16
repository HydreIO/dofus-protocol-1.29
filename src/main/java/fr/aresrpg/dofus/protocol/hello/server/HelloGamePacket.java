/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.hello.server;

import fr.aresrpg.dofus.protocol.*;

public class HelloGamePacket implements ServerPacket {

	@Override
	public void read(DofusStream stream) {
	}

	@Override
	public void write(DofusStream stream) {
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "HelloGamePacket()[" + getId() + "]";
	}
}
