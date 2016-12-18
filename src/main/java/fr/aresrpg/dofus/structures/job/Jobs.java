package fr.aresrpg.dofus.structures.job;

/**
 * 
 * @since
 */
public enum Jobs {

	JOB_NULL(0),
	JOB_BASE(1),
	JOB_BUCHERON(2),
	JOB_F_EPEE(11),
	JOB_S_ARC(13),
	JOB_F_MARTEAU(14),
	JOB_CORDONIER(15),
	JOB_BIJOUTIER(16),
	JOB_F_DAGUE(17),
	JOB_S_BATON(18),
	JOB_S_BAGUETTE(19),
	JOB_F_PELLE(20),
	JOB_MINEUR(24),
	JOB_BOULANGER(25),
	JOB_ALCHIMISTE(26),
	JOB_TAILLEUR(27),
	JOB_PAYSAN(28),
	JOB_F_HACHES(31),
	JOB_PECHEUR(36),
	JOB_CHASSEUR(41),
	JOB_FM_DAGUE(43),
	JOB_FM_EPEE(44),
	JOB_FM_MARTEAU(45),
	JOB_FM_PELLE(46),
	JOB_FM_HACHES(47),
	JOB_SM_ARC(48),
	JOB_SM_BAGUETTE(49),
	JOB_SM_BATON(50),
	JOB_BOUCHER(56),
	JOB_POISSONNIER(58),
	JOB_F_BOUCLIER(60),
	JOB_CORDOMAGE(62),
	JOB_JOAILLOMAGE(63),
	JOB_COSTUMAGE(64),
	JOB_BRICOLEUR(65),
	JOB_JOAILLER(66),
	JOB_BIJOUTIER2(67);

	private int jobId;

	/**
	 * @param jobId
	 */
	private Jobs(int jobId) {
		this.jobId = jobId;
	}

	public static Jobs valueOf(int code) {
		for (Jobs j : values())
			if (j.jobId == code) return j;
		return null;
	}

	/**
	 * @return the jobId
	 */
	public int getJobId() {
		return jobId;
	}
}
