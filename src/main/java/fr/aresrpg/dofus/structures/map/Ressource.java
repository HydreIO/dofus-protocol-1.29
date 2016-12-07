/*******************************************************************************
 * BotFather (C) - Dofus 1.29
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.map;

/**
 * 
 * @since
 */
public class Ressource extends Cell {

	private int typeId;
	private boolean spawned;

	public Ressource(int type,boolean spawned,boolean lineOfSight, int layerGroundRot, int groundLevel, int movement, int layerGroundNum, int groundSlope, boolean layerGroundFlip, int layerObject1Num, int layerObject1Rot, boolean layerObject1Flip, boolean layerObject2Flip, boolean layerObject2Interactive, int layerObject2Num) {
		super(lineOfSight, layerGroundRot, groundLevel, movement, layerGroundNum, groundSlope, layerGroundFlip, layerObject1Num, layerObject1Rot, layerObject1Flip, layerObject2Flip, layerObject2Interactive, layerObject2Num)
		this.typeId = type;
		this.spawned = spawned;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Ressource)) return false;
		Ressource r = (Ressource) obj;
		return cellId == r.getCellId() && typeId == r.getType(); // ya pas d'id ??
	}

	/**
	 * @return the cellId
	 */
	public int getCellId() {
		return cellId;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return typeId;
	}

	/**
	 * @return the spawned
	 */
	public boolean isSpawned() {
		return spawned;
	}

	/**
	 * @param spawned
	 *            the spawned to set
	 */
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}

	public void setSpawned() {
		setSpawned(true);
	}

}
