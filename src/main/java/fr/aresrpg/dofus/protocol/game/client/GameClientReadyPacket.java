package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class GameClientReadyPacket implements Packet{
	private boolean ready;

	@Override
	public void read(DofusStream stream) {
		ready = stream.read().equals("1");
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(ready ? "1" : "2");
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public boolean isReady() {
		return ready;
	}

	public GameClientReadyPacket setReady(boolean ready) {
		this.ready = ready;
		return this;
	}

	@Override
	public String toString() {
		return "GameClientReadyPacket(ready=" + ready +
				")[" + getId() + ']';
	}
}
