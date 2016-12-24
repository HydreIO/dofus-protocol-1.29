/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.character;

import fr.aresrpg.dofus.structures.item.Accessory;
import fr.aresrpg.dofus.util.Convert;
import fr.aresrpg.dofus.util.StringJoiner;

/**
 * 
 * @since
 */
public class PartyMember {

	private int id;
	private String name;
	private String gfxFile;
	private int color1;
	private int color2;
	private int color3;
	private int life;
	private int maxLife;
	private int lvl;
	private int initiative;
	private int prospection;
	private int side;
	private Accessory[] accessories;

	public PartyMember(int id, String name, String gfxFile, int color1, int color2, int color3, int life, int maxLife, int lvl, int initiative, int prospection, int side, Accessory[] accessories) {
		this.id = id;
		this.name = name;
		this.gfxFile = gfxFile;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.life = life;
		this.maxLife = maxLife;
		this.lvl = lvl;
		this.initiative = initiative;
		this.prospection = prospection;
		this.side = side;
		this.accessories = accessories;
	}

	public static PartyMember parseMember(String data) {
		String[] loc7 = data.split(";");
		int id = Integer.parseInt(loc7[0]);
		if (loc7.length == 1) return new PartyMember(id, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, null);
		String name = loc7[1];
		String loc10 = loc7[2];
		int color1 = Convert.toHexInt(loc7[3]);
		int color2 = Convert.toHexInt(loc7[4]);
		int color3 = Convert.toHexInt(loc7[5]);
		String loc14 = loc7[6];
		String[] lifeinfo = loc7[7].split(",");
		int life = Integer.parseInt(lifeinfo[0]);
		int maxLife = Integer.parseInt(lifeinfo[1]);
		int lvl = Integer.parseInt(loc7[8]);
		int init = Integer.parseInt(loc7[9]);
		int pp = Integer.parseInt(loc7[10]);
		int side = Integer.parseInt(loc7[11]);
		return new PartyMember(id, name, "clips/sprites/" + loc10 + ".swf", color1, color2, color3, life, maxLife, lvl, init, pp, side, Accessory.parseFew(loc14));
	}

	public String serialize() {
		StringJoiner joiner = new StringJoiner(";");
		if (name == null) return "" + getId();
		joiner.add(getId())
				.add(getName())
				.add(getGfxFile().substring(14, getGfxFile().lastIndexOf(".swf")))
				.add(getColor1() == -1 ? "-1" : Integer.toHexString(getColor1())).add(getColor2() == -1 ? "-1" : Integer.toHexString(getColor2()))
				.add(getColor3() == -1 ? "-1" : Integer.toHexString(getColor3()))
				.add(Accessory.serializeFew(getAccessories()))
				.add(life + "," + maxLife).add(Integer.toString(lvl)).add(Integer.toString(initiative)).add(Integer.toString(prospection)).add(Integer.toString(side));
		return joiner.toString();
	}

	/**
	 * @return the accessories
	 */
	public Accessory[] getAccessories() {
		return accessories;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the gfxFile
	 */
	public String getGfxFile() {
		return gfxFile;
	}

	/**
	 * @param gfxFile
	 *            the gfxFile to set
	 */
	public void setGfxFile(String gfxFile) {
		this.gfxFile = gfxFile;
	}

	/**
	 * @return the color1
	 */
	public int getColor1() {
		return color1;
	}

	/**
	 * @param color1
	 *            the color1 to set
	 */
	public void setColor1(int color1) {
		this.color1 = color1;
	}

	/**
	 * @return the color2
	 */
	public int getColor2() {
		return color2;
	}

	/**
	 * @param color2
	 *            the color2 to set
	 */
	public void setColor2(int color2) {
		this.color2 = color2;
	}

	/**
	 * @return the color3
	 */
	public int getColor3() {
		return color3;
	}

	/**
	 * @param color3
	 *            the color3 to set
	 */
	public void setColor3(int color3) {
		this.color3 = color3;
	}

	/**
	 * @return the life
	 */
	public int getLife() {
		return life;
	}

	/**
	 * @param life
	 *            the life to set
	 */
	public void setLife(int life) {
		this.life = life;
	}

	/**
	 * @return the lvl
	 */
	public int getLvl() {
		return lvl;
	}

	/**
	 * @param lvl
	 *            the lvl to set
	 */
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}

	/**
	 * @return the initiative
	 */
	public int getInitiative() {
		return initiative;
	}

	/**
	 * @param initiative
	 *            the initiative to set
	 */
	public void setInitiative(int initiative) {
		this.initiative = initiative;
	}

	/**
	 * @return the prospection
	 */
	public int getProspection() {
		return prospection;
	}

	/**
	 * @param prospection
	 *            the prospection to set
	 */
	public void setProspection(int prospection) {
		this.prospection = prospection;
	}

	/**
	 * @return the side
	 */
	public int getSide() {
		return side;
	}

	/**
	 * @param side
	 *            the side to set
	 */
	public void setSide(int side) {
		this.side = side;
	}

	@Override
	public String toString() {
		return "PartyMember [id=" + id + ", name=" + name + ", gfxFile=" + gfxFile + ", color1=" + color1 + ", color2=" + color2 + ", color3=" + color3 + ", life=" + life + ", lvl=" + lvl
				+ ", initiative=" + initiative + ", prospection=" + prospection + ", side=" + side + "]";
	}

}
