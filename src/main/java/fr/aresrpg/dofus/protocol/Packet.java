package fr.aresrpg.dofus.protocol;

public interface Packet {
	void read(DofusStream stream);

	void write(DofusStream stream);

	void handle(PacketHandler handler);

	default String getId() {
		for(ProtocolRegistry registry : ProtocolRegistry.values()){
			if(registry.getPacket() == getClass())
				return registry.getId();
		}
		return null;
	}
}
