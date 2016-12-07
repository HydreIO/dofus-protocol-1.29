package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class GameActionACKPacket implements Packet{
	private int actionId;

	@Override
	public void read(DofusStream stream) throws IOException {
		actionId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).writeInt(actionId);
	}

	@Override
	public void handle(PacketHandler handler) {
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
