package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

public class UnknownAction extends GameAction {
	private String[] data;

	@Override
	public void readClient(DofusStream stream) { // no need car on override direct read/write
	}

	@Override
	public void writeClient(DofusStream stream) {
	}

	@Override
	public void readServer(DofusStream stream) {
	}

	@Override
	public void writeServer(DofusStream stream) {
	}

	@Override
	public void read(DofusStream stream) {
		data = new String[stream.available()];
		for (int i = 0; i < data.length; i++)
			data[i] = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(data.length);
		for (String s : data)
			stream.write(s);
	}

	public String[] getData() {
		return data;
	}

}
