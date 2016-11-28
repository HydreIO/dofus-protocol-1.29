package fr.aresrpg.dofus.structures.server;

/**
 * 
 * @since
 */
public enum Server {

	ERATZ(601),
	HENUAL(602);

	private int id;

	private Server(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static Server fromId(int id) {
		if (id == 601) return ERATZ;
		else return Server.HENUAL;
	}
}
