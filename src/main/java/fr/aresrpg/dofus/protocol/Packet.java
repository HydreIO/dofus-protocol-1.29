package fr.aresrpg.dofus.protocol;

import java.io.IOException;

public interface Packet {
	void read(DofusStream stream) throws IOException;

	void write(DofusStream stream) throws IOException;

	void handle(PacketHandler handler);

	default String getId() {
		return ProtocolRegistry.getRegistry(getClass()).getId();
	}
}
