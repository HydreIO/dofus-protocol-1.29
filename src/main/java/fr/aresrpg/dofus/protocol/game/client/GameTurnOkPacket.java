package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

/**
 * 
 * @since
 */
public class GameTurnOkPacket implements ClientPacket {

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
		return "GameTurnOkPacket(" + (spriteId == null ? "" : spriteId) + ")[" + getId() + "]";
	}

	/**
	 * @return the spriteId
	 */
	public String getSpriteId() {
		return spriteId;
	}

	/**
	 * @param spriteId
	 *            the spriteId to set
	 */
	public void setSpriteId(String spriteId) {
		this.spriteId = spriteId;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

}
