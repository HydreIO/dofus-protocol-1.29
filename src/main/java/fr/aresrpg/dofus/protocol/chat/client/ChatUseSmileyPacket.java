package fr.aresrpg.dofus.protocol.chat.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class ChatUseSmileyPacket implements ClientPacket{
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
	public void handleClient(ClientPacketHandler handler) {
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
