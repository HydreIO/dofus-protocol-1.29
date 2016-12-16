/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

public class GameTurnFinishPacket implements ServerPacket {
	private int entityId;

	@Override
	public void read(DofusStream stream) {
		entityId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(entityId);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public int getEntityId() {
		return entityId;
	}

	public GameTurnFinishPacket setEntityId(int entityId) {
		this.entityId = entityId;
		return this;
	}

	@Override
	public String toString() {
		return "GameTurnFinishPacket(entityId=" + entityId +
				")[" + getId() + ']';
	}
}
