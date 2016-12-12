package fr.aresrpg.dofus.structures;

import java.util.Arrays;

/**
 * 
 * @since
 */
public enum InfosMessage {

	WELCOME(189),
	CURRENT_ADRESS(153),
	LAST_CONNECTION(152), // 0152
	MUTED(1124),
	FRIEND_CONNECT(143),
	FLOOD(115), // 0115
	YOU_ARE_AWAY(72), // 072
	START_SAVE(1164),
	END_SAVE(1165),
	CANT_WEAR_ITEM(119), // 119|43
	GUILD(155),
	FIGHT_ATTRIBUTE_DENY_ACTIVE(95), // 095
	FIGHT_ATTRIBUTE_DENY_NOT_ACTIVE(96), // 096
	FIGHT_ATTRIBUTE_NEED_HELP_ACTIVE(103),
	FIGHT_ATTRIBUTE_NEED_HELP_NOT_ACTIVE(104),
	FIGHT_ATTRIBUTE_ALLOW_GROUP_ACTIVE(93), // 093
	FIGHT_ATTRIBUTE_ALLOW_GROUP_NOT_ACTIVE(94), // 094
	FIGHT_ATTRIBUTE_DENY_SPECTATE_ACTIVE(40), // 040
	FIGHT_ATTRIBUTE_DENY_SPECTATE_NOT_ACTIVE(39), // 039
	ZAAP_DISCOVERED(24), // 024
	TOO_MANY_MERCHANT(125), // 125;max merchands
	EMPTY_STORE(123),
	WAYPOINT_SAVED(6), // 06
	NOT_ENOUGH_KAMA(63), // 063
	FULL_BAG(62), // 062
	EARN_KAMAS(45); // 045;kamas
	private int id;

	private InfosMessage(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	public static InfosMessage fromId(int id) {
		return Arrays.stream(values()).filter(c -> c.getId() == id).findAny().orElse(null);
	}

}
