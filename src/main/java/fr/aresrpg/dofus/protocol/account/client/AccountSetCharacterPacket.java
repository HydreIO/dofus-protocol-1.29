package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class AccountSetCharacterPacket implements ClientPacket {
	private int characterId;

	@Override
	public void read(DofusStream stream) {
		this.characterId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(characterId);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "AccountSetCharacterPacket(characterid:" + characterId + ")[" + getId() + "]";
	}
}
