package fr.aresrpg.dofus.structures.game;

import fr.aresrpg.dofus.structures.item.Item;
import fr.aresrpg.dofus.util.Convert;
import fr.aresrpg.dofus.util.StringJoiner;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 
 * @since
 */
public class FightResult {

	private boolean isPvp;
	private FightEndEntity[] winners;
	private FightEndEntity[] loosers;
	private FightEndEntity[] collectors;

	public FightResult(boolean isPvp) {
		this.isPvp = isPvp;
	}

	public String serialize() {
		StringJoiner joiner = new StringJoiner("|").add(isPvp ? 1 : 0);
		if (winners != null) Arrays.stream(winners).forEach(e -> joiner.add(FightEndEntityType.WINNER.getCode() + ";" + e.serialize()));
		if (loosers != null) Arrays.stream(loosers).forEach(e -> joiner.add(FightEndEntityType.LOOSER.getCode() + ";" + e.serialize()));
		if (collectors != null) Arrays.stream(collectors).forEach(e -> joiner.add(FightEndEntityType.PERCO.getCode() + ";" + e.serialize()));
		return joiner.toString();
	}

	/**
	 * @return the isPvp
	 */
	public boolean isPvp() {
		return isPvp;
	}

	/**
	 * @param isPvp
	 *            the isPvp to set
	 */
	public void setPvp(boolean isPvp) {
		this.isPvp = isPvp;
	}

	/**
	 * @return the winners
	 */
	public FightEndEntity[] getWinners() {
		return winners;
	}

	/**
	 * @param winners
	 *            the winners to set
	 */
	public void setWinners(FightEndEntity[] winners) {
		this.winners = winners;
	}

	/**
	 * @return the loosers
	 */
	public FightEndEntity[] getLoosers() {
		return loosers;
	}

	/**
	 * @param loosers
	 *            the loosers to set
	 */
	public void setLoosers(FightEndEntity[] loosers) {
		this.loosers = loosers;
	}

	/**
	 * @return the collectors
	 */
	public FightEndEntity[] getCollectors() {
		return collectors;
	}

	/**
	 * @param collectors
	 *            the collectors to set
	 */
	public void setCollectors(FightEndEntity[] collectors) {
		this.collectors = collectors;
	}

	@Override
	public String toString() {
		return "FightResult [isPvp=" + isPvp + ", winners=" + Arrays.toString(winners) + ", loosers=" + Arrays.toString(loosers) + ", collectors=" + Arrays.toString(collectors) + "]";
	}

	public void parseEntity(String data) {
		String[] datas = data.split(";", -1);
		FightEndEntityType type = FightEndEntityType.valueOf(Integer.parseInt(datas[0]));
		int id = Integer.parseInt(datas[1]);
		String sprite = datas[2];
		int lvl = Integer.parseInt(datas[3]);
		boolean dead = Integer.parseInt(datas[4]) == 1;
		FightEndEntity ent = null;
		if (!isPvp) {
			int minxp = Convert.toInt(datas[5]);
			int xp = Convert.toInt(datas[6]);
			int maxxp = Convert.toInt(datas[7]);
			int winxp = Convert.toInt(datas[8]);
			int guild = Convert.toInt(datas[9]);
			int mount = Convert.toInt(datas[10]);
			int kamas = Convert.toInt(datas[12]);
			ent = new FightEntityPvm(sprite, id, lvl, minxp, xp, maxxp, winxp, guild, mount, kamas, dead, parseEndItems(datas[11]));
		} else {
			int minhonour = Convert.toInt(datas[5]);
			int honour = Convert.toInt(datas[6]);
			int maxhonour = Convert.toInt(datas[7]);
			int winhonour = Convert.toInt(datas[8]);
			int rank = Convert.toInt(datas[9]);
			int disgrace = Convert.toInt(datas[10]);
			int windisgrace = Convert.toInt(datas[11]);
			int maxdisgrace = 0;
			int mindisgrace = 0;
			int kamas = Convert.toInt(datas[13]);
			int minxp = Convert.toInt(datas[14]);
			int xp = Convert.toInt(datas[15]);
			int maxxp = Convert.toInt(datas[16]);
			int winxp = Convert.toInt(datas[17]);
			ent = new FightEntityPlayerPvp(sprite, id, lvl, minhonour, honour, maxhonour, winhonour, rank, disgrace, windisgrace, maxdisgrace, mindisgrace, kamas, minxp, xp, maxxp,
					winxp, dead, parseEndItems(datas[12]));
		}
		switch (type) {
			case WINNER:
				this.winners = concat(ent, this.winners);
				return;
			case LOOSER:
				this.loosers = concat(ent, this.loosers);
				return;
			case PERCO:
				this.collectors = concat(ent, this.collectors);
				return;
			default:
				throw new IllegalArgumentException("Fuck cet erreur signifie que le type 6 n'est pas pour le serveur héroique et donc bonne chance pour trouver fdp");
		}
	}

