package fr.aresrpg.dofus.util;

import fr.aresrpg.dofus.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Lang {
	private Lang(){}

	public static Map<String , Integer> getLangVersion(String assetsServer, String lang) throws IOException {
		String[] data =  getText(new URL(assetsServer + Constants.LANG_VERSION_PATH.replace("{lang}" , lang))).substring(3)/*Remove &f=*/.split("\\|");
		Map<String , Integer> m = new HashMap<>();
		for(String d : data){
			String[] ld = d.split(",");
			System.out.println(d);
			m.put(ld[0] , Integer.parseInt(ld[2]));
		}
		return m;
	}

	public static Map<String , Integer> getLangVersion(String lang) throws IOException {
		return getLangVersion(Constants.DEFAULT_DATA_URL , lang);
	}

	public static InputStream getLangData(String assetsServer, String lang , int version , String type) throws IOException {
		return new URL(assetsServer + Constants.LANG_PATH
				.replace("{lang}" , lang)
				.replace("{version}", Integer.toString(version))
				.replace("{type}" , type)
		).openStream();
	}

	public static InputStream getLangData(String lang , int version , String type) throws IOException {
		return getLangData(Constants.DEFAULT_DATA_URL , lang , version , type);
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
}
