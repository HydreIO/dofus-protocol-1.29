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
import fr.aresrpg.dofus.protocol.game.movement.MovementAction;
import fr.aresrpg.dofus.protocol.game.server.GameMovementPacket;
import fr.aresrpg.dofus.util.StringJoiner;

import java.util.HashSet;
import java.util.Set;

/**
 * Packet invocation
 * 
 * @since
 */
public class GameSummonAction implements GameAction {

	Set<MovementAction> summoned = new HashSet<>();
	private String fullpkt;

	/**
	 * @param summoned
	 */
	public GameSummonAction(Set<MovementAction> summoned) {
		this.summoned = summoned;
	}

	public GameSummonAction() {
	}

	/**
	 * @return the summoned
	 */
	public Set<MovementAction> getSummoned() {
		return summoned;
	}

	/**
	 * @param summoned
	 *            the summoned to set
	 */
	public void setSummoned(Set<MovementAction> summoned) {
		this.summoned = summoned;
	}

	@Override
	public void read(DofusStream stream) {
		StringJoiner n = new StringJoiner("|");
		while (stream.available() > 0)
			n.add(stream.read());
		this.fullpkt = n.toString();
		stream.setReadIndex(0);
		String peek = stream.peek();
		stream.allocate(2).write(0, "").write(1, peek);
		GameMovementPacket dofusjtebaise = new GameMovementPacket(); // genius
		dofusjtebaise.read(stream);
		dofusjtebaise.getActors().stream().forEach(p -> summoned.add(p.getSecond()));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(fullpkt);
	}

	@Override
	public String toString() {
		return "GameSummonAction [summoned=" + summoned + "]";
	}

}
