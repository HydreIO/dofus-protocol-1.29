/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;

public class GameLaunchSpellAction implements GameAction {
	private int spell;
	private int cellId;

	/**
	 * @param spell
	 * @param cellId
	 */
	public GameLaunchSpellAction(int spell, int cellId) {
		this.spell = spell;
		this.cellId = cellId;
	}

	public GameLaunchSpellAction() {
	}

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";");
		spell = Integer.parseInt(data[0]);
		cellId = Integer.parseInt(data[1]);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(spell + ";" + cellId);
	}

	public int getSpell() {
		return spell;
	}

	public void setSpell(int spell) {
		this.spell = spell;
	}

	public int getCellId() {
		return cellId;
	}

	public void setCellId(int cellId) {
		this.cellId = cellId;
	}

	@Override
	public String toString() {
		return "GameLaunchSpellAction(spell=" + spell +
				", cellId=" + cellId +
				")[" + getId() + ']';
	}
}