	public FightEndEntity[] concat(FightEndEntity ent, FightEndEntity[] array) {
		if (array == null || array.length == 0) return new FightEndEntity[] { ent };
		FightEndEntity[] nw = new FightEndEntity[array.length + 1];
		nw[nw.length - 1] = ent;
		IntStream.range(0, array.length).forEach(i -> nw[i] = array[i]);
		return nw;
	}

	public static Item[] parseEndItems(String data) {
		return Arrays.stream(data.split(",", -1)).map(s -> {
			String[] spl = s.split("~");
			return new Item(Convert.toInt(spl[0]), spl.length > 1 ? Convert.toInt(spl[1]) : 1);
		}).toArray(Item[]::new);
	}

	public static interface FightEndEntity {
		String getSprite();

		int getId();

		int getLevel();

		Item[] getDrops();

		String serialize();

		default String serializeItems() {
			StringJoiner joiner = new StringJoiner(",");
			Arrays.stream(getDrops()).filter(i -> i.getItemTypeId() != 0).forEach(d -> joiner.add(d.getItemTypeId() + "~" + d.getQuantity()));
			return joiner.toString();
		}

	}

	public static class FightEntityPvm implements FightEndEntity {

		private String pseudo;
		private int id, lvl, minXp, xp, maxXp, winXp, guildXp, mountXp, kamas;
		private boolean isDead;
		private Item[] drops;

		public FightEntityPvm(String pseudo, int id, int lvl, int minXp, int xp, int maxXp, int winXp, int guildXp, int mountXp, int kamas, boolean isDead, Item[] drops) {
			this.pseudo = pseudo;
			this.id = id;
			this.lvl = lvl;
			this.minXp = minXp;
			this.xp = xp;
			this.maxXp = maxXp;
			this.winXp = winXp;
			this.guildXp = guildXp;
			this.mountXp = mountXp;
			this.kamas = kamas;
			this.isDead = isDead;
			this.drops = drops;
		}

		@Override
		public String serialize() {
			if (id > 0) return new StringJoiner(";").add(id).add(pseudo).add(lvl).add(isDead() ? 1 : 0).add(minXp).add(xp).add(maxXp)
					.add(winXp).add(guildXp).add(mountXp).add(serializeItems()).add(kamas).toString();
			return new StringJoiner(";").add(id).add(pseudo).add(lvl).add(isDead() ? 1 : 0).add(Convert.toDofusString(minXp)).add(Convert.toDofusString(xp)).add(Convert.toDofusString(maxXp))
					.add(Convert.toDofusString(winXp)).add(Convert.toDofusString(guildXp)).add(Convert.toDofusString(mountXp)).add(serializeItems()).add(Convert.toDofusString(kamas)).toString();
		}

		@Override
		public Item[] getDrops() {
			return drops;
		}

		/**
		 * @return the kamas
		 */
		public int getKamas() {
			return kamas;
		}

		/**
		 * @param kamas
		 *            the kamas to set
		 */
		public void setKamas(int kamas) {
			this.kamas = kamas;
		}

		@Override
		public int getLevel() {
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
		 * @param drops
		 *            the drops to set
		 */
		public void setDrops(Item... drops) {
			this.drops = drops;
		}

		/**
		 * @return the pseudo
		 */
		@Override
		public String getSprite() {
			return pseudo;
		}

		/**
		 * @param pseudo
		 *            the pseudo to set
		 */
		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		}

		/**
		 * @return the id
		 */
		@Override
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
		 * @return the minXp
		 */
		public int getMinXp() {
			return minXp;
		}

		/**
		 * @param minXp
		 *            the minXp to set
		 */
		public void setMinXp(int minXp) {
			this.minXp = minXp;
		}

