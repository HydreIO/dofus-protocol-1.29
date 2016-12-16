/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.structures.server.Server;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountServerListPacket implements ServerPacket {
	private long subscriptionDuration;
	private Map<Integer, Integer> characters;

	@Override
	public void read(DofusStream stream) {
		subscriptionDuration = stream.readLong();
		characters = new HashMap<>();
		while (stream.available() != 0) {
			String[] data = stream.read().split(",");
			characters.put(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1 + characters.size()).writeLong(subscriptionDuration);
		characters.forEach((k, v) -> stream.write(k + "," + v));
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public long getSubscriptionDuration() {
		return subscriptionDuration;
	}

	public Map<Integer, Integer> getCharacters() {
		return characters;
	}

	@Override
	public String toString() {
		return "AccountServerListPacket(subscriptionDuration:" + subscriptionDuration + "|characters:"
				+ characters.entrySet().stream().map(e -> Server.fromId(e.getKey()) + "=" + e.getValue()).collect(Collectors.joining(", ")) + ")[" + getId() + "]";
	}
}
