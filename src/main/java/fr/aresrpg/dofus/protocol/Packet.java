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

public interface Packet {
	void read(DofusStream stream);

	void write(DofusStream stream);

	void handle(PacketHandler handler);

	default String getId() {
		return ProtocolRegistry.getRegistry(getClass()).getId();
	}
}
