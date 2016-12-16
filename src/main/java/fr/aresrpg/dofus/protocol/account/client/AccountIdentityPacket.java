/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

public class AccountIdentityPacket implements ClientPacket {
	private String identity;

	@Override
	public void read(DofusStream stream) {
		identity = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(identity);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	public String getIdentity() {
		return identity;
	}

	public AccountIdentityPacket setIdentity(String identity) {
		this.identity = identity;
		return this;
	}

	@Override
	public String toString() {
		return "AccountIdentityPacket(" + "identity:'" + identity + "\')[" + getId() + "]";
	}
}
