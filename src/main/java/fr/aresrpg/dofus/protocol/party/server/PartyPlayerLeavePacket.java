package fr.aresrpg.dofus.protocol.party.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class PartyPlayerLeavePacket implements ServerPacket {

	private String player;

	@Override
	public void read(DofusStream stream) {
		this.player = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(player);
	}

	/**
	 * @return the player
	 */
	public String getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(String player) {
		this.player = player;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyPlayerLeavePacket [player=" + player + "]";
	}

}
