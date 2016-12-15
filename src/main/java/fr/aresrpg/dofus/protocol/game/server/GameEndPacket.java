package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.game.Effect;
import fr.aresrpg.dofus.structures.game.FightType;
import fr.aresrpg.dofus.structures.item.Item;
import fr.aresrpg.dofus.util.Convert;
import fr.aresrpg.dofus.util.StringUtils;

import java.util.Arrays;
import java.util.StringJoiner;

/**
 * 
 * @since
 */
public class GameEndPacket implements ServerPacket {

	private int duration;
	private int bonus;
	private FightType fightType;
	private int senderId;
	private Item[] drop;
	private int code;
	private int characterId;
	private String name;
	private int level;
	private boolean dead;
	private int minXp;
	private int xp;
	private int maxXp;
	private int winXp;
	private int guildXp;
	private int mountXp;
	private int kama;
	private int minHonour;
	private int honour;
	private int maxHonour;
	private int winHonour;
	private int rank;
	private int disgrace;
	private int winDisgrace;

	@Override
	public void read(DofusStream stream) {
		String duration = stream.read();
		if (duration.contains(";")) {
			String[] data = duration.split(";");
			this.duration = Integer.parseInt(data[0]);
			this.bonus = Integer.parseInt(data[1]);
		} else {
			this.duration = Integer.parseInt(duration);
			this.bonus = -1;
		}
		this.senderId = stream.readInt();
		this.fightType = FightType.fromId(stream.readInt());
		String[] data = StringUtils.split(stream.read(), ";");
		code = Integer.parseInt(data[0]);
		if (code == 6) {
			drop = readItem(data[1]);
			kama = Integer.parseInt(data[2]);
		} else {
			characterId = Integer.parseInt(data[1]);
			name = data[2];
			level = Integer.parseInt(data[3]);
			dead = data[4].equals("1");
			minHonour = 0;
			honour = 0;
			maxHonour = 0;
			winHonour = 0;
			rank = 0;
			disgrace = 0;
			winDisgrace = 0;
			switch (fightType) {
				case AGRESSION:
					minHonour = Convert.toInt(data[5]);
					honour = Convert.toInt(data[6]);
					maxHonour = Convert.toInt(data[7]);
					winHonour = Convert.toInt(data[8]);
					rank = Convert.toInt(data[9]);
					disgrace = Convert.toInt(data[10]);
					winDisgrace = Convert.toInt(data[11]);
					drop = readItem(data[12]);
					kama = Convert.toInt(data[13]);
					minXp = Convert.toInt(data[14]);
					xp = Convert.toInt(data[15]);
					maxXp = Convert.toInt(data[16]);
					winXp = Convert.toInt(data[17]);
					break;
				case MONSTER:
					minXp = Convert.toInt(data[5]);
					xp = Convert.toInt(data[6]);
					maxXp = Convert.toInt(data[7]);
					winXp = Convert.toInt(data[8]);
					guildXp = Convert.toInt(data[9], 0);
					mountXp = Convert.toInt(data[10], 0);
					drop = readItem(data[11]);
					kama = Convert.toInt(data[12]);
					break;
				case DUEL:
					break;
				case PERCO:
					break;
			}
		}
	}

	private static Item[] readItem(String data) {
		String[] d = data.split(",");
		Item[] items = new Item[d.length];
		for (int i = 0; i < items.length; i++) {
			String[] amount = d[i].split("~");
			items[i] = new Item(-1, Convert.toInt(amount[0], 0), Convert.toInt(amount[1], 0), -1, new Effect[0]);
		}
		return items;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(4)
				.write(bonus == -1 ? Integer.toString(duration) : duration + ";" + bonus)
				.writeInt(senderId)
				.writeInt(fightType.getId());
		StringJoiner joiner = new StringJoiner(";");
		joiner.add(Integer.toString(code));
		if (code == 6)
			joiner.add(writeItem(drop)).add(Integer.toString(kama));
		else {
			joiner.add(Integer.toString(characterId))
					.add(name)
					.add(Integer.toString(level))
					.add(dead ? "1" : "0");
			switch (fightType) {
				case DUEL:
					break;
				case MONSTER:
					joiner.add(Convert.toDofusString(minXp))
							.add(Convert.toDofusString(xp))
							.add(Convert.toDofusString(maxXp))
							.add(Convert.toDofusString(winXp))
							.add(Convert.toDofusString(guildXp))
							.add(Convert.toDofusString(mountXp))
							.add(writeItem(drop))
							.add(Convert.toDofusString(kama));
					break;
				case AGRESSION:
					joiner.add(Convert.toDofusString(minHonour))
							.add(Convert.toDofusString(honour))
							.add(Convert.toDofusString(maxHonour))
							.add(Convert.toDofusString(winHonour))
							.add(Convert.toDofusString(rank))
							.add(Convert.toDofusString(disgrace))
							.add(Convert.toDofusString(winDisgrace))
							.add(writeItem(drop))
							.add(Convert.toDofusString(kama))
							.add(Convert.toDofusString(minXp))
							.add(Convert.toDofusString(xp))
							.add(Convert.toDofusString(maxXp))
							.add(Convert.toDofusString(winXp));
					break;
			}
		}
		stream.write(joiner.toString());
	}

	@Override
	public String toString() {
		return "GameEndPacket [duration=" + duration + ", bonus=" + bonus + ", fightType=" + fightType + ", senderId=" + senderId + ", drop=" + Arrays.toString(drop) + ", code=" + code
				+ ", characterId=" + characterId + ", name=" + name + ", level=" + level + ", dead=" + dead + ", minXp=" + minXp + ", xp=" + xp + ", maxXp=" + maxXp + ", winXp=" + winXp + ", guildXp="
				+ guildXp + ", mountXp=" + mountXp + ", kama=" + kama + ", minHonour=" + minHonour + ", honour=" + honour + ", maxHonour=" + maxHonour + ", winHonour=" + winHonour + ", rank=" + rank
				+ ", disgrace=" + disgrace + ", winDisgrace=" + winDisgrace + "]";
	}

	private static String writeItem(Item[] items) {
		StringJoiner sb = new StringJoiner(",");
		for (Item item : items)
			sb.add(item.getItemTypeId() + "~" + item.getQuantity());
		return sb.toString();
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public int getDuration() {
		return duration;
	}

	public int getBonus() {
		return bonus;
	}

	/**
	 * @return the fightType
	 */
	public FightType getFightType() {
		return fightType;
	}

	public int getSenderId() {
		return senderId;
	}

	public Item[] getDrop() {
		return drop;
	}

	public int getCode() {
		return code;
	}

	public int getCharacterId() {
		return characterId;
	}

	public String getName() {
		return name;
	}

	public int getLevel() {
		return level;
	}

	public boolean isDead() {
		return dead;
	}

	public int getMinXp() {
		return minXp;
	}

	public int getXp() {
		return xp;
	}

	public int getMaxXp() {
		return maxXp;
	}

	public int getWinXp() {
		return winXp;
	}

	public int getGuildXp() {
		return guildXp;
	}

	public int getMountXp() {
		return mountXp;
	}

	public int getKama() {
		return kama;
	}

	public int getMinHonour() {
		return minHonour;
	}

	public int getHonour() {
		return honour;
	}

	public int getMaxHonour() {
		return maxHonour;
	}

	public int getWinHonour() {
		return winHonour;
	}

	public int getRank() {
		return rank;
	}

	public int getDisgrace() {
		return disgrace;
	}

	public int getWinDisgrace() {
		return winDisgrace;
	}

}
