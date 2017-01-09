/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.map;

import fr.aresrpg.dofus.Constants;
import fr.aresrpg.dofus.structures.item.Interractable;
import fr.aresrpg.dofus.util.Maps;

public class Cell {
	protected DofusMap map;
	protected final int id;
	protected int permanentLevel;
	protected boolean active;
	protected String layerObjectExternal = "";
	protected boolean layerObjectExternalInteractive = false;
	private String layerObjectExternalData = "";
	protected boolean lineOfSight;
	protected int layerGroundRot;
	protected int groundLevel;
	protected int movement;
	protected int layerGroundNum;
	protected int groundSlope;
	protected int x = -1;
	protected int y = -1;
	protected int xRot = -1;
	protected int yRot = -1;
	protected boolean layerGroundFlip;
	protected int layerObject1Num;
	protected int layerObject1Rot;
	protected boolean layerObject1Flip;
	protected boolean layerObject2Flip;
	protected boolean layerObject2Interactive;
	protected int layerObject2Num;
	protected boolean layerObjectExternalAutoSize;
	protected int frame;

	public Cell(int id, boolean active, boolean lineOfSight, int layerGroundRot, int groundLevel, int movement, int layerGroundNum, int groundSlope, boolean layerGroundFlip,
		int layerObject1Num, int layerObject1Rot, boolean layerObject1Flip, boolean layerObject2Flip, boolean layerObject2Interactive, int layerObject2Num) {
		this.id = id;
		this.active = active;
		this.lineOfSight = lineOfSight;
		this.layerGroundRot = layerGroundRot;
		this.groundLevel = groundLevel;
		this.movement = movement;
		this.layerGroundNum = layerGroundNum;
		this.groundSlope = groundSlope;
		this.layerGroundFlip = layerGroundFlip;
		this.layerObject1Num = layerObject1Num;
		this.layerObject1Rot = layerObject1Rot;
		this.layerObject1Flip = layerObject1Flip;
		this.layerObject2Flip = layerObject2Flip;
		this.layerObject2Interactive = layerObject2Interactive;
		this.layerObject2Num = layerObject2Num;
	}

	private Cell(DofusMap map, int id, int permanentLevel, boolean active, String layerObjectExternal, boolean layerObjectExternalInteractive, String layerObjectExternalData, boolean lineOfSight,
		int layerGroundRot, int groundLevel, int movement, int layerGroundNum, int groundSlope, int x, int y, int xRot, int yRot, boolean layerGroundFlip, int layerObject1Num, int layerObject1Rot,
		boolean layerObject1Flip, boolean layerObject2Flip, boolean layerObject2Interactive, int layerObject2Num, boolean layerObjectExternalAutoSize, int frame) {
		this.map = map;
		this.id = id;
		this.permanentLevel = permanentLevel;
		this.active = active;
		this.layerObjectExternal = layerObjectExternal;
		this.layerObjectExternalInteractive = layerObjectExternalInteractive;
		this.layerObjectExternalData = layerObjectExternalData;
		this.lineOfSight = lineOfSight;
		this.layerGroundRot = layerGroundRot;
		this.groundLevel = groundLevel;
		this.movement = movement;
		this.layerGroundNum = layerGroundNum;
		this.groundSlope = groundSlope;
		this.x = x;
		this.y = y;
		this.xRot = xRot;
		this.yRot = yRot;
		this.layerGroundFlip = layerGroundFlip;
		this.layerObject1Num = layerObject1Num;
		this.layerObject1Rot = layerObject1Rot;
		this.layerObject1Flip = layerObject1Flip;
		this.layerObject2Flip = layerObject2Flip;
		this.layerObject2Interactive = layerObject2Interactive;
		this.layerObject2Num = layerObject2Num;
		this.layerObjectExternalAutoSize = layerObjectExternalAutoSize;
		this.frame = frame;
	}

	public Cell clone() {
		return new Cell(map, id, permanentLevel, active, layerObjectExternal, layerObjectExternalInteractive, layerObjectExternalData, lineOfSight, layerGroundRot, groundLevel, movement,
				layerGroundNum, groundSlope, x, layerGroundNum, xRot, yRot, layerGroundFlip, layerObject1Num, layerObject1Rot, layerObject1Flip, layerObject2Flip, layerObject2Interactive,
				layerObject2Num, layerObjectExternalAutoSize, frame);

	}

	public int distance(int cellid) {
		return Maps.distance(id, cellid, map.getWidth(), map.getHeight());
	}

	public int distance(Cell cell) {
		return distance(cell.getId());
	}

