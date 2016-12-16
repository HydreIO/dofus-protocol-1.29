/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures;

public class Waypoint {
	private int id; //Map id ?
	private int cost;

	public Waypoint(int id, int cost) {
		this.id = id;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public int getCost() {
		return cost;
	}

	@Override
	public String toString() {
		return "Waypoint{" +
				"id=" + id +
				", cost=" + cost +
				'}';
	}
}
