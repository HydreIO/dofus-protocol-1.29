package fr.aresrpg.dofus.protocol.subway.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class SubwayUsePacket implements ClientPacket {

	private int mapid;

	@Override
	public void read(DofusStream stream) {
		this.mapid = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(mapid);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	/**
	 * @return the mapid
	 */
	public int getMapid() {
		return mapid;
	}

	/**
	 * @param mapid
	 *            the mapid to set
	 */
	public void setMapid(int mapid) {
		this.mapid = mapid;
	}

	@Override
	public String toString() {
		return "SubwayUsePacket [mapid=" + mapid + "]";
	}

}
