package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class GameActionACKPacket implements ClientPacket {
	private int actionId;

	@Override
	public void read(DofusStream stream) {
		actionId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(actionId);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	public GameActionACKPacket setActionId(int actionId) {
		this.actionId = actionId;
		return this;
	}

	public int getActionId() {
		return actionId;
	}

	@Override
	public String toString() {
		return "GameActionACKPacket(actionId=" + actionId + ")[" + getId() + ']';
	}
}
