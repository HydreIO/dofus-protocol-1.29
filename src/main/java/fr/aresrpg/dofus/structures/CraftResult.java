package fr.aresrpg.dofus.structures;

/**
 * 
 * @since
 */
public enum CraftResult {

	NO_RESULT('I'),
	FAILED('F'),
	SUCCESS(';'); // oui je sais c'est dofus qui trouve intelligent de pas mettre de char mais de raccourcir le packet en cas de réussite

	private char code;

	/**
	 * @param code
	 */
	private CraftResult(char code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public char getCode() {
		return code;
	}

	public static CraftResult valueOf(char code) {
		if (code == 'I') return NO_RESULT;
		else if (code == 'F') return FAILED;
		else if (code == ';') return SUCCESS;
		throw new IllegalArgumentException("The char '" + code + "' is invalid");
	}

}