		/**
		 * @return the xp
		 */
		public int getXp() {
			return xp;
		}

		/**
		 * @param xp
		 *            the xp to set
		 */
		public void setXp(int xp) {
			this.xp = xp;
		}

		/**
		 * @return the maxXp
		 */
		public int getMaxXp() {
			return maxXp;
		}

		/**
		 * @param maxXp
		 *            the maxXp to set
		 */
		public void setMaxXp(int maxXp) {
			this.maxXp = maxXp;
		}

		/**
		 * @return the winXp
		 */
		public int getWinXp() {
			return winXp;
		}

		/**
		 * @param winXp
		 *            the winXp to set
		 */
		public void setWinXp(int winXp) {
			this.winXp = winXp;
		}

		/**
		 * @return the guildXp
		 */
		public int getGuildXp() {
			return guildXp;
		}

		/**
		 * @param guildXp
		 *            the guildXp to set
		 */
		public void setGuildXp(int guildXp) {
			this.guildXp = guildXp;
		}

		/**
		 * @return the mountXp
		 */
		public int getMountXp() {
			return mountXp;
		}

		/**
		 * @param mountXp
		 *            the mountXp to set
		 */
		public void setMountXp(int mountXp) {
			this.mountXp = mountXp;
		}

		/**
		 * @return the isDead
		 */
		public boolean isDead() {
			return isDead;
		}

		/**
		 * @param isDead
		 *            the isDead to set
		 */
		public void setDead(boolean isDead) {
			this.isDead = isDead;
		}

		@Override
		public String toString() {
			return "FightEntityPvm [pseudo=" + pseudo + ", id=" + id + ", lvl=" + lvl + ", minXp=" + minXp + ", xp=" + xp + ", maxXp=" + maxXp + ", winXp=" + winXp + ", guildXp=" + guildXp
					+ ", mountXp=" + mountXp + ", isDead=" + isDead + ", drops=" + Arrays.toString(drops) + "]";
		}

	}

	public static class FightEntityPlayerPvp implements FightEndEntity {

		private String pseudo;
		private int id, lvl, minHonour, honour, maxHonour, winHonour, rank, disgrace, winDisgrace, maxDisgrace, minDisgrace, kama, minXp, xp, maxXp, winXp;
		private boolean isDead;
		private Item[] drops;

		public FightEntityPlayerPvp(String pseudo, int id, int lvl, int minHonour, int honour, int maxHonour, int winHonour, int rank, int disgrace, int winDisgrace, int maxDisgrace, int minDisgrace,
			int kama,
			int minXp, int xp, int maxXp, int winXp, boolean isDead, Item... drops) {
			this.pseudo = pseudo;
			this.id = id;
			this.minHonour = minHonour;
			this.honour = honour;
			this.maxHonour = maxHonour;
			this.winHonour = winHonour;
			this.rank = rank;
			this.disgrace = disgrace;
			this.winDisgrace = winDisgrace;
			this.maxDisgrace = maxDisgrace;
			this.minDisgrace = minDisgrace;
			this.kama = kama;
			this.minXp = minXp;
			this.xp = xp;
			this.maxXp = maxXp;
			this.winXp = winXp;
			this.isDead = isDead;
			this.drops = drops;
			this.lvl = lvl;
		}

		@Override
		public String serialize() {
			return new StringJoiner(";").add(2).add(id).add(pseudo).add(lvl).add(isDead() ? 1 : 0)
					.add(minHonour).add(honour).add(maxHonour).add(winHonour).add(rank).add(disgrace).add(winDisgrace).add(maxDisgrace).add(minDisgrace).add(kama)
					.add(minXp).add(xp).add(maxXp).add(winXp).add(serializeItems()).toString();
		}

		@Override
		public int getLevel() {
			return lvl;
		}

		/**
		 * @return the minHonour
		 */
		public int getMinHonour() {
			return minHonour;
		}

		/**
		 * @param minHonour
		 *            the minHonour to set
		 */
		public void setMinHonour(int minHonour) {
			this.minHonour = minHonour;
		}

		/**
		 * @return the honour
		 */
		public int getHonour() {
			return honour;
		}

		/**
		 * @param honour
		 *            the honour to set
		 */
		public void setHonour(int honour) {
			this.honour = honour;
		}

		/**
		 * @return the maxHonour
		 */
		public int getMaxHonour() {
			return maxHonour;
		}

