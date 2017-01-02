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
	 * @return the map
	 */
	public static DofusMap loadMap(Map<String, Object> data, String decryptKey) {
		String cellData = (String) data.get("mapData");
		String key = Crypt.prepareKey(decryptKey);
		cellData = Crypt.decipherData(cellData, key, Integer.parseInt(Character.toString(Crypt.checksum(key)), 16) * 2);
		int width = (int) data.get("width");
		int height = (int) data.get("height");
		Cell[] cells = Compressor.uncompressMap(cellData);

		return new DofusMap((int) data.get("id"), width,
				height, (int) data.get("musicId"),
				(int) data.get("capabilities"), (boolean) data.get("bOutdoor"),
				(int) data.get("backgroundNum"), cells);
	}

	public static int distance(int from, int to, int width, int height) {
		int xto = getXRotated(to, width, height);
		int xfrom = getXRotated(from, width, height);
		int yto = getYRotated(to, width, height);
		int yfrom = getYRotated(from, width, height);
		return (xto - xfrom) * (xto - xfrom) + (yto - yfrom) * (yto - yfrom);
	}

	public static int distanceManathan(int from, int to, int width, int height) {
		int xto = getXRotated(to, width, height);
		int xfrom = getXRotated(from, width, height);
		int yto = getYRotated(to, width, height);
		int yfrom = getYRotated(from, width, height);
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

	private static final double PId4 = Math.PI/4;
	private static final double COS_PId4 = Math.cos(PId4);
	private static final double SIN_PId4 = Math.sin(PId4);
	private static final double COS_mPId4 = COS_PId4;
	private static final double SIN_mPId4 = -SIN_PId4;

	public static int getXRotated(int id, int width, int height) {
		int x = getX(id , width) - width;
		int y = getY(id , width) - height;
		return (int)Math.ceil((x*COS_PId4 - y*SIN_PId4) * 0.7) + width;
	}

	public static int getYRotated(int id, int width, int height) {
		int x = getX(id , width) - width;
		int y = getY(id , width) - height;

		return (int)Math.ceil((x*SIN_PId4 + y*COS_PId4) * 0.7) + height - 2;
	}

	public static int getIdRotated(int xRot, int yRot, int width, int height) {
		double xR = Math.ceil(xRot - width)/0.7;
		double xY = Math.ceil(yRot - height + 2)/0.7;
		int x = (int)(xR*COS_mPId4 - xY*SIN_mPId4) + width;
		int y = (int)(xR*SIN_mPId4 + xY*COS_mPId4) + height;
		return getId(x, y, width);
	}

	public static void main(String[] args) {
		int id = 186;
		int width = 16;
		int heigth = 17;
		System.out.println(getIdRotated(getXRotated(id , width , heigth) , getYRotated(id , width , heigth) , width , heigth));
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

}
