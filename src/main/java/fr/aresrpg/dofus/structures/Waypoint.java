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
