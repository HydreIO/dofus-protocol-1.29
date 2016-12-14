package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.item.ItemTypeEnum;

/**
 * 
 * @since
 */
public class ExchangeAskHdvPacket implements ClientPacket {

	private AskHdvType askType;
	private ItemTypeEnum type;
	private int itemId;

	/**
	 * @param type
	 */
	public ExchangeAskHdvPacket(ItemTypeEnum type) {
		this.type = type;
		this.askType = AskHdvType.TYPE;
	}

	public ExchangeAskHdvPacket(AskHdvType type, int itemId) {
		if (type == AskHdvType.TYPE) throw new IllegalArgumentException("The asktype can't be TYPE in this constructor !");
		this.itemId = itemId;
		this.askType = type;
	}

	/**
	 * @return the askType
	 */
	public AskHdvType getAskType() {
		return askType;
	}

	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}

	@Override
	public void read(DofusStream stream) {
		String d = stream.read();
		char c = d.charAt(0);
		int val = Integer.parseInt(d.substring(1));
		this.askType = AskHdvType.valueOf(c);
		if (this.askType == AskHdvType.TYPE) this.type = ItemTypeEnum.valueOf(val);
		else this.itemId = val;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(getAskType().getCode() + String.valueOf(getAskType() == AskHdvType.TYPE ? getType().getValue() : getItemId()));
	}

	/**
	 * @return the type
	 */
	public ItemTypeEnum getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ItemTypeEnum type) {
		this.type = type;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	public static enum AskHdvType {
		TYPE('T'),
		LIST('l'),
		MIDDLE_PRICE('P');

		private char code;

		/**
		 * @param code
		 */
		private AskHdvType(char code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public char getCode() {
			return code;
		}

		public static AskHdvType valueOf(char c) {
			if (c == 'T') return TYPE;
			else if (c == 'l') return LIST;
			else if (c == 'P') return MIDDLE_PRICE;
			else throw new IllegalArgumentException("The char '" + c + "' is invalid !");
		}

	}

}
