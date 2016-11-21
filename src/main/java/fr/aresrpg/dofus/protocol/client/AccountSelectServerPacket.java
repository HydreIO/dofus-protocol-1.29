package fr.aresrpg.dofus.protocol.client;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class AccountSelectServerPacket implements Packet {
	public static int LIST_PERSONAGE = Integer.MIN_VALUE;

	private int server = LIST_PERSONAGE;

	@Override
	public void read(DofusStream stream) throws UnsupportedEncodingException, IOException {
		if (stream.available() == 1)
			server = stream.readInt();
		else
			server = LIST_PERSONAGE;
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		if (server != LIST_PERSONAGE)
			stream.allocate(1).writeInt(server);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ServerSelect(server:" + server + ")[" + getId() + "]";
	}
}
