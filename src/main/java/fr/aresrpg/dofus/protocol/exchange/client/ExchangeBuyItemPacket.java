package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ExchangeBuyItemPacket implements Packet {

	private int itemId;
	private int amount;

	@Override
	public void read(DofusStream stream) {
		this.itemId = stream.readInt();
		this.amount = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(itemId).writeInt(amount);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
