/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.item.ItemCategory;

/**
 * 
 * @since
 */
public class ExchangeHdvPacket implements ClientPacket {

	private AskHdvType askType;
	private ItemCategory type;
	private int itemId;
	private BuyedItem item;

	/**
	 * @param type
	 */
	public ExchangeHdvPacket(ItemCategory type) {
		this.type = type;
		this.askType = AskHdvType.TYPE;
	}

	public ExchangeHdvPacket(AskHdvType type, int itemId) {
		if (type == AskHdvType.TYPE) throw new IllegalArgumentException("The asktype can't be TYPE in this constructor !");
		this.itemId = itemId;
		this.askType = type;
	}

	public ExchangeHdvPacket(ItemCategory type, int itemId) {
		this.itemId = itemId;
		this.type = type;
		this.askType = AskHdvType.SEARCH;
	}

	public ExchangeHdvPacket(BuyedItem item) {
		this.askType = AskHdvType.BUY;
		this.item = item;
	}

	/**
	 * @return the item
	 */
	public BuyedItem getItem() {
		return item;
	}

	/**
	 * @param item
	 *            the item to set
	 */
	public void setItem(BuyedItem item) {
		this.item = item;
	}

	/**
	 * @param itemId
	 *            the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
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
		switch (getAskType()) {
			case LIST:
			case MIDDLE_PRICE:
				this.itemId = val;
				break;
			case SEARCH:
				this.itemId = stream.readInt();
				// no break
			case TYPE:
				this.type = ItemCategory.valueOf(val);
				break;
			case BUY:
				this.item = new BuyedItem(val, stream.readInt(), stream.readInt());
				break;
			default:
				break;
		}
	}

	@Override
	public void write(DofusStream stream) {
		switch (getAskType()) {
			case LIST:
			case MIDDLE_PRICE:
				stream.allocate(1);
				writeCodeAnd(getItemId(), stream);
				return;
			case SEARCH:
				stream.allocate(2);
				writeCodeAnd(getType().getValue(), stream);
				stream.writeInt(getItemId());
				return;
			case TYPE:
				stream.allocate(1);
				writeCodeAnd(getType().getValue(), stream);
				return;
			case BUY:
				stream.allocate(3);
				writeCodeAnd(item.getId(), stream);
				stream.writeInt(item.getQuantityIndex()).writeInt(item.getPrice());
				return;
		}
	}

	private void writeCodeAnd(int val, DofusStream stream) {
		stream.write(getAskType().getCode() + String.valueOf(val));
	}

	/**
	 * @return the type
	 */
	public ItemCategory getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(ItemCategory type) {
		this.type = type;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeHdvPacket [askType=" + askType + ", type=" + type + ", itemId=" + itemId + ", item=" + item + "]";
	}

	public static enum AskHdvType {
		TYPE('T'),
		LIST('l'),
		BUY('B'),
		SEARCH('S'),
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

	public static class BuyedItem {
		private int id;
		private int quantityIndex;
		private int price;

		/**
		 * @param id
		 * @param quantityIndex
		 * @param price
		 */
		public BuyedItem(int id, int quantityIndex, int price) {
			super();
			this.id = id;
			this.quantityIndex = quantityIndex;
			this.price = price;
		}

		/**
		 * @return the id
		 */
		public int getId() {
			return id;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		/**
		 * @return the quantityIndex
		 */
		public int getQuantityIndex() {
			return quantityIndex;
		}

		/**
		 * @param quantityIndex
		 *            the quantityIndex to set
		 */
		public void setQuantityIndex(int quantityIndex) {
			this.quantityIndex = quantityIndex;
		}

		/**
		 * @return the price
		 */
		public int getPrice() {
			return price;
		}

		/**
		 * @param price
		 *            the price to set
		 */
		public void setPrice(int price) {
			this.price = price;
		}

		@Override
		public String toString() {
			return "BuyedItem [id=" + id + ", quantityIndex=" + quantityIndex + ", price=" + price + "]";
		}

	}

}
