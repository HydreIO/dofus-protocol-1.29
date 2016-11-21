package fr.aresrpg.dofus.protocol.server.account;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

/**
 * 
 * @since
 */
public class AccountYGameServerPacket implements Packet {

	private String ip;
	private int port;
	private String guid;

	@Override
	public void read(DofusStream stream) throws IOException {
		this.ip = stream.read();
		this.port = stream.readInt();
		this.guid = stream.read();
	}

	/**
	 * @param ip
	 *            the ip to set
	 */
	public AccountYGameServerPacket setIp(String ip) {
		this.ip = ip;
		return this;
	}

	/**
	 * @param port
	 *            the port to set
	 */
	public AccountYGameServerPacket setPort(int port) {
		this.port = port;
		return this;
	}

	/**
	 * @param guid
	 *            the guid to set
	 */
	public AccountYGameServerPacket setGuid(String guid) {
		this.guid = guid;
		return this;
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(3).write(ip).writeInt(port).write(guid);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
