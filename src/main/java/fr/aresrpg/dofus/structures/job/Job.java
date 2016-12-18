package fr.aresrpg.dofus.structures.job;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 
 * @since
 */
public class Job {

	private Jobs type;
	private Skill[] skills;
	private String option;

	/**
	 * @param type
	 * @param skills
	 * @param option
	 */
	public Job(Jobs type, Skill[] skills, String option) {
		super();
		this.type = type;
		this.skills = skills;
		this.option = option;
	}

	public String serializeSkills() {
		StringJoiner joiner = new StringJoiner(",");
		Arrays.stream(skills).forEach(s -> joiner.add(s.serialize()));
		return joiner.toString();
	}

	/**
	 * @return the type
	 */
	public Jobs getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Jobs type) {
		this.type = type;
	}

	/**
	 * @return the skills
	 */
	public Skill[] getSkills() {
		return skills;
	}

	/**
	 * @param skills
	 *            the skills to set
	 */
	public void setSkills(Skill[] skills) {
		this.skills = skills;
	}

	/**
	 * @return the option
	 */
	public String getOption() {
		return option;
	}

	/**
	 * @param option
	 *            the option to set
	 */
	public void setOption(String option) {
		this.option = option;
	}

	@Override
	public String toString() {
		return "Job [type=" + type + ", skills=" + Arrays.toString(skills) + ", option=" + option + "]";
	}

}
