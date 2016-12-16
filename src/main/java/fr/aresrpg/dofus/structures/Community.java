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

public enum Community {
	FRENCH(0),
	BRITISH(1),
	INTERNATIONAL(2),
	GERMAN(3),
	SPANISH(4),
	RUSSIAN(5),
	BRAZILIAN(6),
	NETHERLANDS(7),
	ITALIAN(8),
	JAPANESE(10),
	DEBUG(99);

	private final int code;

	Community(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static Community valueOf(int code) {
		for(Community c : values())
			if(c.code == code)
				return c;
		return null;
	}
}
