package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

import java.util.Arrays;

public class UnknownAction implements GameAction {
	private int id;
	private String[] data;

	@Override
	public void read(DofusStream stream) {
		data = new String[stream.available()];
		for (int i = 0; i < data.length; i++) data[i] = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(data.length);
		for (String s : data) stream.write(s);
	}

	public String[] getData() {
		return data;
	}

	@Override
	public int getId() {
		return id;
	}

	public UnknownAction setId(int id) {
		this.id = id;
		return this;
	}

	@Override
	public String toString() {
		return "UnknownAction{" +
				"id=" + id +
				", data=" + Arrays.toString(data) +
				'}';
	}
}