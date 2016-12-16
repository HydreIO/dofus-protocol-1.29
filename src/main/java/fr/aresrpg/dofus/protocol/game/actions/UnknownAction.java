/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;

import java.util.Arrays;

public class UnknownAction implements GameAction {
	private int id[];
	private String[] data;

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

	/**
	 * @return the id
	 */
	public int[] getId() {
		return id;
	}

	public UnknownAction setId(int... id) {
		this.id = id;
		return this;
	}

	@Override
	public String toString() {
		return "UnknownAction [id=" + Arrays.toString(id) + ", data=" + Arrays.toString(data) + "]";
	}

}