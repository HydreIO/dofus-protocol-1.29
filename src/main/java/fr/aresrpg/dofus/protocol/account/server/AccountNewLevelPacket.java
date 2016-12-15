package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class AccountNewLevelPacket implements ServerPacket {

	private int newlvl;

	public AccountNewLevelPacket() {
	}

	/**
	 * @param newlvl
	 */
	public AccountNewLevelPacket(int newlvl) {
		this.newlvl = newlvl;
	}

	@Override
	public void read(DofusStream stream) {
		this.newlvl = stream.readInt();
	}

	/**
	 * @return the newlvl
	 */
	public int getNewlvl() {
		return newlvl;
	}

	/**
	 * @param newlvl
	 *            the newlvl to set
	 */
	public void setNewlvl(int newlvl) {
		this.newlvl = newlvl;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(newlvl);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "AccountNewLevelPacket [newlvl=" + newlvl + "]";
	}

}
