/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;

public class AccountServerHostPacket implements ServerPacket {
	private String ip;
	private int port;
	private String ticketKey;

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";");
		String[] first = data[0].split(":");
		ip = first[0];
		port = Integer.parseInt(first[1]);
		ticketKey = data[1];
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(ip + ':' + port + ';' + ticketKey);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public String getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

	public String getTicketKey() {
		return ticketKey;
	}

	public AccountServerHostPacket setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public AccountServerHostPacket setPort(int port) {
		this.port = port;
		return this;
	}

	public AccountServerHostPacket setTicketKey(String ticketKey) {
		this.ticketKey = ticketKey;
		return this;
	}

	@Override
	public String toString() {
		return "AccountServerHostPacket(ip:'" + ip + '\'' + "|port:" + port + "|ticketKey:'" + ticketKey + "\'})[" + getId() + "]";
	}
}
