package fr.aresrpg.dofus.structures.game;

public class FightEntity {
	private int id;
	private int lp;
	private int lpMax;
	private int ap;
	private int mp;

	public FightEntity(int id, int lp, int lpMax, int ap, int mp) {
		this.id = id;
		this.lp = lp;
		this.lpMax = lpMax;
		this.ap = ap;
		this.mp = mp;
	}

	public int getId() {
		return id;
	}

	public int getLp() {
		return lp;
	}

	public int getLpMax() {
		return lpMax;
	}

	public int getAp() {
		return ap;
	}

	public int getMp() {
		return mp;
	}

	@Override
	public String toString() {
		return "FightEntity{" +
				"id=" + id +
				", lp=" + lp +
				", lpMax=" + lpMax +
				", ap=" + ap +
				", mp=" + mp +
				'}';
	}
}
