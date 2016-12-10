package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

public abstract class GameAction {
	private boolean server;

	public abstract void readClient(DofusStream stream);

	public abstract void writeClient(DofusStream stream);

	public abstract void readServer(DofusStream stream);

	public abstract void writeServer(DofusStream stream);

	public void read(DofusStream stream) {
		if (isServer()) readServer(stream);
		else readClient(stream);
	}

	public void write(DofusStream stream) {
		if (isServer()) writeServer(stream);
		else writeClient(stream);
	}

	public GameAction setIsServer(boolean srv) {
		this.server = srv;
		return this;
	}

	protected String toDofusId(int id) {
		String bef = "";
		if (id < 10) bef = "00";
		else if (id < 100) bef = "0";
		return bef + id;
	}

	/**
	 * @return the server
	 */
	public boolean isServer() {
		return server;
	}
}
