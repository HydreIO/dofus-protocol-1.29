package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.util.Crypt;

import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @since
 */
public class GamePositionStartPacket implements Packet {

	private int currentTeam;
	private int[] placesTeam0;
	private int[] placesTeam1;

	@Override
	public String toString() {
		return "GamePositionStartPacket(placeTeam0:" + Arrays.toString(placesTeam0) + "|placesTeam1:" + Arrays.toString(placesTeam1) + "|currentTeam:" + currentTeam + ")[" + getId() + "]";
	}

	@Override
	public void read(DofusStream stream) throws IOException {
		this.placesTeam0 = readTeam(stream.read());
		this.placesTeam1 = readTeam(stream.read());
		this.currentTeam = stream.readInt();
	}

	private static int[] readTeam(String data) {
		int[] team = new int[data.length() / 2];
		for (int i = 0; i < data.length(); i += 2)
			team[i / 2] = (Crypt.indexOfHash(data.charAt(i)) << 6) +
					Crypt.indexOfHash(data.charAt(i + 1));
		return team;
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(3).write(writeTeam(placesTeam0))
				.write(writeTeam(placesTeam1))
				.writeInt(currentTeam);
	}

	private static String writeTeam(int[] team) {
		StringBuilder sb = new StringBuilder(team.length * 2);
		for (int place : team)
			sb.append(Crypt.hashToIndex(place >> 6)).append(Crypt.hashToIndex(place & 0x3F));
		return sb.toString();
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
