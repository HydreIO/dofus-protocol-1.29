package fr.aresrpg.dofus.protocol.dialog;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class DialogLeavePacket implements ClientPacket {

	@Override
	public void read(DofusStream stream) {
	}

	@Override
	public void write(DofusStream stream) {
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

}
