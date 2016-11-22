package fr.aresrpg.dofus.structures.server;

public class DofusServer {
	private final int id;
	private final ServerState state;
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
