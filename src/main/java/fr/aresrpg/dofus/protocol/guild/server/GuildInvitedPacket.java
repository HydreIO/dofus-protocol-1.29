package fr.aresrpg.dofus.protocol.guild.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class GuildInvitedPacket implements ServerPacket {

	private int sprite;
	private String player;
	private String guild;

	@Override
	public void read(DofusStream stream) {
		this.sprite = stream.readInt();
		this.player = stream.read();
		this.guild = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(3).writeInt(sprite).write(player).write(guild);
	}

	/**
	 * @return the sprite
	 */
	public int getSprite() {
		return sprite;
	}

	/**
	 * @param sprite
	 *            the sprite to set
	 */
	public void setSprite(int sprite) {
		this.sprite = sprite;
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

	/**
	 * @return the guild
	 */
	public String getGuild() {
		return guild;
	}

	/**
	 * @param guild
	 *            the guild to set
	 */
	public void setGuild(String guild) {
		this.guild = guild;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

}
