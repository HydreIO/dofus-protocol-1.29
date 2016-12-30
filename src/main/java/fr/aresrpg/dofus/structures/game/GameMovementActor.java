/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
/*******************************************************************************
 * BotFather (C) - Dofus 1.29
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.game;

import fr.aresrpg.dofus.protocol.game.movement.MovementRemoveActor;
import fr.aresrpg.dofus.structures.Orientation;

/**
 * 
 * @since
 */
public class GameMovementActor {

	private GameMovementType action;
	private long id;
	private int cellId;
	private Orientation orientation;
	private int classeId;
	private int scaleX;
	private int scaleY;
	private int sex;
	private int lvl;
	private int color1;
	private int color2;
	private int color3;
	private int[] accessories;
	private int life;
	private int pa;
	private int pm;
	private int[] resi;
	private int team;
	private int aura;
	private int emote;
	private int emotetimer;
	private String guildname;
	private int emblem;
	private int restrictions;
	private int alignement;
	private int rank;

	private GameMovementActor(int id, GameMovementType action, int cellid, Orientation orientation) {
		this.id = id;
		this.action = action;
		this.cellId = cellid;
		this.orientation = orientation;
	}

	public static GameMovementActor withRemoveMovement(MovementRemoveActor movement) {
		return new GameMovementActor(movement.getId(), GameMovementType.REMOVE, 0, null);
	}

	/**
	 * @return the action
	 */
	public GameMovementType getAction() {
		return action;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @return the cellId
	 */
	public int getCellId() {
		return cellId;
	}

	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

}
