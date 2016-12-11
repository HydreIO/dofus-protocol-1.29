package fr.aresrpg.dofus.protocol.game.movement;

/**
 * 
 * @since
 */
public class MovementRemoveActor implements MovementAction {

	private int id;

	public MovementRemoveActor(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
