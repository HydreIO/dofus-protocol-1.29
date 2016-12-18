package fr.aresrpg.dofus.structures.game;

/**
 * 
 * @since
 */
public enum FightEndEntityType {

	LOOSER(0),
	WINNER(2),
	PERCO(5),
	HEROIC_DEATH(6);

	private int code;

	private FightEndEntityType(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	public static FightEndEntityType valueOf(int code) {
		for (FightEndEntityType t : values())
			if (t.code == code) return t;
		return null;
	}

}
