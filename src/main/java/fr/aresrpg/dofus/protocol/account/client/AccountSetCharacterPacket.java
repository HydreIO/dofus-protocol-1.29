package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class AccountSetCharacterPacket implements Packet{
	private int characterId;

	@Override
	public void read(DofusStream stream) throws IOException {
		this.characterId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).writeInt(characterId);
	}

	@Override
	public void handle(PacketHandler handler) {

	}
}
