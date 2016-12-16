/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.chat.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class BasicUseSmileyPacket implements ClientPacket{
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

	public BasicUseSmileyPacket setSmileyId(int smileyId) {
		this.smileyId = smileyId;
		return this;
	}

	@Override
	public String toString() {
		return "ChatUseSmileyPacket(smileyId=" + smileyId +
				")[" + getId() + ']';
	}
}
