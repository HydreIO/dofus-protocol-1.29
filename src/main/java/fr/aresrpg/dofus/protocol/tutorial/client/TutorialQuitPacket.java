package fr.aresrpg.dofus.protocol.tutorial.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Orientation;

/**
 * 
 * @since
 */
public class TutorialQuitPacket implements ClientPacket {

	private int successId;
	private int cellid = -1;
	private Orientation orientation;

	@Override
	public void read(DofusStream stream) {
		this.successId = stream.readInt();
		if (stream.available() > 0) {
			this.cellid = stream.readInt();
			this.orientation = Orientation.valueOf(stream.readInt());
		}
	}

	/**
	 * @return the successId
	 */
	public int getSuccessId() {
		return successId;
	}

	/**
	 * @param successId
	 *            the successId to set
	 */
	public void setSuccessId(int successId) {
		this.successId = successId;
	}

	/**
	 * @return the cellid
	 */
	public int getCellid() {
		return cellid;
	}

	/**
	 * @param cellid
	 *            the cellid to set
	 */
	public void setCellid(int cellid) {
		this.cellid = cellid;
	}

	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation
	 *            the orientation to set
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	@Override
	public void write(DofusStream stream) {
		boolean full = cellid != -1;
		stream.allocate(full ? 3 : 1).writeInt(successId);
		if (full) stream.writeInt(cellid).writeInt(orientation.ordinal());
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "TutorialQuitPacket [successId=" + successId + ", cellid=" + cellid + ", orientation=" + orientation + "]";
	}

}
