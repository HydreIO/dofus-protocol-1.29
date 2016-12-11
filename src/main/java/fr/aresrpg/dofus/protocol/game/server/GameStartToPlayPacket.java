package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class GameStartToPlayPacket implements Packet{
	@Override
	public void read(DofusStream stream) {}

	@Override
	public void write(DofusStream stream) {}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}
}
