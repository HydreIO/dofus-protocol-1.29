/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.map;

public class Frame {
	private int id;
	private Boolean interactive; //can be null

	public Frame(int id, Boolean interactive) {
		this.id = id;
		this.interactive = interactive;
	}

	public int getId() {
		return id;
	}

	public Boolean isInteractive() {
		return interactive;
	}

	@Override
	public String toString() {
		return "Frame(" +
				"id=" + id +
				(interactive == null ? "" : ", interactive=" + interactive) +
				')';
	}
}
