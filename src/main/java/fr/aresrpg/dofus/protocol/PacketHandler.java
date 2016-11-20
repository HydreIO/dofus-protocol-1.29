package fr.aresrpg.dofus.protocol;

import fr.aresrpg.dofus.protocol.client.AccountAuthPacket;
import fr.aresrpg.dofus.protocol.server.account.AccountLoginErrPacket;
import fr.aresrpg.dofus.protocol.server.account.AccountLoginOkPacket;
import fr.aresrpg.dofus.protocol.server.hello.HelloConnectionPacket;
import fr.aresrpg.dofus.protocol.server.hello.HelloGamePacket;

public interface PacketHandler {
	void register(DofusConnection<?> connection);

	void handle(HelloGamePacket helloGamePacket);

	void handle(HelloConnectionPacket helloConnectionPacket);

	void handle(AccountAuthPacket accountAuthPacket);

	void handle(AccountLoginErrPacket accountLoginErrPacket);

	void handle(AccountLoginOkPacket accountLoginOkPacket);
}
