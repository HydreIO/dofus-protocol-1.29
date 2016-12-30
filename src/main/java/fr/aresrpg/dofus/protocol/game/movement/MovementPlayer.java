/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
/*******************************************************************************
 * BotFather (C) - Dofus 1.29
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.movement;

import fr.aresrpg.dofus.structures.Orientation;
import fr.aresrpg.dofus.structures.game.Alignement;
import fr.aresrpg.dofus.structures.item.Accessory;

import java.util.Arrays;

/**
 * 
 * @since
 */
public class MovementPlayer implements MovementAction {

	private int id;
	private String pseudo;
	private int sprite;
	private int cell;
	private int scaleX;
	private int scaleY;
	private Orientation orientation;
	private int sex;
	private Alignement alignement;
	private int rank;
	private PlayerInFight playerInFight;
	private PlayerOutsideFight playerOutsideFight;

	/**
	 * 
	 * @param id
	 * @param pseudo
	 * @param sprite
	 * @param cell
	 * @param scaleX
	 * @param scaleY
	 * @param orientation
	 * @param sex
	 * @param alignement
	 * @param rank
	 * @param playerInFight
	 * @param playerOutsideFight
	 */
	public MovementPlayer(int id, String pseudo, int sprite, int cell, int scaleX, int scaleY, Orientation orientation, int sex, Alignement alignement, int rank, PlayerInFight playerInFight,
		PlayerOutsideFight playerOutsideFight) {
		this.id = id;
		this.pseudo = pseudo;
		this.sprite = sprite;
		this.cell = cell;
		this.scaleX = scaleX;
		this.scaleY = scaleY;
		this.orientation = orientation;
		this.sex = sex;
		this.alignement = alignement;
		this.rank = rank;
		this.playerInFight = playerInFight;
		this.playerOutsideFight = playerOutsideFight;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		return obj instanceof MovementPlayer && ((MovementPlayer) obj).getId() == getId();
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @return the playerInFight
	 */
	public PlayerInFight getPlayerInFight() {
		return playerInFight;
	}

	/**
	 * @return the playerOutsideFight
	 */
	public PlayerOutsideFight getPlayerOutsideFight() {
		return playerOutsideFight;
	}

	/**
	 * @param playerInFight
	 *            the playerInFight to set
	 */
	public void setPlayerInFight(PlayerInFight playerInFight) {
		this.playerInFight = playerInFight;
	}

	/**
	 * @param playerOutsideFight
	 *            the playerOutsideFight to set
	 */
	public void setPlayerOutsideFight(PlayerOutsideFight playerOutsideFight) {
		this.playerOutsideFight = playerOutsideFight;
	}

	/**
	 * @return the sprite
	 */
	public int getSprite() {
		return sprite;
	}

	/**
	 * @param sprite
	 *            the sprite to set
	 */
	public void setSprite(int sprite) {
		this.sprite = sprite;
	}

	/**
	 * @return the cell
	 */
	public int getCellId() {
		return cell;
	}

	/**
	 * @param cell
	 *            the cell to set
	 */
	public void setCellId(int cell) {
		this.cell = cell;
	}

	/**
	 * @return the scaleX
	 */
	public int getScaleX() {
		return scaleX;
	}

	/**
	 * @param scaleX
	 *            the scaleX to set
	 */
	public void setScaleX(int scaleX) {
		this.scaleX = scaleX;
	}

	/**
	 * @return the scaleY
	 */
	public int getScaleY() {
		return scaleY;
	}

	/**
	 * @param scaleY
	 *            the scaleY to set
	 */
	public void setScaleY(int scaleY) {
		this.scaleY = scaleY;
	}

	/**
	 * @return the orientation
	 */
	public Orientation getOrientation() {
		return orientation;
	}

	/**
	 * @param orientation
	 *            the orientation to set
	 */
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex
	 *            the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * @return the alignement
	 */
	public Alignement getAlignement() {
		return alignement;
	}

	/**
	 * @param alignement
	 *            the alignement to set
	 */
	public void setAlignement(Alignement alignement) {
		this.alignement = alignement;
	}

	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}

