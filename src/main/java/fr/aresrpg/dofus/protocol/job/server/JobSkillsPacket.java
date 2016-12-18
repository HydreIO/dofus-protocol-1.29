package fr.aresrpg.dofus.protocol.job.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.job.*;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 
 * @since
 */
public class JobSkillsPacket implements ServerPacket {

	private Job[] jobs;

	@Override
	public void read(DofusStream stream) {
		stream.read(); // remove empty
		this.jobs = new Job[stream.available()];
		IntStream.range(0, stream.available()).forEach(i -> {
			String[] loc6 = stream.read().split(";");
			int loc7 = Integer.parseInt(loc6[0]);
			String[] loc9 = loc6[1].split(",");
			Skill[] skills = new Skill[loc9.length];
			for (int loc10 = 0; loc10 < loc9.length; loc10++)
				skills[loc10] = Skill.parse(loc9[loc10]);
			this.jobs[i] = new Job(Jobs.valueOf(loc7), skills, null);
		});
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(jobs.length + 1).write("");
		Arrays.stream(jobs).forEach(j -> stream.write(j.getType().getJobId() + ";" + j.serializeSkills()));
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "JobSkillsPacket [jobs=" + Arrays.toString(jobs) + "]";
	}

}
