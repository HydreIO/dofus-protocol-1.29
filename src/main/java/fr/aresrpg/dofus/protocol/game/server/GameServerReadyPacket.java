package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class GameServerReadyPacket implements Packet{
	private boolean ready;
	private int entityId;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		ready = data.charAt(0) == '1';
		entityId = Integer.parseInt(data.substring(1));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write((ready ? "1" : "0") + entityId);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public boolean isReady() {
		return ready;
	}

	public GameServerReadyPacket setReady(boolean ready) {
		this.ready = ready;
		return this;
	}

	public int getEntityId() {
		return entityId;
	}

	public GameServerReadyPacket setEntityId(int entityId) {
		this.entityId = entityId;
		return this;
	}

	@Override
	public String toString() {
		return "GameServerReadyPacket(ready=" + ready +
				", entityId=" + entityId +
				")[" + getId() + ']';
	}
}
