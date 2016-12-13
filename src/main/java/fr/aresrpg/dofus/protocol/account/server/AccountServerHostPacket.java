package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;

public class AccountServerHostPacket implements ServerPacket {
	private String ip;
	private int port;
	private String ticketKey;

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";");
		ip = data[0];
		port = Integer.parseInt(data[1]);
		ticketKey = data[2];
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
