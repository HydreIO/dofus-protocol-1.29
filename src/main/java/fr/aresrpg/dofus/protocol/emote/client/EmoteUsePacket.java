/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.emote.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Emotes;

public class EmoteUsePacket implements ClientPacket {
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

	public Emotes getEmot() {
		return Emotes.valueOf(getEmoteId());
	}

	public EmoteUsePacket setEmot(Emotes emot) {
		return setEmoteId(emot.getCode());
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
