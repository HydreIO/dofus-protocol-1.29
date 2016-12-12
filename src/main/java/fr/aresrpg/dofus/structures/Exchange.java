package fr.aresrpg.dofus.structures;

public enum Exchange {
	PLAYER_INVENTORY(1),
	BANK(5);
	private final int code;

	Exchange(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Exchange valueOf(int code) {
		for (Exchange c : values())
			if (c.code == code)
				return c;
		return null;
	}
}
