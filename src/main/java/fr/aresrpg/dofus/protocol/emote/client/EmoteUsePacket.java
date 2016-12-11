package fr.aresrpg.dofus.protocol.emote.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class EmoteUsePacket implements Packet{
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
	public void handle(PacketHandler handler) {
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
