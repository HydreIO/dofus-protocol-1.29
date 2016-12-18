package fr.aresrpg.dofus.structures.job;

import fr.aresrpg.dofus.structures.Skills;

import java.util.StringJoiner;

/**
 * 
 * @since
 */
public class Skill {

	private int skillId, param1, param2, param3, param4;

	/**
	 * @param type
	 * @param param1
	 * @param param2
	 * @param param3
	 * @param param4
	 */
	public Skill(int skillId, int param1, int param2, int param3, int param4) {
		this.skillId = skillId;
		this.param1 = param1;
		this.param2 = param2;
		this.param3 = param3;
		this.param4 = param4;
	}

	public static Skill parse(String data) {
		String[] datas = data.split("~");
		int param1 = 0, param2 = 0, param3 = 0, param4 = 0;
		switch (datas.length) {
			case 5:
				if (datas[4] != null) param4 = Integer.parseInt(datas[4]);
			case 4:
				if (datas[3] != null) param3 = Integer.parseInt(datas[3]);
			case 3:
				if (datas[2] != null) param2 = Integer.parseInt(datas[2]);
			case 2:
				if (datas[1] != null) param1 = Integer.parseInt(datas[1]);
		}
		return new Skill(Integer.parseInt(datas[0]), param1, param2, param3, param4);
	}

	public String serialize() {
		return new StringJoiner("~").add(Integer.toString(getSkillId())).add(Integer.toString(param1)).add(Integer.toString(param2)).add(Integer.toString(param3)).add(Integer.toString(param4))
				.toString();
	}

	/**
	 * @return the type
	 */
	public Skills getType() {
		return Skills.valueOf(skillId);
	}

	/**
	 * @return the skillId
	 */
	public int getSkillId() {
		return skillId;
	}

	/**
	 * @param skillId
	 *            the skillId to set
	 */
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	/**
	 * @return the param1
	 */
	public int getParam1() {
		return param1;
	}

	/**
	 * @param param1
	 *            the param1 to set
	 */
	public void setParam1(int param1) {
		this.param1 = param1;
	}

	/**
	 * @return the param2
	 */
	public int getParam2() {
		return param2;
	}

	/**
	 * @param param2
	 *            the param2 to set
	 */
	public void setParam2(int param2) {
		this.param2 = param2;
	}

	/**
	 * @return the param3
	 */
	public int getParam3() {
		return param3;
	}

	/**
	 * @param param3
	 *            the param3 to set
	 */
	public void setParam3(int param3) {
		this.param3 = param3;
	}

	/**
	 * @return the param4
	 */
	public int getParam4() {
		return param4;
	}

	/**
	 * @param param4
	 *            the param4 to set
	 */
	public void setParam4(int param4) {
		this.param4 = param4;
	}

	@Override
	public String toString() {
		if (Skills.isHarvestSkill(getType())) return "Skill [skill=" + getType() + "(" + skillId + "), min=" + param1 + ", max=" + param2 + ", param3=" + param3 + ", millisecond=" + param4 + "]";
		else return "Skill [skill=" + getType() + "(" + skillId + "), param1=" + param1 + ", param2=" + param2 + ", param3=" + param3 + ", param4=" + param4 + "]";
	}

}
