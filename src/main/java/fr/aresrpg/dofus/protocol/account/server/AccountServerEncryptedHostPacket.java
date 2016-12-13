package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.util.Crypt;

public class AccountServerEncryptedHostPacket implements ServerPacket {
	private String ip;
	private int port;
	private String ticketKey;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		this.ip = Crypt.decryptIp(data.substring(0, 8));
		this.port = Crypt.decryptPort(data.substring(8, 11));
		this.ticketKey = data.substring(11);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(ip + ticketKey);
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
