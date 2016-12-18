package fr.aresrpg.dofus.structures.game;

/**
 * 
 * @since
 */
public enum FightJoinError {

	UNAVAILABLE('t'),
	DENIED('f');

	private char code;

	private FightJoinError(char c) {
		this.code = c;
	}

	/**
	 * @return the code
	 */
	public char getCode() {
		return code;
	}

	public static FightJoinError valueOf(char code) {
		if (code == 't') return UNAVAILABLE;
		else if (code == 'f') return DENIED;
		else throw new IllegalArgumentException("The char '" + code + "' is invalid !");
	}

}
