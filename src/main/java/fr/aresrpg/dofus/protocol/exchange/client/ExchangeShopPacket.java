package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Exchange;

/**
 * 
 * @since
 */
public class ExchangeShopPacket implements ClientPacket {

	private Exchange type;

	/**
	 * @param type
	 */
	public ExchangeShopPacket(Exchange type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public Exchange getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Exchange type) {
		this.type = type;
	}

	@Override
	public void read(DofusStream stream) {
		this.type = Exchange.valueOf(stream.readInt());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(type.getCode());
	}

	@Override
	public String toString() {
		return "ExchangeShopPacket [type=" + type + "]";
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

}
