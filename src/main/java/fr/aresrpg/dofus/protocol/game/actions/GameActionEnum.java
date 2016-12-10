package fr.aresrpg.dofus.protocol.game.actions;

/**
 * 
 * @since
 */
public enum GameActionEnum {

	MOVE(1),
	HARVEST_RESSOURCE(500),
	DUEL_ASK(900),
	DUEL_CANCEL(902);
	private int id;

	private GameActionEnum(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static GameActionEnum fromId(int id) {
		for (GameActionEnum ga : values())
			if (ga.getId() == id) return ga;
		return null;
	}

}
