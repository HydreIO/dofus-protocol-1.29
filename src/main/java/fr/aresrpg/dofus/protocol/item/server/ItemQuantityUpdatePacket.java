/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemQuantityUpdatePacket implements ServerPacket {

	private int intemUid;
	private int amount;

	public ItemQuantityUpdatePacket() {
	}

	/**
	 * @param intemUid
	 * @param amount
	 */
	public ItemQuantityUpdatePacket(int intemUid, int amount) {
		this.intemUid = intemUid;
		this.amount = amount;
	}

	/**
	 * @return the intemUid
	 */
	public int getIntemUid() {
		return intemUid;
	}

	/**
	 * @param intemUid
	 *            the intemUid to set
	 */
	public void setIntemUid(int intemUid) {
		this.intemUid = intemUid;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public void read(DofusStream stream) {
		this.intemUid = stream.readInt();
		this.amount = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(intemUid).writeInt(amount);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ItemQuantityUpdatePacket [intemUid=" + intemUid + ", amount=" + amount + "]";
	}

}
