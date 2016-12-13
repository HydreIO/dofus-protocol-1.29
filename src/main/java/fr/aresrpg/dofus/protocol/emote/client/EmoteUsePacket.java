package fr.aresrpg.dofus.protocol.emote.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class EmoteUsePacket implements ClientPacket{
	private int emoteId;

	@Override
	public void read(DofusStream stream) {
		emoteId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(emoteId);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	public int getEmoteId() {
		return emoteId;
	}

	public EmoteUsePacket setEmoteId(int emoteId) {
		this.emoteId = emoteId;
		return this;
	}

	@Override
	public String toString() {
		return "EmoteUsePacket(emoteId=" + emoteId +
				")[" + getId() + ']';
	}
}
