/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
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
