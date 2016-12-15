package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Skills;

/**
 * 
 * @since
 */
public class ItemToolPacket implements ServerPacket {

	private Skills jobId;

	public ItemToolPacket() {
	}

	/**
	 * @param jobId
	 */
	public ItemToolPacket(Skills jobId) {
		super();
		this.jobId = jobId;
	}

	/**
	 * @return the jobId
	 */
	public Skills getJobId() {
		return jobId;
	}

	/**
	 * @param jobId
	 *            the jobId to set
	 */
	public void setJobId(Skills jobId) {
		this.jobId = jobId;
	}

	@Override
	public void read(DofusStream stream) {
		this.jobId = Skills.valueOf(stream.readInt());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(jobId.getId());
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ItemToolPacket [jobId=" + jobId + "]";
	}

}
