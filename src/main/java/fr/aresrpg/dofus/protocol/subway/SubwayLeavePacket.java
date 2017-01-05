package fr.aresrpg.dofus.protocol.subway;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class SubwayLeavePacket implements Packet {

	@Override
	public void read(DofusStream stream) {

	}

	@Override
	public void write(DofusStream stream) {

	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
