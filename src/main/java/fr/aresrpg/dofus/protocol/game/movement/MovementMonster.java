/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.movement;

import fr.aresrpg.dofus.structures.PathDirection;
import fr.aresrpg.dofus.structures.item.Accessory;

import java.util.Arrays;

/**
 * 
 * @since
 */
public class MovementMonster implements MovementAction {

	private int id;
	private int entitytype;
	private int spriteType;
	private int gfxId;
	private int scaleX;
	private int scaleY;
	private boolean noFlip;
	private int cellId;
	private PathDirection orientation;
	private int powerLvl;
	private int color1;
	private int color2;
	private int color3;
	private Accessory[] accessories;

	/**
	 * @param spriteType
	 * @param gfxId
	 * @param scaleX
	 * @param scaleY
	 * @param noFlip
	 * @param cellId
	 * @param orientation
	 * @param powerLvl
	 * @param color1
	 * @param color2
	 * @param color3
	 * @param accessories
	 */
	public MovementMonster(int id, int entitytype, int spriteType, int gfxId, int scaleX, int scaleY, boolean noFlip, int cellId, PathDirection orientation, int powerLvl, int color1, int color2,
		int color3,
		Accessory[] accessories) {
		this.id = id;
		this.entitytype = entitytype;
		this.spriteType = spriteType;
		this.gfxId = gfxId;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.noFlip = noFlip;
		this.cellId = cellId;
		this.orientation = orientation;
		this.powerLvl = powerLvl;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.accessories = accessories;
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
	 * @return the gfxId
	 */
	public int getGfxId() {
		return gfxId;
	}

	/**
	 * @param gfxId
	 *            the gfxId to set
	 */
	public void setGfxId(int gfxId) {
		this.gfxId = gfxId;
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
	 * @return the cellId
	 */
	public int getCellId() {
		return cellId;
	}

	/**
	 * @param cellId
	 *            the cellId to set
	 */
	public void setCellId(int cellId) {
		this.cellId = cellId;
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
	 * @return the powerLvl
	 */
	public int getPowerLvl() {
		return powerLvl;
	}

	/**
	 * @param powerLvl
	 *            the powerLvl to set
	 */
	public void setPowerLvl(int powerLvl) {
		this.powerLvl = powerLvl;
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

	@Override
	public String toString() {
		return "MovementCreateMonster [id=" + id + ", entitytype=" + entitytype + ", spriteType=" + spriteType + ", gfxId=" + gfxId + ", scaleX=" + scaleX + ", scaleY=" + scaleY + ", noFlip=" + noFlip
				+ ", cellId=" + cellId + ", orientation=" + orientation + ", powerLvl=" + powerLvl + ", color1=" + color1 + ", color2=" + color2 + ", color3=" + color3 + ", accessories="
				+ Arrays.toString(accessories) + "]";
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
}
