/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.party.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class PartyFollowPacket implements ClientPacket {

	private int playerId;
	private boolean follow;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		this.follow = data.charAt(0) == '+';
		this.playerId = Integer.parseInt(data.substring(1));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write((follow ? "+" : "-") + playerId);
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the follow
	 */
	public boolean isFollow() {
		return follow;
	}

	/**
	 * @param follow
	 *            the follow to set
	 */
	public void setFollow(boolean follow) {
		this.follow = follow;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyFollowPacket [playerId=" + playerId + ", follow=" + follow + "]";
	}

}
