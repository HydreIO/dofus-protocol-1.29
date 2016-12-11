package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountSetCharacterPacket implements Packet {
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
	public void handle(PacketHandler handler) {

	}

	@Override
	public String toString() {
		return "AccountSetCharacterPacket(characterid:" + characterId + ")[" + getId() + "]";
	}
}
