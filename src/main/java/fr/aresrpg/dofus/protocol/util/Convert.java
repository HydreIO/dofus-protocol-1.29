package fr.aresrpg.dofus.protocol.util;

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

	public static String fromInt(int val) {
		return val == 0 ? "" : Integer.toHexString(val);
	}
}
