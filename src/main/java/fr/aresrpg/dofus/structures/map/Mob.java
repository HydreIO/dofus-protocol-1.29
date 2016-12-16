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

/**
 * 
 * @since
 */
public class Mob {

	private int id;
	private int sprite;
	private int cellId;
	private int lvl;
	private int pa;
	private int pm;

	public Mob(int id, int sprite, int cellid, int lvl) {
		this.id = id;
		this.sprite = sprite;
		this.cellId = cellid;
		this.lvl = lvl;
	}

	/**
	 * @return the pa
	 */
	public int getPa() {
		return pa;
	}

	/**
	 * @return the pm
	 */
	public int getPm() {
		return pm;
	}

	/**
	 * @param pa
	 *            the pa to set
	 */
	public void setPa(int pa) {
		this.pa = pa;
	}

	/**
	 * @param pm
	 *            the pm to set
	 */
	public void setPm(int pm) {
		this.pm = pm;
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

	@Override
	public String toString() {
		return "Mob [id=" + id + ", sprite=" + sprite + ", cellId=" + cellId + ", lvl=" + lvl + ", pa=" + pa + ", pm=" + pm + "]";
	}

}
