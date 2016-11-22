package fr.aresrpg.dofus.protocol.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class DofusMapDownloader {
	public static final String MAP_URL = "http://staticns.ankama.com/dofus/gamedata/dofus/maps/";

	public static InputStream downloadMap(int id , String subId) throws IOException{
		return new URL(MAP_URL + id + '_' + subId + "X.swf").openStream();
	}
}
