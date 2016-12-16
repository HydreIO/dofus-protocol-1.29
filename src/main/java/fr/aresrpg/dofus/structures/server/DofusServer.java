/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.server;

public class DofusServer {
	private final int id;
	private ServerState state;
	private final int serverPopulation;
	private final boolean needSubscription;

	public DofusServer(int id, ServerState state, int serverPopulation, boolean needSubscription) {
		this.id = id;
		this.state = state;
		this.serverPopulation = serverPopulation;
		this.needSubscription = needSubscription;
	}

	public int getId() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		return obj instanceof DofusServer && ((DofusServer) obj).getId() == id;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(ServerState state) {
		this.state = state;
	}

	public ServerState getState() {
		return state;
	}

	public int getServerPopulation() {
		return serverPopulation;
	}

	public boolean needSubscription() {
		return needSubscription;
	}

	@Override
	public String toString() {
		return "DofusServer[" +
				"id:" + id +
				"|state:" + state +
				"|serverPopulation:" + serverPopulation +
				"|needSubscription:" + needSubscription +
				']';
	}
}
