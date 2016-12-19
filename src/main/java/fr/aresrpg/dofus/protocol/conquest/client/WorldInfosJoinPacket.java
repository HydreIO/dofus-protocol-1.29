package fr.aresrpg.dofus.protocol.conquest.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class WorldInfosJoinPacket implements ClientPacket {

	@Override
	public void read(DofusStream stream) {
	}

	@Override
	public void write(DofusStream stream) {
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "WorldInfosJoinPacket []";
	}

}
