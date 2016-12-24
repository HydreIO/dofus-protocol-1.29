/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.party.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.ExchangeMove;
import fr.aresrpg.dofus.structures.character.PartyMember;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 
 * @since
 */
public class PartyMovementPacket implements ServerPacket {

	private ExchangeMove move;
	private PartyMember[] members;

	@Override
	public void read(DofusStream stream) {
		move = ExchangeMove.fromSymbol(stream.peek().charAt(0));
		stream.replaceRead(stream.peek().substring(1)); // remove symbol
		this.members = new PartyMember[stream.available()];
		IntStream.range(0, stream.available()).forEach(i -> members[i] = PartyMember.parseMember(stream.read()));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(members.length);
		boolean first = true;
		Arrays.stream(members).forEach(pm -> stream.write(first ? move.getSymbol() + pm.serialize() : pm.serialize()));
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	/**
	 * @return the move
	 */
	public ExchangeMove getMove() {
		return move;
	}

	/**
	 * @param move
	 *            the move to set
	 */
	public void setMove(ExchangeMove move) {
		this.move = move;
	}

	/**
	 * @return the members
	 */
	public PartyMember[] getMembers() {
		return members;
	}

	/**
	 * @param members
	 *            the members to set
	 */
	public void setMembers(PartyMember[] members) {
		this.members = members;
	}

	@Override
	public String toString() {
		return "PartyMovementPacket [move=" + move + ", members=" + Arrays.toString(members) + "]";
	}

}
