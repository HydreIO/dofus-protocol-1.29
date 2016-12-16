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

public class AccountTicketPacket implements ServerPacket {
	private String ticket;

	@Override
	public void read(DofusStream stream) {
		ticket = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(ticket);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public String getTicket() {
		return ticket;
	}

	public AccountTicketPacket setTicket(String ticket) {
		this.ticket = ticket;
		return this;
	}

	@Override
	public String toString() {
		return "AccountTicketPacket(ticket:'" + ticket + "\')[" + getId() + "]";
	}
}
