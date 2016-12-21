package fr.aresrpg.dofus.structures;

/**
 * 
 * @since
 */
public enum Emotes {

	SIT(1),

	;
	private int code;

	private Emotes(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	public static Emotes valueOf(int code) {
		for (Emotes e : values())
			if (e.getCode() == code) return e;
		return null;
	}

}
