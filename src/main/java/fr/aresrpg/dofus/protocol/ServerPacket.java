package fr.aresrpg.dofus.protocol;

public interface ServerPacket extends Packet{
	void handleServer(ServerPacketHandler handler);

	@Override
	default void handle(PacketHandler handler){
		if(handler instanceof ServerPacketHandler)
			handleServer((ServerPacketHandler) handler);
		else
			throw new UnsupportedOperationException("The handle for the packet "  + ProtocolRegistry.getRegistry(getClass()) + " must be a instance of ServerPacketHandler");
	}
}
