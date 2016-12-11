package fr.aresrpg.dofus.protocol.chat.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class ChatUseSmileyPacket implements Packet{
	private int smileyId;

	@Override
	public void read(DofusStream stream) {
		smileyId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(smileyId);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getSmileyId() {
		return smileyId;
	}

	public ChatUseSmileyPacket setSmileyId(int smileyId) {
		this.smileyId = smileyId;
		return this;
	}

	@Override
	public String toString() {
		return "ChatUseSmileyPacket(smileyId=" + smileyId +
				")[" + getId() + ']';
	}
}
