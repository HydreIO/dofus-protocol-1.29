package fr.aresrpg.dofus.protocol.guild.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.structures.guild.Guild;

public class GuildStatPacket implements ServerPacket {
	private Guild guild;

	@Override
	public void read(DofusStream stream) {
		guild = new Guild(stream.read(),
				stream.readIntRadix(36),
				stream.readIntRadix(36),
				stream.readIntRadix(36),
				stream.readIntRadix(36),
				stream.readIntRadix(36));
	}

	@Override
	public void write(DofusStream stream) {
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
