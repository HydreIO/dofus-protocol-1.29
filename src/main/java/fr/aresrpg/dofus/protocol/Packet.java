package fr.aresrpg.dofus.protocol;

public interface Packet {
	void read(DofusStream stream);

	void write(DofusStream stream);

	void handle(PacketHandler handler);

	default String getId() {
		return ProtocolRegistry.getRegistry(getClass()).getId();
	}
}
