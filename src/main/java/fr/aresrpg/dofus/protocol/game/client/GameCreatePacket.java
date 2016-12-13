package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class GameCreatePacket implements ClientPacket {
	public static final int TYPE_SOLO = 1;

	private int gameType;

	@Override
	public void read(DofusStream stream) {
		gameType = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(gameType);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	public int getGameType() {
		return gameType;
	}

	public GameCreatePacket setGameType(int gameType) {
		this.gameType = gameType;
		return this;
	}

	@Override
	public String toString() {
		return "GameCreatePacket(gameType=" + gameType + ")[" + getId() + ']';
	}
}
