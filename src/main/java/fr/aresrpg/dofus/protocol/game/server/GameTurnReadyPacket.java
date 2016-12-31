/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;

public class GameTurnReadyPacket implements ServerPacket {
	private long entityId;

	@Override
	public void read(DofusStream stream) {
		entityId = stream.readLong();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeLong(entityId);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public long getEntityId() {
		return entityId;
	}

	public GameTurnReadyPacket setEntityId(long entityId) {
		this.entityId = entityId;
		return this;
	}

	@Override
	public String toString() {
		return "GameTurnReadyPacket(entityId=" + entityId +
				")[" + getId() + ']';
	}
}
