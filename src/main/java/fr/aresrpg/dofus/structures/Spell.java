/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures;

public class Spell {
	private int id;
	private int level;
	private int position;

	public Spell(int id, int level, int position) {
		this.id = id;
		this.level = level;
		this.position = position;
	}

	public int getId() {
		return id;
	}

	public int getLevel() {
		return level;
	}

	public int getPosition() {
		return position;
	}

	@Override
	public String toString() {
		return "Spell [id=" + id + ", level=" + level + ", position=" + position + "]";
	}

}
