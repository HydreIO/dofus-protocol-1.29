/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Alignment;
import fr.aresrpg.dofus.structures.Rank;
import fr.aresrpg.dofus.structures.game.Alignement;
import fr.aresrpg.dofus.structures.stat.Stat;
import fr.aresrpg.dofus.structures.stat.StatValue;

import java.util.EnumMap;
import java.util.Map;

public class AccountStatsPacket implements ServerPacket {

	private int xp;
	private int xpLow;
	private int xpHigh;
	private int kama;
	private int bonusPoints;
	private int bonusPointsSpell;
	private Alignement alignment;
	private Alignement fakeAlignment;
	private Rank rank;
	private int life;
	private int lifeMax;
	private int energy;
	private int energyMax;
	private int initiative;
	private int prospection;
	private Map<Stat, StatValue> stats;

	@Override
	public void read(DofusStream stream) {
		String[] xpData = stream.read().split(",");
		this.xp = Integer.parseInt(xpData[0]);
		this.xpLow = Integer.parseInt(xpData[1]);
		this.xpHigh = Integer.parseInt(xpData[2]);
		this.kama = stream.readInt();
		this.bonusPoints = stream.readInt();
		this.bonusPointsSpell = stream.readInt();
		String[] alignement = stream.read().split(",");
		int alignementValue = Integer.parseInt(alignement[1]);
		if (alignement[0].contains("~")) {
			String[] fakeAlignement = alignement[0].split("~");
			alignement[0] = fakeAlignement[0];
			this.fakeAlignment = new Alignement(Alignment.valueOf(Integer.valueOf(fakeAlignement[1])), alignementValue);
		}
		this.alignment = new Alignement(Alignment.valueOf(Integer.valueOf(alignement[0])), alignementValue);
		this.rank = new Rank(
				Integer.parseInt(alignement[2]),
				Integer.parseInt(alignement[3]),
				Integer.parseInt(alignement[4]),
				alignement[5].equals("1"));

		String[] life = stream.read().split(",");
		this.life = Integer.parseInt(life[0]);
		this.lifeMax = Integer.parseInt(life[1]);
		String[] energy = stream.read().split(",");
		this.energy = Integer.parseInt(energy[0]);
		this.energyMax = Integer.parseInt(energy[1]);
		this.initiative = stream.readInt();
		this.prospection = stream.readInt();
		stats = new EnumMap<>(Stat.class);
		int available = stream.available();
		for (int i = 0; i < available; i++) {
			String[] data = stream.read().split(",");
			if (data.length < 4) continue;
			int base = Integer.parseInt(data[0]);
			int equip = Integer.parseInt(data[1]);
			int dons = Integer.parseInt(data[2]);
			int boost = Integer.parseInt(data[3]);
			int total = data.length == 5 ? Integer.parseInt(data[4]) : base + equip + dons + boost;
			stats.put(Stat.valueOf(i), new StatValue(base, equip, dons, boost, total));
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(8 + Stat.values().length)
				.write(xp + "," + xpLow + "," + xpHigh)
				.writeInt(bonusPoints)
				.writeInt(bonusPointsSpell);
		String alignment = Integer.toString(this.alignment.getIndex().ordinal());
		if (fakeAlignment != null)
			alignment += "~" + this.fakeAlignment.getIndex().ordinal();
		alignment += "," + this.alignment.getValue();
		stream.write(alignment + "," + rank.getValue() + "," +
				rank.getHonour() + "," + rank.getDisgrace() +
				"," + (rank.isEnabled() ? 1 : 0))
				.write(life + "," + lifeMax)
				.write(energy + "," + energyMax)
				.writeInt(initiative)
				.writeInt(prospection);
		for (Stat stat : Stat.values()) { // assure order
			StatValue value = stats.get(stat);
			stream.write(value.getBase() + "," + value.getEquipment() + "," + value.getDons() + "," +
					value.getBoost() + (stat == Stat.ActionPoints || stat == Stat.MovementPoints ? "," + value.getTotal() : ""));
		}

	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public int getXp() {
		return xp;
	}

	public int getXpLow() {
		return xpLow;
	}

	public int getXpHigh() {
		return xpHigh;
	}

	public int getKama() {
		return kama;
	}

	public int getBonusPoints() {
		return bonusPoints;
	}

	public int getBonusPointsSpell() {
		return bonusPointsSpell;
	}

	public Alignement getAlignment() {
		return alignment;
	}

	public Alignement getFakeAlignment() {
		return fakeAlignment;
	}

	public Rank getRank() {
		return rank;
	}

	public int getLife() {
		return life;
	}

	public int getLifeMax() {
		return lifeMax;
	}

	public int getEnergy() {
		return energy;
	}

	public int getEnergyMax() {
		return energyMax;
	}

	public int getInitiative() {
		return initiative;
	}

	/**
	 * @return the prospection
	 */
	public int getProspection() {
		return prospection;
	}

	public Map<Stat, StatValue> getStats() {
		return stats;
	}

	public AccountStatsPacket setXp(int xp) {
		this.xp = xp;
		return this;
	}

	public AccountStatsPacket setXpLow(int xpLow) {
		this.xpLow = xpLow;
		return this;
	}

	public AccountStatsPacket setXpHigh(int xpHigh) {
		this.xpHigh = xpHigh;
		return this;
	}

	public AccountStatsPacket setKama(int kama) {
		this.kama = kama;
		return this;
	}

	public AccountStatsPacket setBonusPoints(int bonusPoints) {
		this.bonusPoints = bonusPoints;
		return this;
	}

	public AccountStatsPacket setBonusPointsSpell(int bonusPointsSpell) {
		this.bonusPointsSpell = bonusPointsSpell;
		return this;
	}

	public AccountStatsPacket setAlignment(Alignement alignment) {
		this.alignment = alignment;
		return this;
	}

	public AccountStatsPacket setFakeAlignment(Alignement fakeAlignment) {
		this.fakeAlignment = fakeAlignment;
		return this;
	}

	public AccountStatsPacket setRank(Rank rank) {
		this.rank = rank;
		return this;
	}

	public AccountStatsPacket setLife(int life) {
		this.life = life;
		return this;
	}

	public AccountStatsPacket setLifeMax(int lifeMax) {
		this.lifeMax = lifeMax;
		return this;
	}

	public AccountStatsPacket setEnergy(int energy) {
		this.energy = energy;
		return this;
	}

	public AccountStatsPacket setEnergyMax(int energyMax) {
		this.energyMax = energyMax;
		return this;
	}

	public AccountStatsPacket setInitiative(int initiative) {
		this.initiative = initiative;
		return this;
	}

	public AccountStatsPacket setProspection(int prospection) {
		this.prospection = prospection;
		return this;
	}

	public AccountStatsPacket setStats(Map<Stat, StatValue> stats) {
		this.stats = stats;
		return this;
	}

	@Override
	public String toString() {
		return "AccountStatsPacket [xp=" + xp + ", xpLow=" + xpLow + ", xpHigh=" + xpHigh + ", kama=" + kama + ", bonusPoints=" + bonusPoints + ", bonusPointsSpell=" + bonusPointsSpell
				+ ", alignment=" + alignment + ", fakeAlignment=" + fakeAlignment + ", rank=" + rank + ", life=" + life + ", lifeMax=" + lifeMax + ", energy=" + energy + ", energyMax=" + energyMax
				+ ", initiative=" + initiative + ", prospection=" + prospection + ", stats=" + stats + "]";
	}

}
