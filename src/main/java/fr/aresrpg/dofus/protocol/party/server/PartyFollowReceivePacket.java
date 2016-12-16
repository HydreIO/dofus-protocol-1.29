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
import fr.aresrpg.dofus.protocol.ProtocolRegistry.State;

/**
 * 
 * @since
 */
public class PartyFollowReceivePacket implements ServerPacket {

	private boolean success;
	private String followed;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		this.success = data.charAt(0) == State.OK;
		if (success) this.followed = data.substring(1);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(success ? "K" + followed : "E");
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the followed
	 */
	public String getFollowed() {
		return followed;
	}

	/**
	 * @param followed
	 *            the followed to set
	 */
	public void setFollowed(String followed) {
		this.followed = followed;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyFollowReceivePacket [success=" + success + ", followed=" + followed + "]";
	}

}
