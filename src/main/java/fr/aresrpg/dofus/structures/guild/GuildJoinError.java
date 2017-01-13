package fr.aresrpg.dofus.structures.guild;

/**
 * 
 * @since
 */
public enum GuildJoinError {

	ALREADY_IN_GUILD('a'),
	NO_RIGHTS('d'),
	UNKNOW('u'),
	OCCUPED('o'),
	REFUSED('r');

	private char code;

	private GuildJoinError(char code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public char getCode() {
		return code;
	}

	public static GuildJoinError valueOf(char code) {
		for (GuildJoinError e : values())
			if (e.getCode() == code) return e;
		return null;
	}

}
