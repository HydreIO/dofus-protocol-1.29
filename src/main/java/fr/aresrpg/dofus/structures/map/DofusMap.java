package fr.aresrpg.dofus.structures.map;

import fr.aresrpg.dofus.structures.character.Character;

import java.util.Set;

public class DofusMap {
	private int id;
	private int width;
	private int height;
	private int musicId;
	private int capabilities;
	private boolean outdoor;
	private int backgroundId;
	private int area;
	private int subarea;
	private int x, z;
	private Cell[] cells;
	private int[] ressources;
	private Set<Character> players;
	private Set<Mob> mobs;
	private Set<Npc> npcs;
	private Set<Percepteur> percos;

	public DofusMap(int id, int width, int height, int musicId, int capabilities, boolean outdoor, int backgroundId, Cell[] cells) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.musicId = musicId;
		this.capabilities = capabilities;
		this.outdoor = outdoor;
		this.backgroundId = backgroundId;
		this.cells = cells;
	}

	public int getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Cell[] getCells() {
		return cells;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the z
	 */
	public int getZ() {
		return z;
	}

	/**
	 * @return the area
	 */
	public int getArea() {
		return area;
	}

	/**
	 * @return the subarea
	 */
	public int getSubarea() {
		return subarea;
	}

	/**
	 * @return the backgroundId
	 */
	public int getBackgroundId() {
		return backgroundId;
	}

	/**
	 * @return the capabilities
	 */
	public int getCapabilities() {
		return capabilities;
	}

	/**
	 * @return the musicId
	 */
	public int getMusicId() {
		return musicId;
	}

	/**
	 * @return the mobs
	 */
	public Set<Mob> getMobs() {
		return mobs;
	}

	/**
	 * @return the npcs
	 */
	public Set<Npc> getNpcs() {
		return npcs;
	}

	/**
	 * @return the percos
	 */
	public Set<Percepteur> getPercos() {
		return percos;
	}

	/**
	 * @return the players
	 */
	public Set<Character> getPlayers() {
		return players;
	}

	/**
	 * @return the ressources
	 */
	public int[] getRessources() {
		return ressources;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof DofusMap)) return false;
		DofusMap map = (DofusMap) obj;
		return x == map.getX() && z == map.getZ() && area == map.getArea() && subarea == map.getSubarea();
	}
}
