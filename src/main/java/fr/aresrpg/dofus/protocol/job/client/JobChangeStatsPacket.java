package fr.aresrpg.dofus.protocol.job.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.job.Jobs;

/**
 * 
 * @since
 */
public class JobChangeStatsPacket implements ClientPacket {

	private Jobs job;
	private String params;
	private int minSlot;

	@Override
	public void read(DofusStream stream) {
		this.job = Jobs.valueOf(stream.readInt());
		this.params = stream.read();
		this.minSlot = stream.readInt();
	}

	/**
	 * @return the job
	 */
	public Jobs getJob() {
		return job;
	}

	/**
	 * @param job
	 *            the job to set
	 */
	public void setJob(Jobs job) {
		this.job = job;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @return the minSlot
	 */
	public int getMinSlot() {
		return minSlot;
	}

	/**
	 * @param minSlot
	 *            the minSlot to set
	 */
	public void setMinSlot(int minSlot) {
		this.minSlot = minSlot;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(3).writeInt(job.getJobId()).write(params).writeInt(minSlot);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "JobChangeStatsPacket [job=" + job + ", params=" + params + ", minSlot=" + minSlot + "]";
	}

}
