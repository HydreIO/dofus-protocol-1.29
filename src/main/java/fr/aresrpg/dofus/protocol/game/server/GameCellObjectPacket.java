package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class GameCellObjectPacket implements ServerPacket {

	@Override
	public void read(DofusStream stream) {

	}

	@Override
	public void write(DofusStream stream) {

	}
	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}
	
	

}
