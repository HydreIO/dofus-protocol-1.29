package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

/**
 * 
 * @since
 */
public class LeaveGamePacket implements Packet {

	private String spriteId;

	@Override
	public void read(DofusStream stream) throws IOException {
		if (stream.available() > 0)
			this.spriteId = stream.read();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		if (spriteId != null) stream.allocate(1).write(spriteId);
	}

	@Override
	public String toString() {
		return "LeaveGamePacket(" + (spriteId == null ? "" : spriteId) + ")[" + getId() + "]";
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
