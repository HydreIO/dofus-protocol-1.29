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

/**
 * 
 * @since
 */
public class PartyLeaderPacket implements ServerPacket {

	private int leaderId;

	@Override
	public void read(DofusStream stream) {
		this.leaderId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(leaderId);
	}

	/**
	 * @return the leaderId
	 */
	public int getLeaderId() {
		return leaderId;
	}

	/**
	 * @param leaderId
	 *            the leaderId to set
	 */
	public void setLeaderId(int leaderId) {
		this.leaderId = leaderId;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyLeaderPacket [leaderId=" + leaderId + "]";
	}

}
