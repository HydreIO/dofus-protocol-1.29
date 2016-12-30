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
public class MovementNpc implements MovementAction {

	private int id;
	private int spriteType;
	private int gfx;
	private int scaleX;
	private int scaleY;
	private int cellid;
	private Orientation orientation;
	private int sex;
	private int color1;
	private int color2;
	private int color3;
	private Accessory[] accessories;
	private int extraclip;
	private int customArtwork;

	public MovementNpc(int id, int spriteType, int gfx, int scaleX, int scaleY, int cellid, Orientation orientation, int sex, int color1, int color2, int color3, Accessory[] accessories,
		int extraclip, int customArtwork) {
		super();
		this.id = id;
		this.spriteType = spriteType;
		this.gfx = gfx;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.cellid = cellid;
		this.orientation = orientation;
		this.sex = sex;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.accessories = accessories;
		this.extraclip = extraclip;
		this.customArtwork = customArtwork;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		return obj instanceof MovementNpc && ((MovementNpc) obj).getId() == getId();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the spriteType
	 */
	public int getSpriteType() {
		return spriteType;
	}

	/**
	 * @param spriteType
	 *            the spriteType to set
	 */
	public void setSpriteType(int spriteType) {
		this.spriteType = spriteType;
	}

	/**
	 * @return the gfx
	 */
	public int getGfx() {
		return gfx;
	}

	/**
	 * @param gfx
	 *            the gfx to set
	 */
	public void setGfx(int gfx) {
		this.gfx = gfx;
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
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
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
	 * @param accessories
	 *            the accessories to set
	 */
	public void setAccessories(Accessory[] accessories) {
		this.accessories = accessories;
	}

	/**
	 * @return the extraclip
	 */
	public int getExtraclip() {
		return extraclip;
	}

	/**
	 * @param extraclip
	 *            the extraclip to set
	 */
	public void setExtraclip(int extraclip) {
		this.extraclip = extraclip;
	}

	/**
	 * @return the customArtwork
	 */
	public int getCustomArtwork() {
		return customArtwork;
	}

	/**
	 * @param customArtwork
	 *            the customArtwork to set
	 */
	public void setCustomArtwork(int customArtwork) {
		this.customArtwork = customArtwork;
	}

	@Override
	public String toString() {
		return "MovementNpc [id=" + id + ", spriteType=" + spriteType + ", gfx=" + gfx + ", scaleX=" + scaleX + ", scaleY=" + scaleY + ", cellid=" + cellid + ", orientation=" + orientation + ", sex="
				+ sex + ", color1=" + color1 + ", color2=" + color2 + ", color3=" + color3 + ", accessories=" + Arrays.toString(accessories) + ", extraclip=" + extraclip + ", customArtwork="
				+ customArtwork + "]";
	}

}
