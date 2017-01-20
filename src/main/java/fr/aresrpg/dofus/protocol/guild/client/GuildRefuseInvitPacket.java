package fr.aresrpg.dofus.protocol.guild.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class GuildRefuseInvitPacket implements ClientPacket {

	private long playerid;

	@Override
	public void read(DofusStream stream) {
		this.playerid = stream.readLong();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeLong(playerid);
	}

	/**
	 * @return the playerid
	 */
	public long getPlayerid() {
		return playerid;
	}

	/**
	 * @param playerid
	 *            the playerid to set
	 */
	public void setPlayerid(long playerid) {
		this.playerid = playerid;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

}
