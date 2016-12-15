package fr.aresrpg.dofus.protocol.dialog.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class DialogPausePacket implements ServerPacket {

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
