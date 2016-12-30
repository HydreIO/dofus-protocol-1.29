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
public class MovementMonster implements MovementAction {

	private int id;
	private int entitytype;
	private int spriteType;
	private int gfxId;
	private int scaleX;
	private int scaleY;
	private boolean noFlip;
	private int cellId;
	private Orientation orientation;
	private int powerLvl;
	private int color1;
	private int color2;
	private int color3;
	private Accessory[] accessories;

	private int life;
	private int pa;
	private int pm;
	private int[] resi;
	private int team;

	public MovementMonster(int id, int entitytype, int spriteType, int gfxId, int scaleX, int scaleY, boolean noFlip, int cellId, Orientation orientation, int powerLvl, int color1, int color2,
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

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		return obj instanceof MovementMonster && ((MovementMonster) obj).getId() == getId();
	}

	/**
	 * @return the spriteType
	 */
	public int getSpriteType() {
		return spriteType;
	}

	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life
	 *            the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * @return the pa
	 */
	public int getPa() {
		return pa;
	}

	/**
	 * @param pa
	 *            the pa to set
	 */
	public void setPa(int pa) {
		this.pa = pa;
	}

	/**
	 * @return the pm
	 */
	public int getPm() {
		return pm;
	}

	/**
	 * @param pm
	 *            the pm to set
	 */
	public void setPm(int pm) {
		this.pm = pm;
	}

	/**
	 * @return the resi
	 */
	public int[] getResi() {
		return resi;
	}

	/**
	 * @param resi
	 *            the resi to set
	 */
	public void setResi(int[] resi) {
		this.resi = resi;
	}

	/**
	 * @return the team
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * @param team
	 *            the team to set
	 */
	public void setTeam(int team) {
		this.team = team;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param entitytype
	 *            the entitytype to set
	 */
	public void setEntitytype(int entitytype) {
		this.entitytype = entitytype;
	}

	/**
	 * @param accessories
	 *            the accessories to set
	 */
	public void setAccessories(Accessory[] accessories) {
		this.accessories = accessories;
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

	@Override
	public String toString() {
		return "MovementMonster [id=" + id + ", entitytype=" + entitytype + ", spriteType=" + spriteType + ", gfxId=" + gfxId + ", scaleX=" + scaleX + ", scaleY=" + scaleY + ", noFlip=" + noFlip
				+ ", cellId=" + cellId + ", orientation=" + orientation + ", powerLvl=" + powerLvl + ", color1=" + color1 + ", color2=" + color2 + ", color3=" + color3 + ", accessories="
				+ Arrays.toString(accessories) + ", life=" + life + ", pa=" + pa + ", pm=" + pm + ", resi=" + Arrays.toString(resi) + ", team=" + team + "]";
	}

}
