package fr.aresrpg.dofus.protocol.guild.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.guild.GuildJoinError;

/**
 * 
 * @since
 */
public class GuildJoinErrorPacket implements ServerPacket { // gJE<c>

	private GuildJoinError error;

	@Override
	public void read(DofusStream stream) {
		this.error = GuildJoinError.valueOf(stream.readChar());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeChar(error.getCode());
	}

	/**
	 * @return the error
	 */
	public GuildJoinError getError() {
		return error;
	}

	/**
	 * @param error
	 *            the error to set
	 */
	public void setError(GuildJoinError error) {
		this.error = error;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

}
