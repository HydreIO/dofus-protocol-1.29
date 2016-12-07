package fr.aresrpg.dofus.util;

import fr.aresrpg.dofus.structures.map.Cell;

import static fr.aresrpg.dofus.util.Crypt.hashToIndex;
import static fr.aresrpg.dofus.util.Crypt.indexOfHash;

public class Compressor {
	private Compressor() {}

	public static String compressCellId(int cellId) {
		return "" + hashToIndex((cellId & 0xFC0) >> 6) +
				hashToIndex(cellId & 0x3F);
	}

	public static int uncompressCellId(String cellId) {
		return (indexOfHash(cellId.charAt(0)) << 6) + indexOfHash(cellId.charAt(1));
	}

	public static Cell[] uncompressMap(String d) {
		int[] data = new int[d.length()];
		for (int i = 0; i < data.length; i++)
			data[i] = indexOfHash(d.charAt(i));

		Cell[] cells = new Cell[data.length / 10];
		for (int i = 0; i < data.length / 10; i++) {
			int index = i * 10;
			boolean lineOfSight = (data[index] & 1) == 1;
			int layerGroundRot = (data[index + 1] & 48) >> 4;
			int groundLevel = data[index + 1] & 15;
			int movement = (data[index + 2] & 56) >> 3;
			int layerGroundNum = ((data[index] & 24) << 6) + ((data[index + 2] & 7) << 6) + data[index + 3];
			int groundSlope = (data[index + 4] & 60) >> 2;
			boolean layerGroundFlip = (data[index + 4] & 2) >> 1 == 1;
			int layerObject1Num = ((data[index] & 4) << 11) + ((data[index + 4] & 1) << 12) + (data[index + 5] << 6) + data[index + 6];
			int layerObject1Rot = (data[index + 7] & 48) >> 4;
			boolean layerObject1Flip = (data[index + 7] & 8) >> 3 == 1;
			boolean layerObject2Flip = (data[index + 7] & 4) >> 2 == 1;
			boolean layerObject2Interactive = (data[index + 7] & 2) >> 1 == 1;
			int layerObject2Num = ((data[index] & 2) << 12) + ((data[index + 7] & 1) << 12) + (data[index + 8] << 6) + data[index + 9];

			cells[i] = new Cell(lineOfSight, layerGroundRot, groundLevel, movement,
					layerGroundNum, groundSlope, layerGroundFlip, layerObject1Num,
					layerObject1Rot, layerObject1Flip, layerObject2Flip, layerObject2Interactive,
					layerObject2Num);
		}
		return cells;
	}
}
