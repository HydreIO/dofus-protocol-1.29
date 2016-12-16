/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.hello.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

public class HelloConnectionPacket implements ServerPacket {
	private String hashKey;

	@Override
	public void read(DofusStream stream) {
		hashKey = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(hashKey);
	}

	public String getHashKey() {
		return hashKey;
	}

	public HelloConnectionPacket setHashKey(String hashKey) {
		this.hashKey = hashKey;
		return this;
	}

	@Override
	public String toString() {
		return "HelloConnection(hashkey:" + hashKey + ")[" + getId() + "]";
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

}
