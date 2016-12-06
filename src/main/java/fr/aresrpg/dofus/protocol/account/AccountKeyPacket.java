package fr.aresrpg.dofus.protocol.account;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class AccountKeyPacket implements Packet {
	protected int key;
	protected String data;

	@Override
	public void read(DofusStream stream) throws IOException {
		String buf = stream.read();
		key = Integer.parseInt(String.valueOf(buf.charAt(0)), 16);
		data = buf.substring(1);
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).write(key + data);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getKey() {
		return key;
	}

	public String getData() {
		return data;
	}

	public AccountKeyPacket setKey(int key) {
		this.key = key;
		return this;
	}

	public AccountKeyPacket setData(String data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return "AccountKeyPacket(" + "key:" + key + ", data:'" + data + "\'}[" + getId() + "]";
	}
}
