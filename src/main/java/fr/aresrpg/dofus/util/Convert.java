/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.util;

public class Convert {
	public static int toInt(String value, int def, int base) {
		if (value.isEmpty())
			return def;
		else
			return Integer.parseInt(value, base);
	}

	public static int toInt(String value, int def) {
		return toInt(value, def, 10);
	}

	public static int toInt(String value) {
		return toInt(value, 0, 10);
	}

	public static int toHexInt(String value) {
		return toInt(value, 0, 16);
	}

	public static String fromHexInt(int val) {
		return val == 0 ? "" : Integer.toHexString(val);
	}

	public static String toDofusString(int val) {
		return val == 0 ? "" : Integer.toString(val);
	}
}
