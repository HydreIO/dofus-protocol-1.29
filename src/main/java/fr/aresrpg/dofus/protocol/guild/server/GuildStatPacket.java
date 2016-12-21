/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.guild.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.guild.Guild;

public class GuildStatPacket implements ServerPacket {
	private Guild guild;

	@Override
	public void read(DofusStream stream) {
		if (stream.available() < 1) return;
		guild = new Guild(stream.read(),
				stream.readIntRadix(36),
				stream.readIntRadix(36),
				stream.readIntRadix(36),
				stream.readIntRadix(36),
				stream.readIntRadix(36));
	}

	@Override
	public void write(DofusStream stream) {
		if (guild == null) return;
		stream.allocate(6).write(guild.getName())
				.writeIntRadix(guild.getBackEmblem(), 36)
				.writeIntRadix(guild.getBackEmblemColor(), 36)
				.writeIntRadix(guild.getUpEmblem(), 36)
				.writeIntRadix(guild.getUpEmblemColor(), 36)
				.writeIntRadix(guild.getRawRights(), 36);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public Guild getGuild() {
		return guild;
	}

	public GuildStatPacket setGuild(Guild guild) {
		this.guild = guild;
		return this;
	}

	@Override
	public String toString() {
		return "GuildStatPacket(guild=" + guild + ")[" + getId() + ']';
	}
}
