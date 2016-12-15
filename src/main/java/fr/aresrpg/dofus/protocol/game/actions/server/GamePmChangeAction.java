package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;
import fr.aresrpg.dofus.util.Convert;

/**
 * 
 * @since
 */
public class GamePmChangeAction implements GameAction {

	private int entity;
	private int pa;

	public GamePmChangeAction() {
	}

	/**
	 * @param entity
	 * @param pa
	 */
	public GamePmChangeAction(int entity, int pa) {
		this.entity = entity;
		this.pa = pa;
	}

	/**
	 * @return the entity
	 */
	public int getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(int entity) {
		this.entity = entity;
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

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(",");
		this.entity = Convert.toInt(data[0]);
		this.pa = Convert.toInt(data[1]);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(entity + "," + pa);
	}

	@Override
	public String toString() {
		return "PmChangeAction [entity=" + entity + ", pa=" + pa + "]";
	}

}
