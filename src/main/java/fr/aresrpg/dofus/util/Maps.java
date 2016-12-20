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

	public static DofusMap loadMap(Map<String, Object> data, String decryptKey) {
		System.out.println("data = " + data);
		String cellData = (String) data.get("mapData");
		String key = Crypt.prepareKey(decryptKey);
		cellData = Crypt.decipherData(cellData, key, Integer.parseInt(Character.toString(Crypt.checksum(key)), 16) * 2);
		int width = (int) data.get("width");
		Cell[] cells = Compressor.uncompressMap(cellData, width);
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

}
