package fr.aresrpg.dofus.structures.map;

/**
 * 
 * @since
 */
public class Mob {

	private int id;
	private int sprite;
	private int cellId;
	private int lvl;
	private int stars;

	public Mob(int id, int sprite, int cellid, int lvl, int stars) {
		this.id = id;
		this.sprite = sprite;
		this.cellId = cellid;
		this.lvl = lvl;
		this.stars = stars;
	}

	/**
	 * @return the cellId
	 */
	public int getCellId() {
		return cellId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @return the sprite
	 */
	public int getSprite() {
		return sprite;
	}

	/**
	 * @return the stars
	 */
	public int getStars() {
		return stars;
	}

}
