/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;

import java.util.Arrays;

public class GameTurnListPacket implements ServerPacket {
	private long[] turns;

	@Override
	public void read(DofusStream stream) {
		stream.read(); //Skip separator
		turns = new long[stream.available()];
		for (int i = 0; i < turns.length; i++)
			turns[i] = stream.readLong();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(turns.length + 1).write(""); //Separator
		for (long turn : turns)
			stream.writeLong(turn);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public long[] getTurns() {
		return turns;
	}

	public GameTurnListPacket setTurns(long[] turns) {
		this.turns = turns;
		return this;
	}

	@Override
	public String toString() {
		return "GameTurnListPacket(turns=" + Arrays.toString(turns) +
				")[" + getId() + ']';
	}
}
