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
		String var4 = stream.read();
		String var5 = stream.read();
		this.currentTeam = stream.readInt();
		this.placesTeam0 = new int[var4.length() / 2];
		this.placesTeam1 = new int[var5.length() / 2];
		int i = 0;
		for (int var7 = 0; var7 < var4.length(); var7 += 2, i++)
			this.placesTeam0[i] = (Crypt.indexOfHash(var4.charAt(var7)) << 6) + Crypt.indexOfHash(var4.charAt(var7 + 1));
		i = 0;
		for (int var9 = 0; var9 < var5.length(); var9 += 2, i++)
			this.placesTeam1[i] = (Crypt.indexOfHash(var5.charAt(var9)) << 6) + Crypt.indexOfHash(var5.charAt(var9 + 1));
	}

	@Override
	public void write(DofusStream stream) throws IOException {
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
