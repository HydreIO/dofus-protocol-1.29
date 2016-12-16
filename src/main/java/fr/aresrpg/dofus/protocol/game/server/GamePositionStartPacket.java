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
import fr.aresrpg.dofus.util.Crypt;

import java.util.Arrays;

/**
 *
 * @since
 */
public class GamePositionStartPacket implements ServerPacket {

	private int currentTeam;
	private int[] placesTeam0;
	private int[] placesTeam1;

	@Override
	public String toString() {
		return "GamePositionStartPacket(placeTeam0:" + Arrays.toString(placesTeam0) + "|placesTeam1:" + Arrays.toString(placesTeam1) + "|currentTeam:" + currentTeam + ")[" + getId() + "]";
	}

	/**
	 * @return the currentTeam
	 */
	public int getCurrentTeam() {
		return currentTeam;
	}

	/**
	 * @return the placesTeam0
	 */
	public int[] getPlacesTeam0() {
		return placesTeam0;
	}

	/**
	 * @return the placesTeam1
	 */
	public int[] getPlacesTeam1() {
		return placesTeam1;
	}

	@Override
	public void read(DofusStream stream) {
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
	public void write(DofusStream stream) {
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
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}


}
