package fr.aresrpg.dofus.protocol.waypoint.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class ZaapUseErrorPacket implements Packet{
	@Override
	public void read(DofusStream stream) {}

	@Override
	public void write(DofusStream stream) {}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "WaypointUseErrorPacket()[" + getId() + ']';
	}
}
