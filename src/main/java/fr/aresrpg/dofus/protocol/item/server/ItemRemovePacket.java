/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemRemovePacket implements ServerPacket {

	private long itemuid;

	public ItemRemovePacket() {
	}

	/**
	 * @param itemuid
	 */
	public ItemRemovePacket(int itemuid) {
		this.itemuid = itemuid;
	}

	@Override
	public void read(DofusStream stream) {
		this.itemuid = stream.readLong();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeLong(itemuid);
	}

	/**
	 * @return the itemuid
	 */
	public long getItemuid() {
		return itemuid;
	}

	/**
	 * @param itemuid
	 *            the itemuid to set
	 */
	public void setItemuid(int itemuid) {
		this.itemuid = itemuid;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ItemRemovePacket [itemuid=" + itemuid + "]";
	}

}
