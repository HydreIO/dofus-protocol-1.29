package fr.aresrpg.dofus.structures.character;

import java.util.Arrays;

public class AvailableCharacter {
	private int id;
	private String pseudo;
	private int level;
	private int gfxId;
	private int color1;
	private int color2;
	private int color3;
	private int[] accessories;
	private boolean merchant;
	private int serverId;
	private boolean isDead;
	private int deathCount;
	private int lvlMax;

	public AvailableCharacter(int id, String pseudo, int level, int gfxId, int color1, int color2, int color3,
		int[] accessories, boolean merchant, int serverId, boolean isDead, int deathCount,
		int lvlMax) {
		this.id = id;
		this.pseudo = pseudo;
		this.level = level;
		this.gfxId = gfxId;
		this.color1 = color1;
		this.color2 = color2;
		this.color3 = color3;
		this.accessories = accessories;
		this.merchant = merchant;
		this.serverId = serverId;
		this.isDead = isDead;
		this.deathCount = deathCount;
		this.lvlMax = lvlMax;
	}

	/**
	 * @return the isDead
	 */
	public boolean isDead() {
		return isDead;
	}

	/**
	 * @return the merchant
	 */
	public boolean isMerchant() {
		return merchant;
	}

	/**
	 * @return the accessories
	 */
	public int[] getAccessories() {
		return accessories;
	}

	/**
	 * @return the color1
	 */
	public int getColor1() {
		return color1;
	}

	/**
	 * @return the color2
	 */
	public int getColor2() {
		return color2;
	}

	/**
	 * @return the color3
	 */
	public int getColor3() {
		return color3;
	}

	/**
	 * @return the deathCount
	 */
	public int getDeathCount() {
		return deathCount;
	}

	/**
	 * @return the gfxId
	 */
	public int getGfxId() {
		return gfxId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @return the lvlMax
	 */
	public int getLvlMax() {
		return lvlMax;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @return the serverId
	 */
	public int getServerId() {
		return serverId;
	}

	@Override
	public String toString() {
		return "AvailableCharacter[" +
				"id=" + id +
				"|pseudo='" + pseudo + '\'' +
				"|level=" + level +
				"|gfxId=" + gfxId +
				"|color1=" + color1 +
				"|color2=" + color2 +
				"|color3=" + color3 +
				"|accessories=" + Arrays.toString(accessories) +
				"|merchant=" + merchant +
				"|serverId=" + serverId +
				"|isDead=" + isDead +
				"|deathCount=" + deathCount +
				"|lvlMax=" + lvlMax +
				']';
	}
}
