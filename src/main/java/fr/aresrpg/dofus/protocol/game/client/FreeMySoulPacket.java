package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

/**
 * 
 * @since
 */
public class FreeMySoulPacket implements Packet {

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

	@Override
	public String toString() {
		return "FreeMySoulPacket()[" + getId() + "]";
	}

}
