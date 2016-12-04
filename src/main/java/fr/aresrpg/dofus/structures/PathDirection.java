package fr.aresrpg.dofus.structures;

public enum PathDirection {
	DOWN_RIGHT,
	DOWN,
	DOWN_LEFT,
	LEFT,
	UP_LEFT,
	UP,
	UP_RIGHT,
	RIGHT;

	public static PathDirection valueOf(int code) {
		if(code >= 0 && code < values().length)
			return values()[code];
		else
			return null;
	}

}
