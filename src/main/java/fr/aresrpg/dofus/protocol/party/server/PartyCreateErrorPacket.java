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
import fr.aresrpg.dofus.structures.PartyErrorReason;

/**
 * 
 * @since
 */
public class PartyCreateErrorPacket implements ServerPacket {

	private PartyErrorReason reason;

	@Override
	public void read(DofusStream stream) {
		this.reason = PartyErrorReason.valueOf(stream.read().charAt(0));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeChar(reason.getCode());
	}

	/**
	 * @return the reason
	 */
	public PartyErrorReason getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(PartyErrorReason reason) {
		this.reason = reason;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyCreateErrorPacket [reason=" + reason + "]";
	}

}
