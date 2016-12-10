package fr.aresrpg.dofus.structures.game;

/**
 * 
 * @since
 */
public enum FightType {

	DUEL(0),
	AGRESSION(1),
	MONSTER(4),
	PERCO(5);

	private int id;

	private FightType(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static FightType fromId(int id) {
		for (FightType t : values())
			if (id == t.getId()) return t;
		return null;
	}

}
