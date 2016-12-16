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

import fr.aresrpg.dofus.structures.Alignment;

public class Subarea {
	private int id;
	private Alignment alignment;

	public Subarea(int id, Alignment alignment) {
		this.id = id;
		this.alignment = alignment;
	}

	public int getId() {
		return id;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	@Override
	public String toString() {
		return "Subarea{" +
				"id=" + id +
				", alignment=" + alignment +
				'}';
	}
}
