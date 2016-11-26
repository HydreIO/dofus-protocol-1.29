package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

/**
 * 
 * @since
 */
public class GamePositionStartPacket implements Packet {

	private int currentTeam;

	// WIP

	@Override
	public void read(DofusStream stream) throws IOException {
		String var4 = stream.read();
		String var5 = stream.read();
		this.currentTeam = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
