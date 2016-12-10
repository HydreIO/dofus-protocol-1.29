package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

public class GameUseRessourceAction extends GameAction {
	private int cellId;
	private int jobId;

	@Override
	public void readClient(DofusStream stream) {
		String[] data = stream.read().split(";");
		this.cellId = Integer.parseInt(data[0]);
		this.jobId = Integer.parseInt(data[1]);
	}

	@Override
	public void writeClient(DofusStream stream) {
		stream.allocate(1).write(cellId + ";" + jobId);
	}

	@Override
	public void readServer(DofusStream stream) {
		// TODO

	}

	@Override
	public void writeServer(DofusStream stream) {
		// TODO

	}

	public int getCellId() {
		return cellId;
	}

	public int getJobId() {
		return jobId;
	}

	public GameUseRessourceAction setCellId(int cellId) {
		this.cellId = cellId;
		return this;
	}

	public GameUseRessourceAction setJobId(int jobId) {
		this.jobId = jobId;
		return this;
	}

}
