package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.ExchangeMove;

import java.util.Arrays;

/**
 * 
 * @since
 */
public class ExchangeMovePacket implements Packet { // EMO <+/->id|amount<|price> ...

	private MovedItem[] items;

	/**
	 * @param type
	 * @param itemUUID
	 * @param amount
	 * @param price
	 */
	public ExchangeMovePacket(MovedItem... items) {
		this.items = items;
	}

	@Override
	public void read(DofusStream stream) {
		while (stream.available() > 0) {
			String d = stream.read();
			if (!isBool(d.charAt(0))) return;
			recordItem(String.valueOf(d.charAt(0)), d.substring(1), stream.read(), isBool(stream.peek().charAt(0)) ? "" : stream.read());
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate((int) allocateCount());
		Arrays.stream(getItems()).forEach(i -> i.write(stream));
	}

	@Override
	public void handle(PacketHandler handler) {
	}

	private MovedItem recordItem(String... datas) {
		return new MovedItem(ExchangeMove.fromSymbol(datas[0].charAt(0)), Long.parseLong(datas[1]), Integer.parseInt(datas[2]), datas[3].equals("") ? -1 : Integer.parseInt(datas[3]));
	}

	private long allocateCount() {
		return Arrays.stream(items).mapToInt(MovedItem::getAllocate).sum();
	}

	private boolean isBool(char c) {
		return c == '+' || c == '-';
	}

	/**
	 * @return the items
	 */
	public MovedItem[] getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(MovedItem... items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "ExchangeMovePacket [items=" + Arrays.toString(items) + "]";
	}

	public static class MovedItem {
		private ExchangeMove type;
		private long itemUid;
		private int amount;
		private int price;

		/**
		 * @param itemUid
		 * @param amount
		 */
		public MovedItem(ExchangeMove move, long itemUid, int amount, int price) {
			this.type = move;
			this.itemUid = itemUid;
			this.amount = amount;
			this.price = price;
		}

		public MovedItem(ExchangeMove move, long itemUid, int amount) {
			this(move, itemUid, amount, -1);
		}

		public void write(DofusStream stream) {
			stream.write(getType().getSymbol() + String.valueOf(getItemUid())).writeInt(getAmount());
			if (hasPrice()) stream.writeInt(getPrice());
		}

		/**
		 * @return the type
		 */
		public ExchangeMove getType() {
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(ExchangeMove type) {
			this.type = type;
		}

		public boolean hasPrice() {
			return getPrice() != -1;
		}

		/**
		 * @return the itemUid
		 */
		public long getItemUid() {
			return itemUid;
		}

		/**
		 * @param itemUid
		 *            the itemUid to set
		 */
		public void setItemUid(long itemUid) {
			this.itemUid = itemUid;
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

		public int getAllocate() {
			return hasPrice() ? 3 : 2;
		}

		@Override
		public String toString() {
			return "ItemWrapper [type=" + type + ", itemUid=" + itemUid + ", amount=" + amount + ", price=" + price + "]";
		}

	}

}
