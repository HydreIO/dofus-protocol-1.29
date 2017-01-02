/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.*;

public class AccountSelectCharacterPacket implements ClientPacket {
	private long characterId;

	@Override
	public void read(DofusStream stream) {
		characterId = stream.readLong();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeLong(characterId);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	public long getCharacterId() {
		return characterId;
	}

	public AccountSelectCharacterPacket setCharacterId(long characterId) {
		this.characterId = characterId;
		return this;
	}

	@Override
	public String toString() {
		return "AccountSelectCharacterPacket(" + "characterId:" + characterId + ")[" + getId() + "]";
	}
}
