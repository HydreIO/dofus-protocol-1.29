package fr.aresrpg.dofus.structures;

/**
 * 
 * @since
 */
public enum InfosMsgType {

	INFOS,
	ERROR,
	PVP;

	public static InfosMsgType valueOf(int code) {
		return values()[code];
	}
}
