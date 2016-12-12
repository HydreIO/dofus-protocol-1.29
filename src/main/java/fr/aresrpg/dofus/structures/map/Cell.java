package fr.aresrpg.dofus.structures.map;

import fr.aresrpg.dofus.util.Maps;

public class Cell {
	int id;
	int mapWidth;
	boolean lineOfSight;
	int layerGroundRot;
	int groundLevel;
	int movement;
	int layerGroundNum;
	int groundSlope;
	int x, y;
	boolean layerGroundFlip;
	int layerObject1Num;
	int layerObject1Rot;
	boolean layerObject1Flip;
	boolean layerObject2Flip;
	boolean layerObject2Interactive;
	int layerObject2Num;
	private Frame frame;

	public Cell(int mapwidth, int id, int x, int y, boolean lineOfSight, int layerGroundRot, int groundLevel, int movement, int layerGroundNum, int groundSlope, boolean layerGroundFlip,
		int layerObject1Num,
		int layerObject1Rot,
		boolean layerObject1Flip, boolean layerObject2Flip, boolean layerObject2Interactive, int layerObject2Num) {
		this.lineOfSight = lineOfSight;
		this.id = id;
		this.mapWidth = mapwidth;
		this.x = x;
		this.y = y;
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

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	@Override
	public String toString() {
		return "Cell [id=" + id + ", mapWidth=" + mapWidth + ", lineOfSight=" + lineOfSight + ", layerGroundRot=" + layerGroundRot + ", groundLevel=" + groundLevel + ", movement=" + movement
				+ ", layerGroundNum=" + layerGroundNum + ", groundSlope=" + groundSlope + ", x=" + x + ", y=" + y + ", layerGroundFlip=" + layerGroundFlip + ", layerObject1Num=" + layerObject1Num
				+ ", layerObject1Rot=" + layerObject1Rot + ", layerObject1Flip=" + layerObject1Flip + ", layerObject2Flip=" + layerObject2Flip + ", layerObject2Interactive=" + layerObject2Interactive
				+ ", layerObject2Num=" + layerObject2Num + ", frame=" + frame + "]";
	}

}