	public int distanceManathan(int cellid) {
		return Maps.distanceManathan(getId(), cellid, map.getWidth(), map.getHeight());
	}

	public int distanceManathan(Cell cell) {
		return distanceManathan(cell.getId());
	}

	public boolean isWalkeable() {
		return getMovement() != 0 && !isInterractable();
	}

	public boolean isTeleporter() {
		for (int i : Constants.TELEPORT_TEXTURES)
			if (layerObject1Num == i || layerObject2Num == i) return true;
		return false;
	}

	public boolean isInterractable() {
		if (movement == 1) return true;
		return Interractable.isInterractable(getLayerObject2Num());
	}

	public DofusMap getMap() {
		return map;
	}

	public Cell setMap(DofusMap map) {
		this.map = map;
		this.x = Maps.getX(id, map.getWidth());
		this.y = Maps.getY(id, map.getWidth());
		this.xRot = Maps.getXRotated(id, map.getWidth(), map.getHeight());
		this.yRot = Maps.getYRotated(id, map.getWidth(), map.getHeight());
		return this;
	}

	public void setPermanentLevel(int permanentLevel) {
		this.permanentLevel = permanentLevel;
	}

	/**
	 * @return the layerObjectExternalData
	 */
	public String getLayerObjectExternalData() {
		return layerObjectExternalData;
	}

	/**
	 * @param layerObjectExternalData
	 *            the layerObjectExternalData to set
	 */
	public void setLayerObjectExternalData(String layerObjectExternalData) {
		this.layerObjectExternalData = layerObjectExternalData;
	}

	/**
	 * @return the layerObjectExternalAutoSize
	 */
	public boolean isLayerObjectExternalAutoSize() {
		return layerObjectExternalAutoSize;
	}

	/**
	 * @param layerObjectExternalAutoSize
	 *            the layerObjectExternalAutoSize to set
	 */
	public void setLayerObjectExternalAutoSize(boolean layerObjectExternalAutoSize) {
		this.layerObjectExternalAutoSize = layerObjectExternalAutoSize;
	}

	/**
	 * @return the permanentLevel
	 */
	public int getPermanentLevel() {
		return permanentLevel;
	}

	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @return the layerObjectExternal
	 */
	public String getLayerObjectExternal() {
		return layerObjectExternal;
	}

	/**
	 * @return the layerObjectExternalInteractive
	 */
	public boolean isLayerObjectExternalInteractive() {
		return layerObjectExternalInteractive;
	}

	/**
	 * @param active
	 *            the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @param layerObjectExternal
	 *            the layerObjectExternal to set
	 */
	public void setLayerObjectExternal(String layerObjectExternal) {
		this.layerObjectExternal = layerObjectExternal;
	}

	/**
	 * @param layerObjectExternalInteractive
	 *            the layerObjectExternalInteractive to set
	 */
	public void setLayerObjectExternalInteractive(boolean layerObjectExternalInteractive) {
		this.layerObjectExternalInteractive = layerObjectExternalInteractive;
	}

	/**
	 * @param lineOfSight
	 *            the lineOfSight to set
	 */
	public void setLineOfSight(boolean lineOfSight) {
		this.lineOfSight = lineOfSight;
	}

	/**
	 * @param layerGroundRot
	 *            the layerGroundRot to set
	 */
	public void setLayerGroundRot(int layerGroundRot) {
		this.layerGroundRot = layerGroundRot;
	}

	/**
	 * @param groundLevel
	 *            the groundLevel to set
	 */
	public void setGroundLevel(int groundLevel) {
		this.groundLevel = groundLevel;
	}

	/**
	 * @param movement
	 *            the movement to set
	 */
	public void setMovement(int movement) {
		this.movement = movement;
	}

	/**
	 * @param layerGroundNum
	 *            the layerGroundNum to set
	 */
	public void setLayerGroundNum(int layerGroundNum) {
		this.layerGroundNum = layerGroundNum;
	}

