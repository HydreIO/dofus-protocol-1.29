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

public enum Alignment {
	NEUTRE(-1),
	BONTA(1),
	BARAKMAR(2),
	MERCENAIRE(3);

	private int code;

	private Alignment(int code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	public static Alignment valueOf(int code) {
		if (code == 0) return NEUTRE;
		for (Alignment a : values())
			if (a.getCode() == code) return a;
		return null;
	}
}
