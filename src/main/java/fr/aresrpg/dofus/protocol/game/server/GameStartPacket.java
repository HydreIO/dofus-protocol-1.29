package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

/**
 * 
 * @since
 */
public class GameStartPacket implements ServerPacket {

	@Override
	public String toString() {
		return "GameStartPacket()[" + getId() + "]";
	}

	@Override
	public void read(DofusStream stream) {}

	@Override
	public void write(DofusStream stream) {}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}


}
