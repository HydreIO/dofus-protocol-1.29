package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemRemovePacket implements ServerPacket {

	private int itemuid;

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
		this.itemuid = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(itemuid);
	}

	/**
	 * @return the itemuid
	 */
	public int getItemuid() {
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
