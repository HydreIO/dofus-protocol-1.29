package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

public class GameTurnReadyPacket implements ServerPacket {
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

	public GameTurnReadyPacket setEntityId(int entityId) {
		this.entityId = entityId;
		return this;
	}

	@Override
	public String toString() {
		return "GameTurnReadyPacket(entityId=" + entityId +
				")[" + getId() + ']';
	}
}