	/**
	 * @param groundSlope
	 *            the groundSlope to set
	 */
	public void setGroundSlope(int groundSlope) {
		this.groundSlope = groundSlope;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @param xRot
	 *            the xRot to set
	 */
	public void setxRot(int xRot) {
		this.xRot = xRot;
	}

	/**
	 * @param yRot
	 *            the yRot to set
	 */
	public void setyRot(int yRot) {
		this.yRot = yRot;
	}

	/**
	 * @param layerGroundFlip
	 *            the layerGroundFlip to set
	 */
	public void setLayerGroundFlip(boolean layerGroundFlip) {
		this.layerGroundFlip = layerGroundFlip;
	}

	/**
	 * @param layerObject1Num
	 *            the layerObject1Num to set
	 */
	public void setLayerObject1Num(int layerObject1Num) {
		this.layerObject1Num = layerObject1Num;
	}

	/**
	 * @param layerObject1Rot
	 *            the layerObject1Rot to set
	 */
	public void setLayerObject1Rot(int layerObject1Rot) {
		this.layerObject1Rot = layerObject1Rot;
	}

	/**
	 * @param layerObject1Flip
	 *            the layerObject1Flip to set
	 */
	public void setLayerObject1Flip(boolean layerObject1Flip) {
		this.layerObject1Flip = layerObject1Flip;
	}

	/**
	 * @param layerObject2Flip
	 *            the layerObject2Flip to set
	 */
	public void setLayerObject2Flip(boolean layerObject2Flip) {
		this.layerObject2Flip = layerObject2Flip;
	}

	/**
	 * @param layerObject2Interactive
	 *            the layerObject2Interactive to set
	 */
	public void setLayerObject2Interactive(boolean layerObject2Interactive) {
		this.layerObject2Interactive = layerObject2Interactive;
	}

	/**
	 * @param layerObject2Num
	 *            the layerObject2Num to set
	 */
	public void setLayerObject2Num(int layerObject2Num) {
		this.layerObject2Num = layerObject2Num;
	}

	/**
	 * @param frame
	 *            the frame to set
	 */
	public void setFrame(int frame) {
		this.frame = frame;
	}

	/**
	 * @return the xRot
	 */
	public int getxRot() {
		return xRot;
	}

	/**
	 * @return the yRot
	 */
	public int getyRot() {
		return yRot;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	public int getXRot() {
		return xRot;
	}

	public int getYRot() {
		return yRot;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the layerGroundFlip
	 */
	public boolean isLayerGroundFlip() {
		return layerGroundFlip;
	}

	/**
	 * @return the layerObject1Flip
	 */
	public boolean isLayerObject1Flip() {
		return layerObject1Flip;
	}

	/**
	 * @return the layerObject2Flip
	 */
	public boolean isLayerObject2Flip() {
		return layerObject2Flip;
	}

	/**
	 * @return the layerObject2Interactive
	 */
	public boolean isLayerObject2Interactive() {
		return layerObject2Interactive;
	}

	/**
	 * @return the lineOfSight
	 */
	public boolean isLineOfSight() {
		return lineOfSight;
	}

	/**
	 * @return the groundLevel
	 */
	public int getGroundLevel() {
		return groundLevel;
	}

	/**
	 * @return the groundSlope
	 */
	public int getGroundSlope() {
		return groundSlope;
	}

	/**
	 * @return the layerGroundNum
	 */
	public int getLayerGroundNum() {
		return layerGroundNum;
	}

	/**
	 * @return the layerGroundRot
	 */
	public int getLayerGroundRot() {
		return layerGroundRot;
	}

	/**
	 * @return the layerObject1Num
	 */
	public int getLayerObject1Num() {
		return layerObject1Num;
	}

	/**
	 * @return the layerObject1Rot
	 */
	public int getLayerObject1Rot() {
		return layerObject1Rot;
	}

	/**
	 * @return the layerObject2Num
	 */
	public int getLayerObject2Num() {
		return layerObject2Num;
	}

	public int getMovement() {
		return movement;
	}

	public int getFrame() {
		return frame;
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && (obj == this || (obj instanceof Cell && ((Cell) obj).getId() == getId()));
	}

	public void applyFrame(Frame frame) {
		this.frame = frame.getId();
		if (frame.isInteractive() != null) {
			this.layerObject2Interactive = frame.isInteractive();
		}
	}

	@Override
	public String toString() {
		return "Cell [id=" + id + " ,frame=" + frame + ", lineOfSight=" + lineOfSight + ", layerGroundRot=" + layerGroundRot + ", groundLevel=" + groundLevel + ", movement="
				+ movement
				+ ", layerGroundNum=" + layerGroundNum + ", groundSlope=" + groundSlope + ", x=" + x + ", y=" + y + ", layerGroundFlip=" + layerGroundFlip + ", layerObject1Num=" + layerObject1Num
				+ ", layerObject1Rot=" + layerObject1Rot + ", layerObject1Flip=" + layerObject1Flip + ", layerObject2Flip=" + layerObject2Flip + ", layerObject2Interactive=" + layerObject2Interactive
				+ ", layerObject2Num=" + layerObject2Num + "]";
	}

}
