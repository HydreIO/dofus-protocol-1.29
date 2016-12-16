package fr.aresrpg.dofus.structures;

/**
 * 
 * @since
 */
public enum PartyErrorReason {

	ALREADY_IN_GROUP('a'),
	FULL('f'),
	UNKNOW_PLAYER('n');

	private char c;

	/**
	 * @param c
	 */
	private PartyErrorReason(char c) {
		this.c = c;
	}

	/**
	 * @return the c
	 */
	public char getCode() {
		return c;
	}

	public static PartyErrorReason valueOf(char code) {
		if (code == 'a') return ALREADY_IN_GROUP;
		else if (code == 'f') return FULL;
		else if (code == 'n') return UNKNOW_PLAYER;
		else throw new IllegalArgumentException("The char '" + code + "' is invalid !");
	}

}
