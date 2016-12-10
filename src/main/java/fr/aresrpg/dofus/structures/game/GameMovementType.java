package fr.aresrpg.dofus.structures.game;

/**
 * 
 * @since
 */
public enum GameMovementType {

	SHOW('+'),
	UPDATE('~'),
	REMOVE('-');

	private char value;

	private GameMovementType(char c) {
		this.value = c;
	}

	/**
	 * @return the value
	 */
	public char getValue() {
		return value;
	}

	public static GameMovementType fromChar(char c) {
		for (GameMovementType t : values())
			if (t.getValue() == c) return t;
		return null;
	}

}
