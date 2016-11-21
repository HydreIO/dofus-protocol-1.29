package fr.aresrpg.dofus.protocol.server.account;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

/**
 * 
 * @since
 */
public class AccountGameServerPacket implements Packet {

	private String cryptedIp;
	private String guid;

	@Override
	public void read(DofusStream stream) throws IOException {
		this.cryptedIp = stream.read();
		this.guid = stream.read();
	}

	/**
	 * @param cryptedIp
	 *            the cryptedIp to set
	 */
	public AccountGameServerPacket setCryptedIp(String cryptedIp) {
		this.cryptedIp = cryptedIp;
		return this;
	}

	/**
	 * @return the cryptedIp
	 */
	public String getCryptedIp() {
		return cryptedIp;
	}

	/**
	 * @param guid
	 *            the guid to set
	 */
	public AccountGameServerPacket setGuid(String guid) {
		this.guid = guid;
		return this;
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(2).write(cryptedIp).write(guid);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
