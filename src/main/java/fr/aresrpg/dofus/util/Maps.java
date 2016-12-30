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

import fr.aresrpg.dofus.Constants;
import fr.aresrpg.dofus.structures.map.Cell;
import fr.aresrpg.dofus.structures.map.DofusMap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.IntStream;

public class Maps {
	private Maps() {
	}

	public static InputStream downloadMap(String assetsServer, int id, String subId) throws IOException {
		return new URL(assetsServer + Constants.MAP_PATH + id + '_' + subId + "X.swf").openStream();
	}

	public static InputStream downloadMap(int id, String subId) throws IOException {
		return downloadMap(Constants.DEFAULT_DATA_URL, id, subId);
	}

	/**
	 * Load a map with mapData
	 * 
	 * @param data
	 *            the datas
	 * @param decryptKey
	 *            the decrypt key
	 * @param replaceCell
	 *            a function to replace a cell if needed (for exemple you can replace a cell by a class which extend cell if this cell is a ressource)
	 * @return the map
	 */
	public static DofusMap loadMap(Map<String, Object> data, String decryptKey, Function<Cell, Cell> replaceCell) {
		String cellData = (String) data.get("mapData");
		String key = Crypt.prepareKey(decryptKey);
		cellData = Crypt.decipherData(cellData, key, Integer.parseInt(Character.toString(Crypt.checksum(key)), 16) * 2);
		int width = (int) data.get("width");
		Cell[] cells = Compressor.uncompressMap(cellData, width);
		IntStream.range(0, cells.length).forEach(i -> cells[i] = replaceCell.apply(cells[i]));
		return new DofusMap((int) data.get("id"), width,
				(int) data.get("height"), (int) data.get("musicId"),
				(int) data.get("capabilities"), (boolean) data.get("bOutdoor"),
				(int) data.get("backgroundNum"), cells);
	}

	public static int distance(int from, int to, int width) {
		int xto = getX(to, width);
		int xfrom = getX(from, width);
		int yto = getY(to, width);
		int yfrom = getY(from, width);
		return (xto - xfrom) * (xto - xfrom) + (yto - yfrom) * (yto - yfrom);
	}

	public static int distanceManathan(int from, int to, int width) {
		int xto = getX(to, width);
		int xfrom = getX(from, width);
		int yto = getY(to, width);
		int yfrom = getY(from, width);
		return Math.abs(xto - xfrom) + Math.abs(yto - yfrom);
	}

	public static int getY(int id, int width) {
		return (int) (id / (width - 0.5));
	}

	public static int getX(int id, int width) {
		return (int) ((id % (width - 0.5)) * 2);
	}

	public static int getId(int x, int y, int width) {
		return (int) (y * (width - 0.5) + x / 2.0);
	}

	public static int getId2(int x, int y, int width, int height) {
		int id = (width + height - 1 - x) * width;
		return y > height ? id - (y - height) * (width - 1) : y < height ? id + (height - y) * (width - 1) : 0;
	}

	public static int getX2(int id, int width, int height) {
		int n84 = width * (width * 2 - 2);
		if (id <= n84 && id % width == 0) return getXOfMultiple(id, width, height);
		int var = id;
		int count = 0;
		while (var % width != 0 || var > n84) {
			var -= (width * 2 - 1);
			count++;
			if (var < 0) return getXupper(id, width, height);
		}
		int xOfMultiple = getXOfMultiple(var, width, height);
		while (count-- > 0)
			xOfMultiple--;
		return xOfMultiple;
	}

	public static int getY2(int id, int width, int height) {
		int n84 = width * (width * 2 - 2);
		if (id <= n84 && id % width == 0) return height;
		int var = id;
		int count = 0;
		while (var % width != 0 || var > n84) {
			var -= (width * 2 - 1);
			count++;
			if (var < 0) return getYupper(id, width, height);
		}
		int yOfMultiple = height;
		while (count-- > 0)
			yOfMultiple--;
		return yOfMultiple;
	}

	private static int getXupper(int id, int width, int height) {
		int count = 0;
		int var = id;
		while (var % width != 0) {
			var += (width * 2 - 1);
			count++;
		}
		int xOfMultiple = getXOfMultiple(var, width, height);
		while (count-- > 0)
			xOfMultiple++;
		return xOfMultiple;
	}

	private static int getYupper(int id, int width, int height) {
		int count = 0;
		int var = id;
		while (var % width != 0) {
			var += (width * 2 - 1);
			count++;
		}
		int yOfMultiple = height;
		while (count-- > 0)
			yOfMultiple++;
		return yOfMultiple;
	}

	static int getXOfMultiple(int id, int width, int height) {
		return (width + height - 1) - id / width;
	}

	/**
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return true if the provided coordinate are inside the dofus map
	 */
	public static boolean isInMap(int x, int y, int width, int height) {
		if (x == 0 || y == 0) return false;
		if (leftUpCorner(x, y, width, height)) return x - width < y;
		else if (leftDownCorner(x, y, width)) return x + y > width;
		else if (rightUpCorner(x, y, width, height)) return (width + height - 1) * 2 - (x + y) >= width - 1;
		else if (rightDownCorner(x, y, width, height)) return y - width < x;
		return true;
	}

	private static boolean leftUpCorner(int x, int y, int width, int height) {
		return x > width && y < height;
	}

	private static boolean leftDownCorner(int x, int y, int width) {
		return x < width && y < width;
	}

	private static boolean rightUpCorner(int x, int y, int width, int height) {
		return x > height && y > height;
	}

	private static boolean rightDownCorner(int x, int y, int width, int height) {
		return x < height && y > width;
	}

	private static int roundDown(int numToRound, int multiple) {
		return numToRound - (numToRound % multiple);
	}

	private static int roundUp(int numToRound, int multiple) {
		if (multiple == 0)
			return numToRound;
		int mod = numToRound % multiple;
		return mod == 0 ? numToRound : numToRound + multiple - mod;
	}

	public static void main(String[] args) {
		int largeur = 7;
		int hauteur = 10;
		int id = 6;
		int x = getX2(id, largeur, hauteur);
		int y = getY2(id, largeur, hauteur);
		System.out.println("L'id " + id + " = " + x + "," + y);

		x = 1;
		y = 7;
	}

}
