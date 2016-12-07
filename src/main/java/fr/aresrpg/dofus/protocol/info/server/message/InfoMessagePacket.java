package fr.aresrpg.dofus.protocol.info.server.message;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class InfoMessagePacket implements Packet{
	private int messageId;
	@Override
	public void read(DofusStream stream) throws IOException {
		String[] data = stream.read().split(";");
		this.messageId = Integer.parseInt(data[0]);
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).writeInt(messageId);
	}

	public int getMessageId() {
		return messageId;
	}

	public InfoMessagePacket setMessageId(int messageId) {
		this.messageId = messageId;
		return this;
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "InfoMessagePacket()[" + getId() + ']';
	}
}
