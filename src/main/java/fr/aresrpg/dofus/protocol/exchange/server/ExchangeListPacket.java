/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.EquipmentPosition;
import fr.aresrpg.dofus.structures.Exchange;
import fr.aresrpg.dofus.structures.game.Effect;
import fr.aresrpg.dofus.structures.item.Item;

import java.util.*;

// HDV SELL : EL226848718;1;2473;7b#1#0#0#0d0+1,76#2#0#0#0d0+2,77#2#0#0#0d0+2,7c#1#0#0#0d0+1,7e#1#0#0#0d0+1;3000;350 // separateur '|'
// HDV SELL : EL227235876;1;2583;;1000;350|227241943;1;2302;;700;350 // separateur '|'
// SELF SHOP : EL217681226;13;993;6e#33#64#0#1d50+50;700 // separateur '|'
// SELF SHOP : EL217681225;6;996;259#1#1a9e#da;20000|217681226;13;993;6e#33#64#0#1d50+50;700 // separateur '|'
// NPC SHOP : EL2091;26c#0#0#2d // separateur '|'
// NPC SHOP : EL2086;8b#1#0#0#0d0+1|311 // separateur '|'
// BANK : ELOcf98d87~17f~2~~; // separateur ';' // O = Objet G = Kamas
// BANK : ELOcf98d87~17f~2~~;Ocf98d88~1b9~97~~; // separateur ';' // O = Objet G = Kamas
public class ExchangeListPacket implements ServerPacket {
	private Exchange invType;
	private List<Item> items = new ArrayList<>();
	private int kamas = -1;

	@Override
	public void read(DofusStream stream) {
		if (stream.available() < 1) return;
		char type = stream.peek().charAt(0);
		if (type == 'O' || type == 'G') {
			this.invType = Exchange.BANK;
			String[] datas = stream.read().split(";");
			for (int i = 0; i < datas.length; i++) {
				String loc11 = datas[i];
				char loc12 = loc11.charAt(0);
				String loc13 = loc11.substring(1);
				if (loc12 == 'O')
					items.add(Item.parseItem(loc13));
				else if (loc12 == 'G') this.kamas = Integer.parseInt(loc13);
			}
		} else {
			int length = stream.peek().split(";", -1).length;
			this.invType = length == 6 ? Exchange.BIG_STORE_SELL : length == 5 ? Exchange.SELF_MERCHANT_MODE : Exchange.NPC_SHOP;
			switch (invType) {
				case NPC_SHOP:
					while (stream.available() > 0) {
						String[] splited = stream.read().split(";", -1);
						int loc6 = Integer.parseInt(splited[0]);
						Effect[] eff = splited.length > 1 ? Item.parseEffects(splited[1]) : null;
						items.add(new Item(0, loc6, 0, EquipmentPosition.NO_EQUIPED, eff));
					}
					return;
				case SELF_MERCHANT_MODE:
					while (stream.available() > 0) {
						String[] splited = stream.read().split(";", -1);
						int loc18 = Integer.parseInt(splited[0]);
						int loc19 = Integer.parseInt(splited[1]);
						int loc20 = Integer.parseInt(splited[2]);
						String loc21 = splited[3];
						int loc22 = Integer.parseInt(splited[4]);
						items.add(new Item(loc18, loc20, loc19, EquipmentPosition.NO_EQUIPED, Item.parseEffects(loc21), loc22, -1));
					}
					return;
				case BIG_STORE_SELL:
					while (stream.available() > 0) {
						String[] splited = stream.read().split(";", -1);
						int loc27 = Integer.parseInt(splited[0]);
						int loc28 = Integer.parseInt(splited[1]);
						int loc29 = Integer.parseInt(splited[2]);
						String loc30 = splited[3];
						int loc31 = Integer.parseInt(splited[4]);
						int loc32 = Integer.parseInt(splited[5]);
						Item item = new Item(loc27, loc29, loc28, EquipmentPosition.NO_EQUIPED, Item.parseEffects(loc30), loc31, -1);
						item.setRemainingHours(loc32);
						items.add(item);
					}
					return;
				default:
					throw new IllegalArgumentException("CE PACKET PUE LA MERDE ET SI TU VOIT CETTE ERREUR TU SERA EN PLS CAR POUR LE FIX C'EST SUPER CHAUD !");
			}
		}
	}

	@Override
	public void write(DofusStream stream) {
		if (invType == null) return;
		switch (this.invType) {
			case BANK:
				StringJoiner joinerr = new StringJoiner(";");
				for (Item i : items)
					joinerr.add("O" + i.serialize());
				if (kamas != -1) joinerr.add("G" + kamas);
				stream.allocate(1).write(joinerr.toString() + ";");
				return;
			case NPC_SHOP:
				stream.allocate(items.size());
				for (Item item : items)
					stream.write(Integer.toString(item.getItemTypeId()) + (item.getEffects() == null ? "" : ";" + item.serializeEffects()));
				return;
			case SELF_MERCHANT_MODE:
				stream.allocate(items.size());
				for (Item item : items) {
					StringJoiner joiner = new StringJoiner(";");
					stream.write(joiner.add("" + item.getUid()).add("" + item.getQuantity()).add("" + item.getItemTypeId()).add("" + item.serializeEffects())
							.add("" + item.getPrice()).toString());
				}
				return;
			case BIG_STORE_SELL:
				stream.allocate(items.size());
				for (Item item : items) {
					StringJoiner joine = new StringJoiner(";");
					stream.write(joine.add("" + item.getUid()).add("" + item.getQuantity()).add("" + item.getItemTypeId()).add("" + Item.serializeEffects(item.getEffects()))
							.add("" + item.getPrice()).add("" + item.getRemainingHours()).toString());
				}
				return;
			default:
				break;
		}
	}

	/**
	 * @return the invType
	 */
	public Exchange getInvType() {
		return invType;
	}

	/**
	 * @param invType
	 *            the invType to set
	 */
	public void setInvType(Exchange invType) {
		this.invType = invType;
	}

	/**
	 * @param items
	 *            the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public int getKamas() {
		return kamas;
	}

	public ExchangeListPacket setKamas(int kamas) {
		this.kamas = kamas;
		return this;
	}

	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "ExchangeListPacket [invType=" + invType + ", items=" + items + ", kamas=" + kamas + "]";
	}

}
