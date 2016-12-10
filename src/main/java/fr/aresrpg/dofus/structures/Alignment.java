package fr.aresrpg.dofus.structures;

public enum Alignment {
	BONTA,
	BARAKMAR;

	public static Alignment valueOf(int code) {
		if(code >= 0 && code < values().length)
			return values()[code];
		else
			return null;
	}
}
