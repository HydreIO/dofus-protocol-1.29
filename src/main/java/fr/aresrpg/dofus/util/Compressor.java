/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.util;

import static fr.aresrpg.dofus.util.Crypt.hashToIndex;
import static fr.aresrpg.dofus.util.Crypt.indexOfHash;

import fr.aresrpg.dofus.structures.map.Cell;

import java.util.Arrays;

public class Compressor {
	private Compressor() {
	}

	public static String compressCellId(int cellId) {
		return "" + hashToIndex((cellId & 0xFC0) >> 6) +
				hashToIndex(cellId & 0x3F);
	}

	public static int uncompressCellId(String cellId) {
		return (indexOfHash(cellId.charAt(0)) << 6) + indexOfHash(cellId.charAt(1));
	}

	public static String compressCells(Cell[] cells) {
		return Arrays.stream(cells).map(Compressor::compressCell).collect(StringBuilder::new, StringBuilder::append, StringBuilder::append).toString();
	}

	public static Cell[] uncompressCells(String d) {
		int[] data = new int[d.length()];
		for (int i = 0; i < data.length; i++)
			data[i] = indexOfHash(d.charAt(i));

		boolean active = (data[0] & 32) >> 5 == 1;
		Cell[] cells = new Cell[data.length / 10];
		for (int i = 0; i < data.length / 10; i++)
			cells[i] = uncompressCell(i, data, active);
		return cells;
	}

	public static String compressCell(Cell cell) {
		int[] data = new int[10];
		data[0] = (cell.isActive() ? 1 : 0) << 5;
		data[0] = data[0] | (cell.isLineOfSight() ? 1 : 0);
		data[0] = data[0] | (cell.getLayerGroundNum() & 1536) >> 6;
		data[0] = data[0] | (cell.getLayerObject1Num() & 8192) >> 11;
		data[0] = data[0] | (cell.getLayerObject2Num() & 8192) >> 12;
		data[1] = (cell.getLayerGroundRot() & 3) << 4;
		data[1] = data[1] | cell.getGroundLevel() & 15;
		data[2] = (cell.getMovement() & 7) << 3;
		data[2] = data[2] | cell.getLayerGroundNum() >> 6 & 7;
		data[3] = cell.getLayerGroundNum() & 63;
		data[4] = (cell.getGroundSlope() & 15) << 2;
		data[4] = data[4] | (cell.isLayerGroundFlip() ? 1 : 0) << 1;
		data[4] = data[4] | cell.getLayerObject1Num() >> 12 & 1;
		data[5] = cell.getLayerObject1Num() >> 6 & 63;
		data[6] = cell.getLayerObject1Num() & 63;
		data[7] = (cell.getLayerObject1Rot() & 3) << 4;
		data[7] = data[7] | (cell.isLayerObject1Flip() ? 1 : 0) << 3;
		data[7] = data[7] | (cell.isLayerObject2Flip() ? 1 : 0) << 2;
		data[7] = data[7] | (cell.isLayerObject2Interactive() ? 1 : 0) << 1;
		data[7] = data[7] | cell.getLayerObject2Num() >> 12 & 1;
		data[8] = cell.getLayerObject2Num() >> 6 & 63;
		data[9] = cell.getLayerObject2Num() & 63;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++)
			sb.append(Crypt.hashToIndex(data[i]));
		return sb.toString();
	}

	private static Cell uncompressCell(int i, int[] data, boolean active) {
		int id = i;
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
		return new Cell(id, active, lineOfSight, layerGroundRot, groundLevel, movement, layerGroundNum, groundSlope, layerGroundFlip, layerObject1Num, layerObject1Rot, layerObject1Flip,
				layerObject2Flip, layerObject2Interactive, layerObject2Num);
	}
}
