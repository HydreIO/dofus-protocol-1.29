package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

public class GameTurnStartPacket implements ServerPacket {
	private int characterId;
	private int time;

	@Override
	public void read(DofusStream stream) {
		characterId = stream.readInt();
		time = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(characterId).writeInt(time);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public int getCharacterId() {
		return characterId;
	}

	public GameTurnStartPacket setCharacterId(int characterId) {
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
