/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.item.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemMovementPacket implements ClientPacket {

	private int itemid;
	private int position;
	private int quantity;

	public ItemMovementPacket() {
	}

	/**
	 * @param itemid
	 * @param position
	 * @param quantity
	 */
	public ItemMovementPacket(int itemid, int position, int quantity) {
		this.itemid = itemid;
		this.position = position;
		this.quantity = quantity;
	}

	@Override
	public void read(DofusStream stream) {
		this.itemid = stream.readInt();
		this.position = stream.readInt();
		this.quantity = stream.available() > 0 ? stream.readInt() : 1;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(quantity > 1 ? 3 : 2).writeInt(itemid).writeInt(position);
		if (quantity > 1) stream.writeInt(quantity);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ItemMovementFastInvPacket [itemid=" + itemid + ", position=" + position + ", quantity=" + quantity + "]";
	}

}
