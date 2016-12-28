/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.ExchangeMove;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @since
 */
public class ExchangeMoveItemsPacket implements ClientPacket { // EMO <+/->id|amount<|price> ...

	private Set<MovedItem> items = new HashSet<>();

	public ExchangeMoveItemsPacket() {
	}

	/**
	 * @param items
	 */
	public ExchangeMoveItemsPacket(Set<MovedItem> items) {
		this.items = items;
	}

	@Override
	public void read(DofusStream stream) {
		while (stream.available() > 0) {
			String d = stream.read();
			if (!isBool(d.charAt(0))) return;
			String type = String.valueOf(d.charAt(0));
			String uid = d.substring(1);
			String amount = stream.read();
			String price = stream.available() > 0 ? isBool(stream.peek().charAt(0)) ? "" : stream.read() : "";
			items.add(recordItem(type, uid, amount, price));
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate((int) allocateCount());
		items.forEach(i -> i.write(stream));
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	private MovedItem recordItem(String... datas) {
		return new MovedItem(ExchangeMove.fromSymbol(datas[0].charAt(0)), Long.parseLong(datas[1]), Integer.parseInt(datas[2]), datas[3].equals("") ? -1 : Integer.parseInt(datas[3]));
	}

	private long allocateCount() {
		return items.stream().mapToInt(MovedItem::getAllocate).sum();
	}

	private boolean isBool(char c) {
		return c == '+' || c == '-';
	}

	@Override
	public String toString() {
		return "ExchangeMoveItemsPacket [items=" + items + "]";
	}

	/**
	 * @return the items
	 */
	public Set<MovedItem> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(Set<MovedItem> items) {
		this.items = items;
	}

	public static class MovedItem {
		private ExchangeMove type;
		private long itemUid;
		private int amount;
		private int price = -1;

		/**
		 * 
		 * @param move
		 * @param itemUid
		 * @param amount
		 * @param price
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
