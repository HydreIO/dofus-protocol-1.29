/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;
import fr.aresrpg.dofus.util.Convert;

/**
 * 
 * @since
 */
public class GamePaChangeAction implements GameAction {

	private long entity;
	private int pa;

	public GamePaChangeAction() {
	}

	/**
	 * @param entity
	 * @param pa
	 */
	public GamePaChangeAction(long entity, int pa) {
		this.entity = entity;
		this.pa = pa;
	}

	/**
	 * @return the entity
	 */
	public long getEntity() {
		return entity;
	}

	/**
	 * @param entity
	 *            the entity to set
	 */
	public void setEntity(long entity) {
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
		this.entity = Convert.toLong(data[0]);
		this.pa = Convert.toInt(data[1]);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(entity + "," + pa);
	}

	@Override
	public String toString() {
		return "PaChangeAction [entity=" + entity + ", pa=" + pa + "]";
	}

}
