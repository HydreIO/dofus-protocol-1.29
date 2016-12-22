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
import fr.aresrpg.dofus.structures.item.Item;

import java.util.*;

/**
 * 
 * @since
 */
public class ItemAddOkPacket implements ServerPacket {

	private Set<Item> items = new HashSet<>();

	public ItemAddOkPacket() {
	}

	/**
	 * @param items
	 */
	public ItemAddOkPacket(Set<Item> items) {
		this.items = items;
	}

	/**
	 * @return the items
	 */
	public Set<Item> getItems() {
		return items;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(Set<Item> items) {
		this.items = items;
	}

	@Override
	public void read(DofusStream stream) {
		String[] loc4 = stream.read().split("\\*");
		for (int loc5 = 0; loc5 < loc4.length; loc5++) {
			String loc6 = loc4[loc5];
			char loc7 = loc6.charAt(0);
			loc6 = loc6.substring(1);
			if (loc7 == 'O') {
				String[] loc8 = loc6.split(";");
				for (int loc9 = 0; loc9 < loc8.length; loc9++)
					if (loc8[loc9].contains("~")) {
						Item parsed = Item.parseItem(loc8[loc9]);
						if (parsed != null) items.add(parsed);
					}
			}
		}
	}

	@Override
	public void write(DofusStream stream) {
		StringJoiner jn = new StringJoiner(";");
		for (Item item : items)
			jn.add(item.serializeItem(item));
		stream.allocate(1).write('O' +jn.toString());
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ItemAddOkPacket [items=" + items + "]";
	}

}
