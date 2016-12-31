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

public class GameTurnStartPacket implements ServerPacket {
	private long characterId;
	private int time;

	@Override
	public void read(DofusStream stream) {
		characterId = stream.readInt();
		time = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeLong(characterId).writeInt(time);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public long getCharacterId() {
		return characterId;
	}

	public GameTurnStartPacket setCharacterId(long characterId) {
		this.characterId = characterId;
		return this;
	}

	public int getTime() {
		return time;
	}

	public GameTurnStartPacket setTime(int time) {
		this.time = time;
		return this;
	}

	@Override
	public String toString() {
		return "GameTurnStartPacket(characterId=" + characterId +
				", time=" + time + ")[" + getId() + ']';
	}
}
