package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

import java.util.Arrays;

public class GameTurnListPacket implements ServerPacket {
	private int[] turns;

	@Override
	public void read(DofusStream stream) {
		stream.read(); //Skip separator
		turns = new int[stream.available()];
		for(int i = 0 ; i < turns.length ; i++)
			turns[i] = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(turns.length + 1).write(""); //Separator
		for(int turn : turns)
			stream.writeInt(turn);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public int[] getTurns() {
		return turns;
	}

	public GameTurnListPacket setTurns(int[] turns) {
		this.turns = turns;
		return this;
	}

	@Override
	public String toString() {
		return "GameTurnListPacket(turns=" + Arrays.toString(turns) +
				")[" + getId() + ']';
	}
}
