/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
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

	private PartyMember[] members;

	@Override
	public void read(DofusStream stream) {
		ExchangeMove m = ExchangeMove.fromSymbol(stream.peek().charAt(0));
		stream.replaceRead(stream.peek().substring(1)); // remove symbol
		this.members = new PartyMember[stream.available()];
		IntStream.range(0, stream.available()).forEach(i -> members[i] = PartyMember.parseMember(m, stream.read()));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(members.length);
		Arrays.stream(members).forEach(pm -> stream.write(pm.serialize()));
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
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
		return "PartyMovementPacket [members=" + Arrays.toString(members) + "]";
	}

}
