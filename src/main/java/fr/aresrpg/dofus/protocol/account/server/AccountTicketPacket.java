package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class AccountTicketPacket implements Packet{
	private String ticket;

	@Override
	public void read(DofusStream stream) throws IOException {
		ticket = stream.read();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).write(ticket);
	}

	@Override
	public void handle(PacketHandler handler) {
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
		return "AccountTicketPacket{" +
				"ticket='" + ticket + '\'' +
				'}';
	}
}
