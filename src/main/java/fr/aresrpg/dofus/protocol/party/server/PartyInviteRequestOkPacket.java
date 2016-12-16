package fr.aresrpg.dofus.protocol.party.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class PartyInviteRequestOkPacket implements ServerPacket {

	private String inviter, invited;

	@Override
	public void read(DofusStream stream) {
		this.inviter = stream.read();
		if (!inviter.isEmpty() && stream.available() > 0)
			this.invited = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).write(inviter).write(invited);
	}

	/**
	 * @return the inviter
	 */
	public String getInviter() {
		return inviter;
	}

	/**
	 * @param inviter
	 *            the inviter to set
	 */
	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	/**
	 * @return the invited
	 */
	public String getInvited() {
		return invited;
	}

	/**
	 * @param invited
	 *            the invited to set
	 */
	public void setInvited(String invited) {
		this.invited = invited;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyInviteRequestOkPacket [inviter=" + inviter + ", invited=" + invited + "]";
	}

}
