/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus;

import javafx.scene.image.Image;

public class Constants {
	public static final String DEFAULT_DATA_URL = "http://staticns.ankama.com/dofus/gamedata/dofus/";
	public static final String MAP_PATH = "maps/";
	public static final String LANG_VERSION_PATH = "lang/versions_{lang}.txt";
	public static final String LANG_PATH = "lang/swf/{type}_{lang}_{version}.swf";

	public static final Image wheat = new Image("https://i.imgur.com/LzUi53W.png");
	public static final Image chanvre = new Image("https://i.imgur.com/UWJKJwc.png");
	public static final Image npc = new Image("https://i.imgur.com/aqwfqXZ.png");
	public static final Image mob = new Image("https://i.imgur.com/KG6PUJ0.png");
	public static final Image player = new Image("https://i.imgur.com/CMBwr3R.png");

	public static final int[] TELEPORT_TEXTURES = { 1030, 1029, 1764, 2298, 745 };
}
