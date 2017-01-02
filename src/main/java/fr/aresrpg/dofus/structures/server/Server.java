/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
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

	public static Server fromName(String name) {
		if (name.equalsIgnoreCase("eratz")) return ERATZ;
		else if (name.equalsIgnoreCase("henual")) return HENUAL;
		return null;
	}

	public static Server fromId(int id) {
		if (id == 601) return ERATZ;
		else return Server.HENUAL;
	}
}