		/**
		 * @param maxHonour
		 *            the maxHonour to set
		 */
		public void setMaxHonour(int maxHonour) {
			this.maxHonour = maxHonour;
		}

		/**
		 * @return the winHonour
		 */
		public int getWinHonour() {
			return winHonour;
		}

		/**
		 * @param winHonour
		 *            the winHonour to set
		 */
		public void setWinHonour(int winHonour) {
			this.winHonour = winHonour;
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

		/**
		 * @return the disgrace
		 */
		public int getDisgrace() {
			return disgrace;
		}

		/**
		 * @param disgrace
		 *            the disgrace to set
		 */
		public void setDisgrace(int disgrace) {
			this.disgrace = disgrace;
		}

		/**
		 * @return the winDisgrace
		 */
		public int getWinDisgrace() {
			return winDisgrace;
		}

		/**
		 * @param winDisgrace
		 *            the winDisgrace to set
		 */
		public void setWinDisgrace(int winDisgrace) {
			this.winDisgrace = winDisgrace;
		}

		/**
		 * @return the maxDisgrace
		 */
		public int getMaxDisgrace() {
			return maxDisgrace;
		}

		/**
		 * @param maxDisgrace
		 *            the maxDisgrace to set
		 */
		public void setMaxDisgrace(int maxDisgrace) {
			this.maxDisgrace = maxDisgrace;
		}

		/**
		 * @return the minDisgrace
		 */
		public int getMinDisgrace() {
			return minDisgrace;
		}

		/**
		 * @param minDisgrace
		 *            the minDisgrace to set
		 */
		public void setMinDisgrace(int minDisgrace) {
			this.minDisgrace = minDisgrace;
		}

		/**
		 * @return the kama
		 */
		public int getKama() {
			return kama;
		}

		/**
		 * @param kama
		 *            the kama to set
		 */
		public void setKama(int kama) {
			this.kama = kama;
		}

		/**
		 * @return the minXp
		 */
		public int getMinXp() {
			return minXp;
		}

		/**
		 * @param minXp
		 *            the minXp to set
		 */
		public void setMinXp(int minXp) {
			this.minXp = minXp;
		}

		/**
		 * @return the xp
		 */
		public int getXp() {
			return xp;
		}

		/**
		 * @param xp
		 *            the xp to set
		 */
		public void setXp(int xp) {
			this.xp = xp;
		}

		/**
		 * @return the maxXp
		 */
		public int getMaxXp() {
			return maxXp;
		}

		/**
		 * @param maxXp
		 *            the maxXp to set
		 */
		public void setMaxXp(int maxXp) {
			this.maxXp = maxXp;
		}

		/**
		 * @return the winXp
		 */
		public int getWinXp() {
			return winXp;
		}

		/**
		 * @param winXp
		 *            the winXp to set
		 */
		public void setWinXp(int winXp) {
			this.winXp = winXp;
		}

		/**
		 * @return the isDead
		 */
		public boolean isDead() {
			return isDead;
		}

		/**
		 * @param isDead
		 *            the isDead to set
		 */
		public void setDead(boolean isDead) {
			this.isDead = isDead;
		}

		/**
		 * @param pseudo
		 *            the pseudo to set
		 */
		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		}

		/**
		 * @param id
		 *            the id to set
		 */
		public void setId(int id) {
			this.id = id;
		}

		@Override
		public String getSprite() {
			return pseudo;
		}

		@Override
		public int getId() {
			return id;
		}

		/**
		 * @return the drops
		 */
		@Override
		public Item[] getDrops() {
			return drops;
		}

		@Override
		public String toString() {
			return "FightEntityPlayerPvp [pseudo=" + pseudo + ", id=" + id + ", lvl=" + lvl + ", minHonour=" + minHonour + ", honour=" + honour + ", maxHonour=" + maxHonour + ", winHonour="
					+ winHonour + ", rank=" + rank + ", disgrace=" + disgrace + ", winDisgrace=" + winDisgrace + ", maxDisgrace=" + maxDisgrace + ", minDisgrace=" + minDisgrace + ", kama=" + kama
					+ ", minXp=" + minXp + ", xp=" + xp + ", maxXp=" + maxXp + ", winXp=" + winXp + ", isDead=" + isDead + ", drops=" + Arrays.toString(drops) + "]";
		}

	}

}
