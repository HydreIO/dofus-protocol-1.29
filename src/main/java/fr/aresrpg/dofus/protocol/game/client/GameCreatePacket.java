/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.game.GameType;

public class GameCreatePacket implements ClientPacket {

	private GameType gameType;

	@Override
	public void read(DofusStream stream) {
		gameType = GameType.valueOf(stream.readInt());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(gameType.getType());
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	/**
	 * @return the gameType
	 */
	public GameType getGameType() {
		return gameType;
	}

	/**
	 * @param gameType
	 *            the gameType to set
	 */
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	@Override
	public String toString() {
		return "GameCreatePacket(gameType=" + gameType + ")[" + getId() + ']';
	}
}
