package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.server.Server;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class AccountServerListPacket implements Packet {
	private int subscriptionDuration;
	private Map<Integer, Integer> characters;

	@Override
	public void read(DofusStream stream) throws IOException {
		subscriptionDuration = stream.readInt();
		characters = new HashMap<>();
		while (stream.available() != 0) {
			String[] data = stream.read().split(",");
			characters.put(Integer.parseInt(data[0]), Integer.parseInt(data[1]));
		}
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1 + characters.size()).writeInt(subscriptionDuration);
		characters.forEach((k, v) -> stream.write(k + "," + v));
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getSubscriptionDuration() {
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
