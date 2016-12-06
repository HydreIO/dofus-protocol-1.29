package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountLoginOkPacket implements Packet {
	private boolean isAdmin;

	@Override
	public void read(DofusStream stream) {
		isAdmin = stream.readInt() == 1;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(isAdmin ? 1 : 0);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	/**
	 * @param isAdmin
	 *            the isAdmin to set
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	/**
	 * @return the isAdmin
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	@Override
	public String toString() {
		return "AccountLoginOkPacket(isAdmin:" + isAdmin + ")[" + getId() + "]";
	}
}
