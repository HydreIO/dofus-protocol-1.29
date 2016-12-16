package fr.aresrpg.dofus.structures;

/**
 * 
 * @since
 */
public enum CraftLoopEndResult {

	OK,
	INTERRUPT,
	FAIL,
	INVALID;
	public static CraftLoopEndResult valueOf(int code) {
		return values()[code - 1];
	}

}
