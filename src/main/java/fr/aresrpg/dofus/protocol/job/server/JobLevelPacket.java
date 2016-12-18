package fr.aresrpg.dofus.protocol.job.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.job.Jobs;

/**
 * 
 * @since
 */
public class JobLevelPacket implements ServerPacket {

	private Jobs job;
	private int lvl;

	@Override
	public void read(DofusStream stream) {
		this.job = Jobs.valueOf(stream.readInt());
		this.lvl = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(job.getJobId()).writeInt(lvl);
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
	 * @return the lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl
	 *            the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "JobLevelPacket [job=" + job + ", lvl=" + lvl + "]";
	}

}
