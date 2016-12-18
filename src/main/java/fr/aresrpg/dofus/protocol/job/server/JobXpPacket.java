package fr.aresrpg.dofus.protocol.job.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.job.JobInfo;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 
 * @since
 */
public class JobXpPacket implements ServerPacket { // JX|2;100;581687;603573;-1;|25;53;72385;74388;75943;|24;48;56498;56874;59437;

	private JobInfo[] infos;

	@Override
	public void read(DofusStream stream) {
		stream.read(); // remove empty
		infos = new JobInfo[stream.available()];
		IntStream.range(0, stream.available()).forEach(i -> infos[i] = JobInfo.parse(stream.read()));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(infos.length + 1).write("");
		Arrays.stream(infos).forEach(i -> stream.write(i.serialize()));
	}

	/**
	 * @return the infos
	 */
	public JobInfo[] getInfos() {
		return infos;
	}

	/**
	 * @param infos
	 *            the infos to set
	 */
	public void setInfos(JobInfo[] infos) {
		this.infos = infos;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "JobXpPacket [infos=" + Arrays.toString(infos) + "]";
	}

}
