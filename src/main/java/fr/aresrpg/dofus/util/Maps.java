package fr.aresrpg.dofus.util;

import fr.aresrpg.dofus.Constants;
import fr.aresrpg.dofus.structures.map.Cell;
import fr.aresrpg.dofus.structures.map.DofusMap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;

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

	public static int getLine(int id, int width) {
		return (int) (id / (width - 0.5));
	}

	public static int getColumn(int id, int width) {
		return (int) ((id % (width - 0.5)) * 2);
	}

	public static int getId(int column, int line, int width) {
		return (int) (line * (width - 0.5) + column / 2.0);
	}

	public static void main(String[] args) {
		System.out.println("line = " + line(293, 15, 17));
		System.out.println(293 % 14);
	}

	static int line(int id, int width, int height) {
		if (id == 0) return id;
		int index = 0;
		int max = width + height - 2;
		int sub = width - 1;
		int val = width * 2 - 1;
		if (id <= val) return id % sub == 0 ? sub : id % sub;
		while (id / sub >= max) {
			id -= width;
			index++;
		}
		while (id % sub != 0) {
			id -= width;
			index++;
		}
		return width - 1 + index;
	}

	public static void mazin(String[] args) {
		int largeur = 15;
		int hauteur = 17;
		int max = largeur + hauteur - 1; // 31
		int firstin = -1;
		int second = -1;
		int same = hauteur - largeur + 1; // hauteur tjr positive donc ntm

		List<String> ss = new ArrayList<>();
		for (int x = 0; x < max; x++) {
			for (int y = 0; y < max; y++) {
				if (y <= largeur - firstin && y >= largeur - second) {
					ss.add(x + " " + y);
				}
			}
			firstin--;
			second++;
		}
		ss.forEach(System.out::println);
		System.out.println("Length = " + ss.size());
	}
}
