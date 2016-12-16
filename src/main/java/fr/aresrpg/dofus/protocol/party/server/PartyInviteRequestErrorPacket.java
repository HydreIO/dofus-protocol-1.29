package fr.aresrpg.dofus.protocol.party.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.PartyErrorReason;

/**
 * 
 * @since
 */
public class PartyInviteRequestErrorPacket implements ServerPacket {

	private PartyErrorReason reason;

	@Override
	public void read(DofusStream stream) {
		this.reason = PartyErrorReason.valueOf(stream.read().charAt(0));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeChar(reason.getCode());
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
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
	public String toString() {
		return "PartyInviteRequestErrorPacket [reason=" + reason + "]";
	}

}
