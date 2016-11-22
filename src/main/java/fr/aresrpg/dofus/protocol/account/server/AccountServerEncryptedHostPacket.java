package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.protocol.util.Crypt;

import java.io.IOException;

public class AccountServerEncryptedHostPacket implements Packet {
	private String ip;
	private int port;
	private String ticketKey;

	@Override
	public void read(DofusStream stream) throws IOException {
		String data = stream.read();
		this.ip = Crypt.decryptIp(data.substring(0, 8));
		this.port = Crypt.decryptPort(data.substring(8, 11));
		this.ticketKey = data.substring(11);
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).write(ip + ticketKey);
	}

	@Override
	public void handle(PacketHandler handler) {
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

	public AccountServerEncryptedHostPacket setIp(String ip) {
		this.ip = ip;
		return this;
	}

	public AccountServerEncryptedHostPacket setPort(int port) {
		this.port = port;
		return this;
	}

	public AccountServerEncryptedHostPacket setTicketKey(String ticketKey) {
		this.ticketKey = ticketKey;
		return this;
	}

	@Override
	public String toString() {
		return "AccountServerEncryptedHostPacket(ip:'" + ip + '\'' + "|port:" + port + "|ticketKey='" + ticketKey + "\')[" + getId() + "]";
	}
}
