package fr.aresrpg.dofus.protocol.waypoint.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class ZaapUsePacket implements ClientPacket {
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
	public void handleClient(ClientPacketHandler handler) {
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
