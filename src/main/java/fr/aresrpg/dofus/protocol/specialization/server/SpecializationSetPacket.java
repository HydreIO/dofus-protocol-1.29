package fr.aresrpg.dofus.protocol.specialization.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class SpecializationSetPacket implements Packet{

	private int specialization;

	@Override
	public void read(DofusStream stream) throws IOException {
		specialization = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).writeInt(specialization);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getSpecialization() {
		return specialization;
	}

	public SpecializationSetPacket setSpecialization(int specialization) {
		this.specialization = specialization;
		return this;
	}

	@Override
	public String toString() {
		return "SpecializationSetPacket(specialization=" + specialization + ")["+ getId() + ']';
	}
}
