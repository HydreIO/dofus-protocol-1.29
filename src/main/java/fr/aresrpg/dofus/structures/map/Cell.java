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

import fr.aresrpg.dofus.util.Maps;

public class Cell {
	private int id;
	private int mapWidth;
	private boolean lineOfSight;
	private int layerGroundRot;
	private int groundLevel;
	private int movement;
	private int layerGroundNum;
	private int groundSlope;
	private int x, y;
	private boolean layerGroundFlip;
	private int layerObject1Num;
	private int layerObject1Rot;
	private boolean layerObject1Flip;
	private boolean layerObject2Flip;
	private boolean layerObject2Interactive;
	private int layerObject2Num;
	private int frame;

	/**
	 * @param id
	 * @param mapWidth
	 * @param lineOfSight
	 * @param layerGroundRot
	 * @param groundLevel
	 * @param movement
	 * @param layerGroundNum
	 * @param groundSlope
	 * @param x
	 * @param y
	 * @param layerGroundFlip
	 * @param layerObject1Num
	 * @param layerObject1Rot
	 * @param layerObject1Flip
	 * @param layerObject2Flip
	 * @param layerObject2Interactive
	 * @param layerObject2Num
	 */
	public Cell(int id, int mapWidth, boolean lineOfSight, int layerGroundRot, int groundLevel, int movement, int layerGroundNum, int groundSlope, int x, int y, boolean layerGroundFlip,
		int layerObject1Num, int layerObject1Rot, boolean layerObject1Flip, boolean layerObject2Flip, boolean layerObject2Interactive, int layerObject2Num) {
		super();
		this.id = id;
		this.mapWidth = mapWidth;
		this.lineOfSight = lineOfSight;
		this.layerGroundRot = layerGroundRot;
		this.groundLevel = groundLevel;
		this.movement = movement;
		this.layerGroundNum = layerGroundNum;
		this.groundSlope = groundSlope;
		this.x = x;
		this.y = y;
		this.layerGroundFlip = layerGroundFlip;
		this.layerObject1Num = layerObject1Num;
		this.layerObject1Rot = layerObject1Rot;
		this.layerObject1Flip = layerObject1Flip;
		this.layerObject2Flip = layerObject2Flip;
		this.layerObject2Interactive = layerObject2Interactive;
		this.layerObject2Num = layerObject2Num;
	}

	public int distance(int cellid) {
		return Maps.distance(getId(), cellid, getMapWidth());
	}

	public int distance(Cell cell) {
		return distance(cell.getId());
	}

	public int distanceManathan(int cellid) {
		return Maps.distanceManathan(getId(), cellid, getMapWidth());
	}

	public int distanceManathan(Cell cell) {
		return distanceManathan(cell.getId());
	}

	/**
	 * @return the mapWidth
	 */
	public int getMapWidth() {
		return mapWidth;
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
		return obj == null ? false : obj == this || (obj instanceof Cell && ((Cell) obj).getId() == getId());
	}

	public void applyFrame(Frame frame) {
		this.frame = frame.getId();
		if (frame.isInteractive() != null) {
			this.layerObject2Interactive = frame.isInteractive();
		}
	}

	@Override
	public String toString() {
		return "Cell [id=" + id + ", mapWidth=" + mapWidth + ", lineOfSight=" + lineOfSight + ", layerGroundRot=" + layerGroundRot + ", groundLevel=" + groundLevel + ", movement=" + movement
				+ ", layerGroundNum=" + layerGroundNum + ", groundSlope=" + groundSlope + ", x=" + x + ", y=" + y + ", layerGroundFlip=" + layerGroundFlip + ", layerObject1Num=" + layerObject1Num
				+ ", layerObject1Rot=" + layerObject1Rot + ", layerObject1Flip=" + layerObject1Flip + ", layerObject2Flip=" + layerObject2Flip + ", layerObject2Interactive=" + layerObject2Interactive
				+ ", layerObject2Num=" + layerObject2Num + ']';
	}

}
