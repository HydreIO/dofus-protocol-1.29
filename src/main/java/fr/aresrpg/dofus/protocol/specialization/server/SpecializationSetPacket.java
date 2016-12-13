package fr.aresrpg.dofus.protocol.specialization.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

public class SpecializationSetPacket implements ServerPacket {

	private int specialization;

	@Override
	public void read(DofusStream stream) {
		specialization = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(specialization);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
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
