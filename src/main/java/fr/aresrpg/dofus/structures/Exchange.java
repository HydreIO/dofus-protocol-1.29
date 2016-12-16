/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures;

public enum Exchange {
	NPC_SHOP(0),
	EXCHANGE(1),
	EXCHANGE_2(2),
	CRAFT(3),
	PLAYER_SHOP(4),
	BANK(5),
	PLAYER_SHOP_MODIFIER(6),
	TAX_COLLECTOR_STORAGE(8),
	EXCHANGE_3(9),
	BIG_STORE_SELL(10),
	BIG_STORE_BUY(11),
	SECURE_CRAFT(12),
	SECURE_CRAFT_2(13),
	CRAFTER_LIST(14),
	MOUNT_STORAGE(15),
	MOUNT_PARK(16),
	EXCHANGE_4(17),
	EXCHANGE_5(18);

	private final int code;

	Exchange(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Exchange valueOf(int code) {
		for(Exchange c : values())
			if(c.code == code)
				return c;
		return null;
	}
}
