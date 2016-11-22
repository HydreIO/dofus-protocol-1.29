package fr.aresrpg.dofus.protocol.mount.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.protocol.util.Convert;

import java.io.IOException;

public class MountXpPacket implements Packet{

	private int percent;

	@Override
	public void read(DofusStream stream) throws IOException {
		percent = Convert.toInt(stream.read() , 0);
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).writeInt(percent);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getPercent() {
		return percent;
	}

	public MountXpPacket setPercent(int percent) {
		this.percent = percent;
		return this;
	}

	@Override
	public String toString() {
		return "MountXpPacket(percent=" + percent + ")[" + getId() + ']';
	}
}
