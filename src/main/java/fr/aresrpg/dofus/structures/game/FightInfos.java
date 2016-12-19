package fr.aresrpg.dofus.structures.game;

import fr.aresrpg.dofus.util.StringJoiner;

/**
 * 
 * @since
 */
public class FightInfos {

	private int id;
	private int duration;
	private int team0Type, team0Alignment, team0Count;
	private int team1Type, team1Alignment, team1Count;

	public static FightInfos parse(String data) {
		String[] loc6 = data.split(";");
		int fid = Integer.parseInt(loc6[0]);
		int time = Integer.parseInt(loc6[1]);
		FightInfos inf = new FightInfos(fid, /* loc9 */time); //  var _loc9 = _loc8 == -1 ? (-1) : (this.api.kernel.NightManager.getDiffDate(_loc8));
		String[] loc11 = loc6[2].split(",");
		int type0 = Integer.parseInt(loc11[0]);
		int align0 = Integer.parseInt(loc11[1]);
		int count0 = Integer.parseInt(loc11[2]);
		inf.addTeam(0, type0, align0, count0);
		String[] loc15 = loc6[3].split(",");
		int type1 = Integer.parseInt(loc15[0]);
		int align1 = Integer.parseInt(loc15[1]);
		int count1 = Integer.parseInt(loc15[2]);
		inf.addTeam(1, type1, align1, count1);
		return inf;
	}

	public String serialize() {
		StringJoiner fj = new StringJoiner(";");
		fj.add(id).add(duration);
		StringJoiner t0j = new StringJoiner(",");
		StringJoiner t1j = new StringJoiner(",");
		t0j.add(team0Type).add(team0Alignment).add(team0Count);
		t1j.add(team1Type).add(team1Alignment).add(team1Count);
		return fj.add(t0j.toString()).add(t1j.toString()).toString();
	}

	/**
	 * @param id
	 * @param duration
	 */
	public FightInfos(int id, int duration) {
		this.id = id;
		this.duration = duration;
	}

	public void addTeam(int index, int type, int alignment, int count) {
		if (index == 0) {
			this.team0Type = type;
			this.team0Alignment = alignment;
			this.team0Count = count;
		} else if (index == 1) {
			this.team1Type = type;
			this.team1Alignment = alignment;
			this.team1Count = count;
		}
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the team0Type
	 */
	public int getTeam0Type() {
		return team0Type;
	}

	/**
	 * @param team0Type
	 *            the team0Type to set
	 */
	public void setTeam0Type(int team0Type) {
		this.team0Type = team0Type;
	}

	/**
	 * @return the team0Alignment
	 */
	public int getTeam0Alignment() {
		return team0Alignment;
	}

	/**
	 * @param team0Alignment
	 *            the team0Alignment to set
	 */
	public void setTeam0Alignment(int team0Alignment) {
		this.team0Alignment = team0Alignment;
	}

	/**
	 * @return the team0Count
	 */
	public int getTeam0Count() {
		return team0Count;
	}

	/**
	 * @param team0Count
	 *            the team0Count to set
	 */
	public void setTeam0Count(int team0Count) {
		this.team0Count = team0Count;
	}

	/**
	 * @return the team1Type
	 */
	public int getTeam1Type() {
		return team1Type;
	}

	/**
	 * @param team1Type
	 *            the team1Type to set
	 */
	public void setTeam1Type(int team1Type) {
		this.team1Type = team1Type;
	}

	/**
	 * @return the team1Alignment
	 */
	public int getTeam1Alignment() {
		return team1Alignment;
	}

	/**
	 * @param team1Alignment
	 *            the team1Alignment to set
	 */
	public void setTeam1Alignment(int team1Alignment) {
		this.team1Alignment = team1Alignment;
	}

	/**
	 * @return the team1Count
	 */
	public int getTeam1Count() {
		return team1Count;
	}

	/**
	 * @param team1Count
	 *            the team1Count to set
	 */
	public void setTeam1Count(int team1Count) {
		this.team1Count = team1Count;
	}

	@Override
	public String toString() {
		return "FightInfos [id=" + id + ", duration=" + duration + ", team0Type=" + team0Type + ", team0Alignment=" + team0Alignment + ", team0Count=" + team0Count + ", team1Type=" + team1Type
				+ ", team1Alignment=" + team1Alignment + ", team1Count=" + team1Count + "]";
	}

}
