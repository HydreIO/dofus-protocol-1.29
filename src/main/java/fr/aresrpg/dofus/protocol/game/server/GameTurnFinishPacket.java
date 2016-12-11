package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class GameTurnFinishPacket implements Packet{
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
	public void handle(PacketHandler handler) {
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
