/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.movement;

import fr.aresrpg.dofus.structures.Orientation;
import fr.aresrpg.dofus.structures.item.Accessory;

import java.util.Arrays;

/**
 * 
 * @since
 */
public class MovementMonsterGroup implements MovementAction {

	private long id;
	private int[] entitytype;
	private int sprite;
	private int[] lvl;
	private int scaleX;
	private int scaleY;
	private boolean noFlip;
	private int cellid;
	private Orientation orientation;
	private int color1;
	private int color2;
	private int color3;
	private Accessory[] accessories;
	private int bonusValue;

	public MovementMonsterGroup(long id, int[] entitytype, int sprite, int[] lvl, int scaleX, int scaleY, boolean noFlip, int cellid, Orientation orientation, int color1, int color2,
		int color3,
		Accessory[] accessories,
		int bonusValue) {
		this.id = id;
		this.entitytype = entitytype;
		this.sprite = sprite;
		this.lvl = lvl;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.noFlip = noFlip;
		this.cellid = cellid;
		this.orientation = orientation;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.accessories = accessories;
		this.bonusValue = bonusValue;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		return obj instanceof MovementMonsterGroup && ((MovementMonsterGroup) obj).getId() == getId();
	}

	/**
	 * @return the sprite
	 */
	public int getSprite() {
		return sprite;
	}

	/**
	 * @param sprite
	 *            the sprite to set
	 */
	public void setSprite(int sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the entitytype
	 */
	public int[] getEntitytype() {
		return entitytype;
	}

	/**
	 * @return the lvl
	 */
	public int[] getLvl() {
		return lvl;
	}

	/**
	 * @return the scaleX
	 */
	public int getScaleX() {
		return scaleX;
	}

	/**
	 * @param scaleX
	 *            the scaleX to set
	 */
	public void setScaleX(int scaleX) {
		this.scaleX = scaleX;
	}

	/**
	 * @return the scaleY
	 */
	public int getScaleY() {
		return scaleY;
	}

	/**
	 * @param scaleY
	 *            the scaleY to set
	 */
	public void setScaleY(int scaleY) {
		this.scaleY = scaleY;
	}

	/**
	 * @return the noFlip
	 */
	public boolean isNoFlip() {
		return noFlip;
	}

	/**
	 * @param noFlip
	 *            the noFlip to set
	 */
	public void setNoFlip(boolean noFlip) {
		this.noFlip = noFlip;
	}

	/**
	 * @return the cellid
	 */
	public int getCellId() {
		return cellid;
	}

	/**
	 * @param cellid
	 *            the cellid to set
	 */
	public void setCellId(int cellid) {
		this.cellid = cellid;
	}

	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation
	 *            the orientation to set
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * @return the color1
	 */
	public int getColor1() {
		return color1;
	}

	/**
	 * @param color1
	 *            the color1 to set
	 */
	public void setColor1(int color1) {
		this.color1 = color1;
	}

	/**
	 * @return the color2
	 */
	public int getColor2() {
		return color2;
	}

	/**
	 * @param color2
	 *            the color2 to set
	 */
	public void setColor2(int color2) {
		this.color2 = color2;
	}

	/**
	 * @return the color3
	 */
	public int getColor3() {
		return color3;
	}

	/**
	 * @param color3
	 *            the color3 to set
	 */
	public void setColor3(int color3) {
		this.color3 = color3;
	}

	/**
	 * @return the accessories
	 */
	public Accessory[] getAccessories() {
		return accessories;
	}

	/**
	 * @return the bonusValue
	 */
	public int getBonusValue() {
		return bonusValue;
	}

	/**
	 * @param bonusValue
	 *            the bonusValue to set
	 */
	public void setBonusValue(int bonusValue) {
		this.bonusValue = bonusValue;
	}

	@Override
	public String toString() {
		return "MovementCreateMonsterGroup [id=" + id + ", entitytype=" + Arrays.toString(entitytype) + ", sprite=" + sprite + ", lvl=" + Arrays.toString(lvl) + ", scaleX=" + scaleX + ", scaleY="
				+ scaleY + ", noFlip=" + noFlip + ", cellid=" + cellid + ", orientation=" + orientation + ", color1=" + color1 + ", color2=" + color2 + ", color3=" + color3 + ", accessories="
				+ Arrays.toString(accessories) + ", bonusValue=" + bonusValue + "]";
	}

}
