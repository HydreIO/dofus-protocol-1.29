package fr.aresrpg.dofus.structures.game;

import java.util.ArrayList;
import java.util.List;

public class FightSpawn {
	private long id;
	private FightType type;
	private List<FightTeam> teams = new ArrayList<>();

	public FightSpawn(long id, FightType type) {
		this.id = id;
		this.type = type;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		return obj instanceof FightSpawn && ((FightSpawn) obj).getId() == id;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public FightType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(FightType type) {
		this.type = type;
	}

	/**
	 * @return the teams
	 */
	public List<FightTeam> getTeams() {
		return teams;
	}

	/**
	 * @param teams
	 *            the teams to set
	 */
	public void setTeams(List<FightTeam> teams) {
		this.teams = teams;
	}

	@Override
	public String toString() {
		return "FightSpawn [id=" + id + ", type=" + type + ", teams=" + teams + "]";
	}

}
