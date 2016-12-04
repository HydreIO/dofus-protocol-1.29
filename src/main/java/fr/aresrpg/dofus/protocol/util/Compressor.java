package fr.aresrpg.dofus.protocol.util;

public class Compressor {
	private Compressor() {}

	public static String compressCellId(int cellId) {
		return "" + Crypt.hashToIndex((cellId & 4032) >> 6) +
				Crypt.hashToIndex(cellId & 63);
	}

	public static int decompressCellId(String cellId) {
		return (Crypt.indexOfHash(cellId.charAt(0)) << 6) + Crypt.indexOfHash(cellId.charAt(1));
	}
}
