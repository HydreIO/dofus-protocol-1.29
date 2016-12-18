package fr.aresrpg.dofus.structures.job;

/**
 * 
 * @since
 */
public class JobInfo { // JX|2;100;581687;603573;-1;|25;53;72385;74388;75943;|24;48;56498;56874;59437;

	private Jobs job;
	private int lvl;
	private int xpMin, xp, xpMax;

	/**
	 * @param job
	 * @param lvl
	 * @param xpMin
	 * @param xp
	 * @param xpMax
	 */
	public JobInfo(Jobs job, int lvl, int xpMin, int xp, int xpMax) {
		this.job = job;
		this.lvl = lvl;
		this.xpMin = xpMin;
		this.xp = xp;
		this.xpMax = xpMax;
	}

	public static JobInfo parse(String data) {
		String[] datas = data.split(";");
		return new JobInfo(Jobs.valueOf(Integer.parseInt(datas[0])), Integer.parseInt(datas[1]), Integer.parseInt(datas[2]), Integer.parseInt(datas[3]), Integer.parseInt(datas[4]));
	}

	public String serialize() {
		return job.getJobId() + ";" + lvl + ";" + xpMin + ";" + xp + ";" + xpMax + ";";
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

	/**
	 * @return the xpMin
	 */
	public int getXpMin() {
		return xpMin;
	}

	/**
	 * @param xpMin
	 *            the xpMin to set
	 */
	public void setXpMin(int xpMin) {
		this.xpMin = xpMin;
	}

	/**
	 * @return the xp
	 */
	public int getXp() {
		return xp;
	}

	/**
	 * @param xp
	 *            the xp to set
	 */
	public void setXp(int xp) {
		this.xp = xp;
	}

	/**
	 * @return the xpMax
	 */
	public int getXpMax() {
		return xpMax;
	}

	/**
	 * @param xpMax
	 *            the xpMax to set
	 */
	public void setXpMax(int xpMax) {
		this.xpMax = xpMax;
	}

	@Override
	public String toString() {
		return "JobInfo [job=" + job + ", lvl=" + lvl + ", xpMin=" + xpMin + ", xp=" + xp + ", xpMax=" + xpMax + "]";
	}

}
