package fr.aresrpg.dofus.protocol.subway.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Waypoint;

/**
 * 
 * @since
 */
public class SubwayCreatePacket implements ServerPacket {

	private int current;
	private Waypoint[] waypoints;

	@Override
	public void read(DofusStream stream) {
		current = stream.readInt();
		waypoints = new Waypoint[stream.available()];
		for (int i = 0; i < waypoints.length; i++) {
			String[] data = stream.read().split(";");
			waypoints[i] = new Waypoint(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1 + waypoints.length).writeInt(current);
		for (Waypoint w : waypoints)
			stream.write(w.getId() + ";" + w.getCost());
	}

	/**
	 * @return the current
	 */
	public int getCurrent() {
		return current;
	}

	/**
	 * @param current
	 *            the current to set
	 */
	public void setCurrent(int current) {
		this.current = current;
	}

	/**
	 * @return the waypoints
	 */
	public Waypoint[] getWaypoints() {
		return waypoints;
	}

	/**
	 * @param waypoints
	 *            the waypoints to set
	 */
	public void setWaypoints(Waypoint[] waypoints) {
		this.waypoints = waypoints;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

}
