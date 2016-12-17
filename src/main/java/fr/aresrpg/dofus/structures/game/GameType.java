package fr.aresrpg.dofus.structures.game;

/**
 * 
 * @since
 */
public enum GameType {

	SOLO,
	FIGHT;

	public int getType() {
		return ordinal() + 1;
	}

	public static GameType valueOf(int code) {
		if (code == 1) return SOLO;
		else if (code == 2) return FIGHT;
		return null;
	}

}
