package fr.aresrpg.dofus.structures;

public enum Alignment {
	NEUTRE(-1),
	BONTA(1),
	BARAKMAR(2),
	MERCENAIRE(3);

	private int code;

	private Alignment(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	public static Alignment valueOf(int code) {
		for (Alignment a : values())
			if (a.getCode() == code) return a;
		return null;
	}
}
