/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Jobs;

/**
 * 
 * @since
 */
public class ItemToolPacket implements ServerPacket {

	private Jobs jobId;

	public ItemToolPacket() {
	}

	/**
	 * @param jobId
	 */
	public ItemToolPacket(Jobs jobId) {
		this.jobId = jobId;
	}

	/**
	 * @return the jobId
	 */
	public Jobs getJobId() {
		return jobId;
	}

	/**
	 * @param jobId
	 *            the jobId to set
	 */
	public void setJobId(Jobs jobId) {
		this.jobId = jobId;
	}

	@Override
	public void read(DofusStream stream) {
		this.jobId = Jobs.valueOf(stream.readInt());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(jobId.getJobId());
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
