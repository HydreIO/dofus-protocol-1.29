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

public class GameActionFinishPacket implements ServerPacket {
	private int ackId;
	private int characterId;

	@Override
	public void read(DofusStream stream) {
		ackId = stream.readInt();
		characterId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(ackId).writeInt(characterId);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}


	public int getAckId() {
		return ackId;
	}

	public GameActionFinishPacket setAckId(int ackId) {
		this.ackId = ackId;
		return this;
	}

	public int getCharacterId() {
		return characterId;
	}

	public GameActionFinishPacket setCharacterId(int characterId) {
		this.characterId = characterId;
		return this;
	}

	@Override
	public String toString() {
		return "GameActionFinishPacket(ackId=" + ackId +
				", characterId=" + characterId +
				")[" + getId() + ']';
	}
}
