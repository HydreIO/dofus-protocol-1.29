/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

public class GameActionStartPacket implements ServerPacket {
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
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
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
