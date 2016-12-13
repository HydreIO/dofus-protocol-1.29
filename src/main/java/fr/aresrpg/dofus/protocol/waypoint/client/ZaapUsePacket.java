package fr.aresrpg.dofus.protocol.waypoint.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class ZaapUsePacket implements Packet{
	private int waypointId;

	@Override
	public void read(DofusStream stream) {
		waypointId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(waypointId);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getWaypointId() {
		return waypointId;
	}

	public ZaapUsePacket setWaypointId(int waypointId) {
		this.waypointId = waypointId;
		return this;
	}

	@Override
	public String toString() {
		return "WaypointUsePacket{" +
				"waypointId=" + waypointId +
				'}';
	}
}
