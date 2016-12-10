package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountSelectCharacterPacket implements Packet {
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
		handler.handle(this);
	}

	public int getCharacterId() {
		return characterId;
	}

	public AccountSelectCharacterPacket setCharacterId(int characterId) {
		this.characterId = characterId;
		return this;
	}

	@Override
	public String toString() {
		return "AccountSelectCharacterPacket(" + "characterId:" + characterId + ")[" + getId() + "]";
	}
}
