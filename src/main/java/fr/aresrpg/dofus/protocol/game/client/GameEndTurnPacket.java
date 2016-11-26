/*******************************************************************************
 * BotFather (C) - Dofus 1.29
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

/**
 * 
 * @since
 */
public class GameEndTurnPacket implements Packet {

	@Override
	public void read(DofusStream stream) throws IOException {

	}

	@Override
	public void write(DofusStream stream) throws IOException {

	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "GameEndTurnPacket()[" + getId() + "]";
	}

}
