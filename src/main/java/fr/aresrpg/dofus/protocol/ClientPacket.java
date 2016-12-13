package fr.aresrpg.dofus.protocol;

public interface ClientPacket extends Packet{
	void handleClient(ClientPacketHandler handler);

	@Override
	default void handle(PacketHandler handler) {
		if(handler instanceof ClientPacketHandler)
			handleClient((ClientPacketHandler) handler);
		else
			throw new UnsupportedOperationException("The handle for the packet "  + ProtocolRegistry.getRegistry(getClass()) + " must be a instance of ClientPacketHandler");
	}
}
