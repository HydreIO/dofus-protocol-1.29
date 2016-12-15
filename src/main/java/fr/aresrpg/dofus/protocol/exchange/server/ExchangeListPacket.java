package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.item.Item;

import java.util.Arrays;
import java.util.StringJoiner;

public class ExchangeListPacket implements ServerPacket {
	private Item[] items;
	private int kamas = -1;

	@Override
	public void read(DofusStream stream) {
		items = new Item[stream.available()];
		for (int i = 0; i < items.length; i++) {
			String[] data = stream.read().split(";");
			if (data[0].contains("~")) { // 1
				items = new Item[data.length];
				int y = 0;
				for (int e = 0; e < data.length; e++) {
					String d = data[e];
					switch (d.charAt(0)) {
						case 'O':
							items[y++] = Item.parseItem(d.substring(1));
							break;
						case 'G':
							kamas = Integer.parseInt(d.substring(1));
							break;
					}
				}
				items = Arrays.copyOf(items, y);
				if (stream.available() != 0)
					throw new IllegalStateException();
			} else if (data.length == 2) { // 2
				items[i] = new Item(0, Integer.parseInt(data[0]), -1,
						-1, Item.parseEffects(data[1]));
			} else if (data.length == 5 || data.length == 6) { // 3
				items[i] = new Item(
						Integer.parseInt(data[0]),
						Integer.parseInt(data[2]),
						Integer.parseInt(data[1]),
						-1,
						Item.parseEffects(data[3]),
						Integer.parseInt(data[4]),
						data.length == 6 ? Integer.parseInt(data[5]) : -1);
			} else
				throw new IllegalStateException();
		}
	}

	@Override
	public void write(DofusStream stream) {
		if (items.length == 0)
			return;
		if (items[0].getPrice() != -1) { // 3
			stream.allocate(items.length);
			for (Item item : items)
				stream.write(item.getUid() + ';' +
						item.getQuantity() + ';' +
						item.getItemTypeId() + ';' +
						Item.serializeEffects(item.getEffects()) +
						item.getPrice() + ';' +
						(item.getSkin() == -1 ? "" : item.getSkin()));

		} else if (items[0].getUid() == 0) { // 2
			stream.allocate(items.length);
			for (Item item : items)
				stream.write(item.getItemTypeId() + ';' + Item.serializeEffects(item.getEffects()));
		} else { // 3
			StringJoiner joiner = new StringJoiner(";");
			for (Item item : items)
				joiner.add('O' + Item.serializeItem(item));
			joiner.add("G" + kamas);
			stream.allocate(1).write(joiner.toString());
		}
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public Item[] getItems() {
		return items;
	}

	public ExchangeListPacket setItems(Item[] items) {
		this.items = items;
		return this;
	}

	public int getKamas() {
		return kamas;
	}

	public ExchangeListPacket setKamas(int kamas) {
		this.kamas = kamas;
		return this;
	}
}