	/**
	 * @param rank
	 *            the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean isFight() {
		return getPlayerInFight() != null;
	}

	@Override
	public String toString() {
		return "MovementPlayer [id=" + id + ", pseudo=" + pseudo + ", sprite=" + sprite + ", cell=" + cell + ", scaleX=" + scaleX + ", scaleY=" + scaleY + ", orientation=" + orientation + ", sex="
				+ sex + ", alignement=" + alignement + ", rank=" + rank + ", playerInFight=" + playerInFight + ", playerOutsideFight=" + playerOutsideFight + "]";
	}

	public static class PlayerInFight {
		private int lvl;
		private int color1;
		private int color2;
		private int color3;
		private Accessory[] accessories;
		private int life;
		private int pa;
		private int pm;
		private int[] resis;
		private int team;

		/**
		 * @param lvl
		 * @param color1
		 * @param color2
		 * @param color3
		 * @param accessories
		 * @param life
		 * @param pa
		 * @param pm
		 * @param resis
		 * @param team
		 */
		public PlayerInFight(int lvl, int color1, int color2, int color3, Accessory[] accessories, int life, int pa, int pm, int[] resis, int team) {
			this.lvl = lvl;
			this.color1 = color1;
			this.color2 = color2;
			this.color3 = color3;
			this.accessories = accessories;
			this.life = life;
			this.pa = pa;
			this.pm = pm;
			this.resis = resis;
			this.team = team;
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
		 * @return the accessories
		 */
		public Accessory[] getAccessories() {
			return accessories;
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
		 * @return the pa
		 */
		public int getPa() {
			return pa;
		}

		/**
		 * @param pa
		 *            the pa to set
		 */
		public void setPa(int pa) {
			this.pa = pa;
		}

		/**
		 * @return the pm
		 */
		public int getPm() {
			return pm;
		}

		/**
		 * @param pm
		 *            the pm to set
		 */
		public void setPm(int pm) {
			this.pm = pm;
		}

		/**
		 * @return the resis
		 */
		public int[] getResis() {
			return resis;
		}

		/**
		 * @param resis
		 *            the resis to set
		 */
		public void setResis(int[] resis) {
			this.resis = resis;
		}

		/**
		 * @return the team
		 */
		public int getTeam() {
			return team;
		}

		/**
		 * @param team
		 *            the team to set
		 */
		public void setTeam(int team) {
			this.team = team;
		}

		@Override
		public String toString() {
			return "PlayerInFight [lvl=" + lvl + ", color1=" + color1 + ", color2=" + color2 + ", color3=" + color3 + ", accessories=" + Arrays.toString(accessories) + ", life=" + life + ", pa=" + pa
					+ ", pm=" + pm + ", resis=" + Arrays.toString(resis) + ", team=" + team + "]";
		}

	}

	public static class PlayerOutsideFight {
		private int color1;
		private int color2;
		private int color3;
		private Accessory[] accessories;
		private int aura;
		private int emot;
		private int emotTimer;
		private String guildname;
		private String[] emblem;
		private String restrictions;

		/**
		 * @param color1
		 * @param color2
		 * @param color3
		 * @param accessories
		 * @param aura
		 * @param emot
		 * @param emotTimer
		 * @param guildname
		 * @param emblem
		 * @param restrictions
		 */
		public PlayerOutsideFight(int color1, int color2, int color3, Accessory[] accessories, int aura, int emot, int emotTimer, String guildname, String[] emblem, String restrictions) {
			this.color1 = color1;
			this.color2 = color2;
			this.color3 = color3;
			this.accessories = accessories;
			this.aura = aura;
			this.emot = emot;
			this.emotTimer = emotTimer;
			this.guildname = guildname;
			this.emblem = emblem;
			this.restrictions = restrictions;
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
		 * @return the accessories
		 */
		public Accessory[] getAccessories() {
			return accessories;
		}

		/**
		 * @return the aura
		 */
		public int getAura() {
			return aura;
		}

		/**
		 * @param aura
		 *            the aura to set
		 */
		public void setAura(int aura) {
			this.aura = aura;
		}

		/**
		 * @return the emot
		 */
		public int getEmot() {
			return emot;
		}

		/**
		 * @param emot
		 *            the emot to set
		 */
		public void setEmot(int emot) {
			this.emot = emot;
		}

		/**
		 * @return the emotTimer
		 */
		public int getEmotTimer() {
			return emotTimer;
		}

		/**
		 * @param emotTimer
		 *            the emotTimer to set
		 */
		public void setEmotTimer(int emotTimer) {
			this.emotTimer = emotTimer;
		}

		/**
		 * @return the guildname
		 */
		public String getGuildname() {
			return guildname;
		}

		/**
		 * @param guildname
		 *            the guildname to set
		 */
		public void setGuildname(String guildname) {
			this.guildname = guildname;
		}

		/**
		 * @return the emblem
		 */
		public String[] getEmblem() {
			return emblem;
		}

		/**
		 * @param emblem
		 *            the emblem to set
		 */
		public void setEmblem(String[] emblem) {
			this.emblem = emblem;
		}

		/**
		 * @return the restrictions
		 */
		public String getRestrictions() {
			return restrictions;
		}

		/**
		 * @param restrictions
		 *            the restrictions to set
		 */
		public void setRestrictions(String restrictions) {
			this.restrictions = restrictions;
		}

		/**
		 * @param accessories
		 *            the accessories to set
		 */
		public void setAccessories(Accessory[] accessories) {
			this.accessories = accessories;
		}

		@Override
		public String toString() {
			return "PlayerOutsideFight [color1=" + color1 + ", color2=" + color2 + ", color3=" + color3 + ", accessories=" + Arrays.toString(accessories) + ", aura=" + aura + ", emot=" + emot
					+ ", emotTimer=" + emotTimer + ", guildname=" + guildname + ", emblem=" + Arrays.toString(emblem) + ", restrictions=" + restrictions + "]";
		}

	}

}
