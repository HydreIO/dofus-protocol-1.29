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

public enum Chat {
	INFO('i'),
	UNKNOWN('#'),
	COMMON('*'),
	PRIVATE('p'),
	GUILD('%'),
	PARTY('$'),
	PVP('!'),
	RECRUITMENT('?'),
	TRADE(':'),
	MEETIC('^'),
	ADMIN('@');

	private final char code;

	private Chat(char code) {
		this.code = code;
	}

	public char getCode() {
		return code;
	}

	public static Chat valueOf(char code) {
		for (Chat c : values())
			if (c.code == code)
				return c;
		return null;
	}
}
