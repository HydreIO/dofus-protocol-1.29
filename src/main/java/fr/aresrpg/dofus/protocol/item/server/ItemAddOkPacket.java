package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.item.Item;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @since
 */
public class ItemAddOkPacket implements ServerPacket {

	private Set<Item> items = new HashSet<>();

	@Override
	public void read(DofusStream stream) {
		String[] loc4 = stream.read().split("\\*");
		for (int loc5 = 0; loc5 < loc4.length; loc5++) {
			String loc6 = loc4[loc5];
			char loc7 = loc6.charAt(0);
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
		for(Item item:items) {
			
		}
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

}
