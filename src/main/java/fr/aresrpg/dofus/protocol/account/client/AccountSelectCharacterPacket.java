/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class AccountSelectCharacterPacket implements ClientPacket {
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
	public void handleClient(ClientPacketHandler handler) {
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
