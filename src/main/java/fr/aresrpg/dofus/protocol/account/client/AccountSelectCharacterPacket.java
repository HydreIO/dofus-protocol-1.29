package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

public class AccountSelectCharacterPacket implements Packet {
	private int characterId;

	@Override
	public void read(DofusStream stream) throws IOException {
		characterId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
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
