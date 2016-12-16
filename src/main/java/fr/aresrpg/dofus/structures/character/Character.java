/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.character;

import fr.aresrpg.dofus.structures.item.Item;

import java.util.Arrays;

public class Character{
	private int id;
	private String pseudo;
	private int level;
	private int sex;
	private int guild;
	private int gfxId;
	private int color1;
	private int color2;
	private int color3;
	private Item[] items;

	public Character(int id, String pseudo, int level, int sex, int guild, int gfxId, int color1, int color2, int color3, Item[] items) {
		this.id = id;
		this.pseudo = pseudo;
		this.level = level;
		this.sex = sex;
		this.guild = guild;
		this.gfxId = gfxId;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.items = items;
	}

	public int getId() {
		return id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public int getLevel() {
		return level;
	}

	public int getSex() {
		return sex;
	}

	public int getGuild() {
		return guild;
	}

	public int getGfxId() {
		return gfxId;
	}

	public int getColor1() {
		return color1;
	}

	public int getColor2() {
		return color2;
	}

	public int getColor3() {
		return color3;
	}

	public Item[] getItems() {
		return items;
	}

	@Override
	public String toString() {
		return "Character{" +
				"id=" + id +
				", pseudo='" + pseudo + '\'' +
				", level=" + level +
				", sex=" + sex +
				", guild=" + guild +
				", gfxId=" + gfxId +
				", color1=" + color1 +
				", color2=" + color2 +
				", color3=" + color3 +
				", items=" + Arrays.toString(items) +
				'}';
	}
}
