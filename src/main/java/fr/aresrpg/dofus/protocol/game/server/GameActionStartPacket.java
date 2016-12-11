package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class GameActionStartPacket implements Packet{
	private int characterId;

	@Override
	public void read(DofusStream stream) {
		characterId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(characterId);
	}

	@Override
	public void handle(PacketHandler handler) {

	}

	public int getCharacterId() {
		return characterId;
	}

	public GameActionStartPacket setCharacterId(int characterId) {
		this.characterId = characterId;
		return this;
	}

	@Override
	public String toString() {
		return "GameActionStartPacket(characterId=" + characterId +
				")[" + getId() + ']';
	}
}