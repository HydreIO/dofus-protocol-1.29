/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;
import fr.aresrpg.dofus.protocol.game.movement.MovementInvocation;
import fr.aresrpg.dofus.protocol.game.server.GameMovementPacket;

import java.util.HashSet;
import java.util.Set;

/**
 * Packet invocation
 * 
 * @since
 */
public class GameSummonAction implements GameAction {

	Set<MovementInvocation> summoned = new HashSet<>();

	/**
	 * @param summoned
	 */
	public GameSummonAction(Set<MovementInvocation> summoned) {
		this.summoned = summoned;
	}

	public GameSummonAction() {
	}

	/**
	 * @return the summoned
	 */
	public Set<MovementInvocation> getSummoned() {
		return summoned;
	}

	/**
	 * @param summoned
	 *            the summoned to set
	 */
	public void setSummoned(Set<MovementInvocation> summoned) {
		this.summoned = summoned;
	}

	@Override
	public void read(DofusStream stream) {
		GameMovementPacket dofusjtebaise = new GameMovementPacket(); // genius
		dofusjtebaise.read(stream);
		dofusjtebaise.getActors().stream().forEach(p -> summoned.add((MovementInvocation) p.getSecond()));
	}

	@Override
	public void write(DofusStream stream) {
		// je write pas les mob c'est mort nique ta grand mere
	}

	@Override
	public String toString() {
		return "GameSummonAction [summoned=" + summoned + "]";
	}

}
