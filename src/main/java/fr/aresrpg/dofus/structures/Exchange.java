package fr.aresrpg.dofus.structures;

public enum Exchange {
	NPC_SHOP,
	EXCHANGE,
	EXCHANGE_2,
	CRAFT,
	PLAYER_SHOP,
	BANK,
	PLAYER_SHOP_MODIFIER,
	TAX_COLLECTOR_STORAGE,
	EXCHANGE_3,
	BIG_STORE_SELL,
	BIG_STORE_BUY,
	SECURE_CRAFT,
	SECURE_CRAFT_2,
	CRAFTER_LIST,
	MOUNT_STORAGE,
	MOUNT_STORAGE_2,
	EXCHANGE_4,
	EXCHANGE_5;

	public static Exchange valueOf(int code) {
		if(code >= 0 && code < values().length)
			return values()[code];
		else
			return null;
	}
}
