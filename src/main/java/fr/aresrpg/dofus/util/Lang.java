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

import java.io.*;
import java.net.URL;
import java.util.*;
import java.util.Map.Entry;

public class Lang {
	private Lang() {
	}

	public static void main(String[] args) throws IOException {
		System.out.println(getLangVersion("fr"));
		Map<String, Object> datas = getDatas("fr", "spells");
		int i = 0;
		for (Entry<String, Object> e : datas.entrySet()) {
			if (!(e.getValue() instanceof Map)) continue;
			Map<String, Object> map = (Map) e.getValue();
			for (Entry<String, Object> en : map.entrySet()) {
				StringBuilder sb = new StringBuilder();
				if (en.getValue() instanceof Object[]) sb.append(Arrays.deepToString((Object[]) en.getValue()));
				else sb.append(String.valueOf(en.getValue()));
				System.out.println(en.getKey() + "=" + sb.toString());
				if (en.getValue().toString().contains("Enflammée")) return;
			}
		}
	}

	public static Map<String, Integer> getLangVersion(String assetsServer, String lang) throws IOException {
		String[] data = getText(new URL(assetsServer + Constants.LANG_VERSION_PATH.replace("{lang}", lang))).substring(3)/* Remove &f= */.split("\\|");
		Map<String, Integer> m = new HashMap<>();
		for (String d : data) {
			String[] ld = d.split(",");
			m.put(ld[0], Integer.parseInt(ld[2]));
		}
		return m;
	}

	public static Map<String, Integer> getLangVersion(String lang) throws IOException {
		return getLangVersion(Constants.DEFAULT_DATA_URL, lang);
	}

	public static InputStream getLangData(String assetsServer, String lang, int version, String type) throws IOException {
		return new URL(assetsServer + Constants.LANG_PATH
				.replace("{lang}", lang)
				.replace("{version}", Integer.toString(version))
				.replace("{type}", type)).openStream();
	}

	public static InputStream getLangData(String lang, int version, String type) throws IOException {
		return getLangData(Constants.DEFAULT_DATA_URL, lang, version, type);
	}

	private static String getText(URL url) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));

		StringBuilder response = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null)
			response.append(inputLine);

		in.close();

		return response.toString();
	}

	public static Map<String, Object> getDatas(String lang, String data) throws IOException {
		return SwfVariableExtractor.extractVariable(getLangData(lang, getLangVersion(lang).get(data), data));
	}

}
