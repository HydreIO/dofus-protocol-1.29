/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;
import fr.aresrpg.dofus.structures.Skills;

/**
 * 
 * @since
 */
public class GameInteractionAction implements GameAction {

	private int cell;
	private Skills skill;

	public GameInteractionAction() {
	}

	/**
	 * @param cell
	 * @param skill
	 */
	public GameInteractionAction(int cell, Skills skill) {
		this.cell = cell;
		this.skill = skill;
	}

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";");
		this.cell = Integer.parseInt(data[0]);
		this.skill = Skills.valueOf(Integer.parseInt(data[1]));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(cell + ";" + skill.getId());
	}

	/**
	 * @return the cell
	 */
	public int getCell() {
		return cell;
	}

	/**
	 * @param cell
	 *            the cell to set
	 */
	public void setCell(int cell) {
		this.cell = cell;
	}

	/**
	 * @return the skill
	 */
	public Skills getSkill() {
		return skill;
	}

	/**
	 * @param skill
	 *            the skill to set
	 */
	public void setSkill(Skills skill) {
		this.skill = skill;
	}

	@Override
	public String toString() {
		return "GameInteractionAction [cell=" + cell + ", skill=" + skill + "]";
	}

}
