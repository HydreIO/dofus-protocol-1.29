package fr.aresrpg.dofus.protocol.tutorial.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class TutorialCreatePacket implements ServerPacket {

	private int tutoId;
	private long longid;

	@Override
	public void read(DofusStream stream) {
		this.tutoId = stream.readInt();
		this.longid = stream.readLong();
	}

	/**
	 * @return the tutoId
	 */
	public int getTutoId() {
		return tutoId;
	}

	/**
	 * @param tutoId
	 *            the tutoId to set
	 */
	public void setTutoId(int tutoId) {
		this.tutoId = tutoId;
	}

	/**
	 * @return the longid
	 */
	public long getLongid() {
		return longid;
	}

	/**
	 * @param longid
	 *            the longid to set
	 */
	public void setLongid(long longid) {
		this.longid = longid;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(tutoId).writeLong(longid);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "TutorialCreatePacket [tutoId=" + tutoId + ", longid=" + longid + "]";
	}

}
