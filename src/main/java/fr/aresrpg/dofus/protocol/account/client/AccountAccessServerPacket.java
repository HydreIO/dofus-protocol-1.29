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

public class AccountAccessServerPacket implements ClientPacket {
	private int serverId;

	@Override
	public void read(DofusStream stream) {
		serverId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(serverId);
	}

	public int getServerId() {
		return serverId;
	}

	public AccountAccessServerPacket setServerId(int serverId) {
		this.serverId = serverId;
		return this;
	}

	@Override
	public String toString() {
		return "AccountAccessServerPacket(serverid:" + serverId + ")[" + getId() + "]";
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}
}
