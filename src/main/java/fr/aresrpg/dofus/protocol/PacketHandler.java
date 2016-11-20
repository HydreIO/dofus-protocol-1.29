package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.server.HelloConnectionPacket;
import fr.aresrpg.dofus.protocol.server.HelloGamePacket;

public interface PacketHandler {
	void handle(HelloGamePacket helloGamePacket);

	void handle(HelloConnectionPacket helloConnectionPacket);
}
