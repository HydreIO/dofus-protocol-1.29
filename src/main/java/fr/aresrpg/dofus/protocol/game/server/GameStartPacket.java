package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

/**
 * 
 * @since
 */
public class GameStartPacket implements Packet {

	@Override
	public String toString() {
		return "GameStartPacket()[" + getId() + "]";
	}

	@Override
	public void read(DofusStream stream) throws IOException {
	}

	@Override
	public void write(DofusStream stream) throws IOException {
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
