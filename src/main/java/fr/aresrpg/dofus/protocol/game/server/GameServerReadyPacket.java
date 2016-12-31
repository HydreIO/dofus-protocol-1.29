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

public class GameServerReadyPacket implements ServerPacket {
	private boolean ready;
	private long entityId;

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
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}


	public boolean isReady() {
		return ready;
	}

	public GameServerReadyPacket setReady(boolean ready) {
		this.ready = ready;
		return this;
	}

	public long getEntityId() {
		return entityId;
	}

	public GameServerReadyPacket setEntityId(long entityId) {
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
