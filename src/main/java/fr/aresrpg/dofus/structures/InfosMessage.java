/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures;

import java.util.Arrays;

/**
 * 
 * @since
 */
public enum InfosMessage {

	WELCOME(InfosMsgType.ERROR, 89),
	CURRENT_ADRESS(InfosMsgType.INFOS, 153),
	LAST_CONNECTION(InfosMsgType.INFOS, 152), // 0152
	RECOLTE_LOST_FULL_POD(InfosMsgType.INFOS, 144),
	FLOOD(InfosMsgType.INFOS, 115), // 0115
	START_SAVE(InfosMsgType.ERROR, 164),
	END_SAVE(InfosMsgType.ERROR, 165),
	CANT_WEAR_ITEM(InfosMsgType.ERROR, 19), // 119|43
	FIGHT_ATTRIBUTE_DENY_ACTIVE(InfosMsgType.INFOS, 95), // 095
	FIGHT_ATTRIBUTE_DENY_NOT_ACTIVE(InfosMsgType.INFOS, 96), // 096
	FIGHT_ATTRIBUTE_NEED_HELP_ACTIVE(InfosMsgType.INFOS, 103),
	FIGHT_ATTRIBUTE_NEED_HELP_NOT_ACTIVE(InfosMsgType.INFOS, 104),
	FIGHT_ATTRIBUTE_ALLOW_GROUP_ACTIVE(InfosMsgType.INFOS, 93), // 093
	FIGHT_ATTRIBUTE_ALLOW_GROUP_NOT_ACTIVE(InfosMsgType.INFOS, 94), // 094
	FIGHT_ATTRIBUTE_DENY_SPECTATE_ACTIVE(InfosMsgType.INFOS, 40), // 040
	FIGHT_ATTRIBUTE_DENY_SPECTATE_NOT_ACTIVE(InfosMsgType.INFOS, 39), // 039
	ZAAP_DISCOVERED(InfosMsgType.INFOS, 4), // 024
	TOO_MANY_MERCHANT(InfosMsgType.ERROR, 25), // 125;max merchands
	WAYPOINT_SAVED(InfosMsgType.INFOS, 6), // 06
	NOT_ENOUGH_KAMA(InfosMsgType.INFOS, 63), // 063
	TROP_CHARGE_(InfosMsgType.ERROR, 12),
	EARN_KAMAS(InfosMsgType.INFOS, 45); // 045;kamas

	private InfosMsgType type;
	private int id;

	/**
	 * @param type
	 * @param id
	 */
	private InfosMessage(InfosMsgType type, int id) {
		this.type = type;
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the type
	 */
	public InfosMsgType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(InfosMsgType type) {
		this.type = type;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	public static InfosMessage fromId(InfosMsgType type, int id) {
		return Arrays.stream(values()).filter(c -> c.getId() == id && c.getType() == type).findAny().orElse(null);
	}

}
