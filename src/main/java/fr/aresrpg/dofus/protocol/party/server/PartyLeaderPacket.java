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

	private long leaderId;

	@Override
	public void read(DofusStream stream) {
		this.leaderId = stream.readLong();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeLong(leaderId);
	}

	/**
	 * @return the leaderId
	 */
	public long getLeaderId() {
		return leaderId;
	}

	/**
	 * @param leaderId
	 *            the leaderId to set
	 */
	public void setLeaderId(long leaderId) {
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
