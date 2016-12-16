/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.guild;

import java.util.StringJoiner;

public class Guild {
	private String name;
	private int backEmblem;
	private int backEmblemColor;
	private int upEmblem;
	private int upEmblemColor;
	private int rights;

	public Guild(String name, int backEmblem, int backEmblemColor, int upEmblem, int upEmblemColor, int rights) {
		this.name = name;
		this.backEmblem = backEmblem;
		this.backEmblemColor = backEmblemColor;
		this.upEmblem = upEmblem;
		this.upEmblemColor = upEmblemColor;
		this.rights = rights;
	}

	public String getName() {
		return name;
	}

	public int getBackEmblem() {
		return backEmblem;
	}

	public int getBackEmblemColor() {
		return backEmblemColor;
	}

	public int getUpEmblem() {
		return upEmblem;
	}

	public int getUpEmblemColor() {
		return upEmblemColor;
	}

	public int getRawRights() {
		return rights;
	}

	public boolean haveRights(GuildRight right) {
		return GuildRight.LEADER.isPresent(rights) || right.isPresent(rights);
	}

	@Override
	public String toString() {
		StringJoiner rights = new StringJoiner(", ");
		if(haveRights(GuildRight.LEADER))
			rights.add("LEADER");
		else
			for(int i = 1 ; i < GuildRight.values().length ; i++)
				rights.add(GuildRight.values()[i].toString());
		return "Guild{" +
				"name='" + name + '\'' +
				", backEmblem=" + backEmblem +
				", backEmblemColor=" + backEmblemColor +
				", upEmblem=" + upEmblem +
				", upEmblemColor=" + upEmblemColor +
				", rights=" + rights +
				'}';
	}
}
