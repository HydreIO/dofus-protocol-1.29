/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

public class AccountPseudoPacket implements ServerPacket {
	private String name;

	@Override
	public void read(DofusStream stream) {
		name = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(name);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public String getName() {
		return name;
	}

	public AccountPseudoPacket setName(String name) {
		this.name = name;
		return this;
	}

	@Override
	public String toString() {
		return "AccountPseudoPacket(name:" + name + ")[" + getId() + "]";
	}
}
