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
		String cellData = (String) data.get("mapData");
		String key = Crypt.prepareKey(decryptKey);
		cellData = Crypt.decipherData(cellData, key, Integer.parseInt(Character.toString(Crypt.checksum(key)), 16) * 2);
		Cell[] cells = Compressor.uncompressMap(cellData);
		return new DofusMap((int) data.get("id"), (int) data.get("width"),
				(int) data.get("height"), (int) data.get("musicId"),
				(int) data.get("capabilities"), (boolean) data.get("bOutdoor"),
				(int) data.get("backgroundNum"), cells);
	}

	public static int distance(int from, int to, DofusMap map) {
		int xto = getLine(to, map.getWidth());
		int xfrom = getLine(from, map.getWidth());
		int yto = getColumn(to, map.getWidth());
		int yfrom = getColumn(from, map.getWidth());
		return (xto - xfrom) * (xto - xfrom) + (yto - yfrom) * (yto - yfrom);
	}

	public static int distanceManathan(int from, int to, DofusMap map) {
		int xto = getLine(to, map.getWidth());
		int xfrom = getLine(from, map.getWidth());
		int yto = getColumn(to, map.getWidth());
		int yfrom = getColumn(from, map.getWidth());
		return Math.abs(xto - xfrom) + Math.abs(yto - yfrom);
	}

	public static int getLine(int id, int width) {
		return (int) (id / (width - 0.5));
	}

	public static int getColumn(int id, int width) {
		return (int) ((id % (width - 0.5)) * 2);
	}

	public static int getId(int column, int line, int width) {
		return (int) (line * (width - 0.5) + column / 2.0);
	}

}
