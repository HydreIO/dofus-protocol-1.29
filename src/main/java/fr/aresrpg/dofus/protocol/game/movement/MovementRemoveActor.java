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

/**
 * 
 * @since
 */
public class MovementRemoveActor implements MovementAction {

	private long id;

	public MovementRemoveActor(long id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	@Override
	public void setCellId(int id) {
	}

	@Override
	public int getCellId() {
		return -1;
	}

	@Override
	public String toString() {
		return "MovementRemoveActor [id=" + id + "]";
	}

}
