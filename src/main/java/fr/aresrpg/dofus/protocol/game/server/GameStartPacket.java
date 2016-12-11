package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

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
	public void read(DofusStream stream) {}

	@Override
	public void write(DofusStream stream) {}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
