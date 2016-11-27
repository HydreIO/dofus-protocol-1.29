package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.protocol.util.Crypt;

import java.io.IOException;

/**
 *
 * @since
 */
public class GamePositionStartPacket implements Packet {

	private int currentTeam;
	private int[] placesTeam0;
	private int[] placesTeam1;

	@Override
	public void read(DofusStream stream) throws IOException {
		this.placesTeam0 = readTeam(stream.read());
		this.placesTeam1 = readTeam(stream.read());
		this.currentTeam = stream.readInt();
	}

	private static int[] readTeam(String data) {
		int[] team = new int[data.length()/2];
		for (int i = 0; i < data.length(); i += 2)
			team[i/2] = (Crypt.indexOfHash(data.charAt(i)) << 6) +
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
		for(int place : team){
			char hash = Crypt.hashToIndex(place);
			sb.append((char)(hash >> 6)).append((char)(hash & 0x3F));
		}
		return sb.toString();
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
