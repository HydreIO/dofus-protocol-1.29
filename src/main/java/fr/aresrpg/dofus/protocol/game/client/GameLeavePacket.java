package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

/**
 * 
 * @since
 */
public class GameLeavePacket implements ClientPacket {

	private String spriteId;

	@Override
	public void read(DofusStream stream) {
		if (stream.available() > 0)
			this.spriteId = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		if (spriteId != null) stream.allocate(1).write(spriteId);
	}

	@Override
	public String toString() {
		return "GameLeavePacket(" + (spriteId == null ? "" : spriteId) + ")[" + getId() + "]";
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

}
