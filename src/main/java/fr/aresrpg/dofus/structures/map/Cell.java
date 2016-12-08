package fr.aresrpg.dofus.structures.map;

public class Cell {
	boolean lineOfSight;
	int layerGroundRot;
	int groundLevel;
	int movement;
	int layerGroundNum;
	int groundSlope;
	boolean layerGroundFlip;
	int layerObject1Num;
	int layerObject1Rot;
	boolean layerObject1Flip;
	boolean layerObject2Flip;
	boolean layerObject2Interactive;
	int layerObject2Num;
	private Frame frame;

	public Cell(boolean lineOfSight, int layerGroundRot, int groundLevel, int movement, int layerGroundNum, int groundSlope, boolean layerGroundFlip, int layerObject1Num, int layerObject1Rot, boolean layerObject1Flip, boolean layerObject2Flip, boolean layerObject2Interactive, int layerObject2Num) {
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

	public int getMovement() {
		return movement;
	}

	public Frame getFrame() {
		return frame;
	}

	public void setFrame(Frame frame) {
		this.frame = frame;
	}

	public int getLayerObject2Num() {
		return layerObject2Num;
	}
}
