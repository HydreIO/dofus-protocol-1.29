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

public class AccountRestrictionsPacket implements ServerPacket {
	private int restrictions;

	@Override
	public void read(DofusStream stream) {
		restrictions = stream.readIntRadix(36);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeIntRadix(restrictions , 36);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public int getRestrictions() {
		return restrictions;
	}

	public AccountRestrictionsPacket setRestrictions(int restrictions) {
		this.restrictions = restrictions;
		return this;
	}

	@Override
	public String toString() {
		return "AccountRestrictionsPacket(restrictions=" + restrictions +
				")[" + getId() + ']';
	}
}
