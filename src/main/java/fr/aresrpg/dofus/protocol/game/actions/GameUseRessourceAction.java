package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

public class GameUseRessourceAction implements GameAction{
	private int cellId;
	private int jobId;

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";");
		this.cellId = Integer.parseInt(data[0]);
		this.jobId = Integer.parseInt(data[1]);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(cellId + ";" + jobId);
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
