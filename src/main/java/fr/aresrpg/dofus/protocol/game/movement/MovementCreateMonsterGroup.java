package fr.aresrpg.dofus.protocol.game.movement;

import fr.aresrpg.dofus.structures.PathDirection;

import java.util.Arrays;

/**
 * 
 * @since
 */
public class MovementCreateMonsterGroup implements MovementAction {

	private int id;
	private int entitytype;
	private int sprite;
	private int lvl;
	private int scaleX;
	private int scaleY;
	private boolean noFlip;
	private int cellid;
	private PathDirection orientation;
	private int color1;
	private int color2;
	private int color3;
	private int[] accessories;
	private int bonusValue;

	/**
	 * @param sprite
	 * @param lvl
	 * @param scaleX
	 * @param scaleY
	 * @param noFlip
	 * @param cellid
	 * @param orientation
	 * @param color1
	 * @param color2
	 * @param color3
	 * @param accessories
	 * @param bonusValue
	 */
	public MovementCreateMonsterGroup(int id, int entitytype, int sprite, int lvl, int scaleX, int scaleY, boolean noFlip, int cellid, PathDirection orientation, int color1, int color2, int color3,
		int[] accessories,
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
	public int getId() {
		return id;
	}

	/**
	 * @return the entitytype
	 */
	public int getEntitytype() {
		return entitytype;
	}

	/**
	 * @return the lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl
	 *            the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
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
	public int getCellid() {
		return cellid;
	}

	/**
	 * @param cellid
	 *            the cellid to set
	 */
	public void setCellid(int cellid) {
		this.cellid = cellid;
	}

	/**
	 * @return the orientation
	 */
	public PathDirection getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation
	 *            the orientation to set
	 */
	public void setOrientation(PathDirection orientation) {
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
	public int[] getAccessories() {
		return accessories;
	}

	/**
	 * @param accessories
	 *            the accessories to set
	 */
	public void setAccessories(int[] accessories) {
		this.accessories = accessories;
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
		return "MovementCreateMonsterGroup [id=" + id + ", entitytype=" + entitytype + ", sprite=" + sprite + ", lvl=" + lvl + ", scaleX=" + scaleX + ", scaleY=" + scaleY + ", noFlip=" + noFlip
				+ ", cellid=" + cellid + ", orientation=" + orientation + ", color1=" + color1 + ", color2=" + color2 + ", color3=" + color3 + ", accessories=" + Arrays.toString(accessories)
				+ ", bonusValue=" + bonusValue + "]";
	}

}
