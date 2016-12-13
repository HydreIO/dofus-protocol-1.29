package fr.aresrpg.dofus.protocol.waypoint.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.structures.Waypoint;

import java.util.Arrays;

public class ZaapCreatePacket implements Packet{
	private int respawnWaypoint;
	private Waypoint[] waypoints;

	@Override
	public void read(DofusStream stream) {
		respawnWaypoint = stream.readInt();
		waypoints = new Waypoint[stream.available()];
		for(int i = 0 ; i < waypoints.length ; i++){
			String[] data = stream.read().split(";");
			waypoints[i] = new Waypoint(Integer.parseInt(data[0]) , Integer.parseInt(data[1]));
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1 + waypoints.length).writeInt(respawnWaypoint);
		for(Waypoint w : waypoints)
			stream.write(w.getId() + ";" + w.getCost());
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getRespawnWaypoint() {
		return respawnWaypoint;
	}

	public ZaapCreatePacket setRespawnWaypoint(int respawnWaypoint) {
		this.respawnWaypoint = respawnWaypoint;
		return this;
	}

	public Waypoint[] getWaypoints() {
		return waypoints;
	}

	public ZaapCreatePacket setWaypoints(Waypoint[] waypoints) {
		this.waypoints = waypoints;
		return this;
	}

	@Override
	public String toString() {
		return "WaypointCreatePacket(respawnWaypoint=" + respawnWaypoint +
				", waypoints=" + Arrays.toString(waypoints) +
				")[" + getId() + ']';
	}
}
