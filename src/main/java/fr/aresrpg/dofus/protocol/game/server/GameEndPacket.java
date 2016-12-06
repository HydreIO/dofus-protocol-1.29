package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.structures.character.Item;
import fr.aresrpg.dofus.util.Convert;
import fr.aresrpg.dofus.util.StringUtils;

import java.io.IOException;
import java.util.StringJoiner;

/**
 * 
 * @since
 */
public class GameEndPacket implements Packet {

	private int duration;
	private int bonus;
	private int fightType;
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
	public void read(DofusStream stream) throws IOException {
		String duration = stream.read();
		if(duration.contains(";")) {
			String[] data = duration.split(";");
			this.duration = Integer.parseInt(data[0]);
			this.bonus = Integer.parseInt(data[1]);
		}else {
			this.duration = Integer.parseInt(duration);
			this.bonus = -1;
		}
		this.senderId = stream.readInt();
		this.fightType = stream.readInt();
		String[] data = StringUtils.split(stream.read() , ";");
		code = Integer.parseInt(data[0]);
		if(code == 6){
			drop = readItem(data[1]);
			kama = Integer.parseInt(data[2]);
		} else {
			characterId = Integer.parseInt(data[1]);
			name = data[2];
			level = Integer.parseInt(data[3]);
			dead = data[4].equals("1");
			minHonour = -1;
			honour = -1;
			maxHonour = -1;
			winHonour = -1;
			rank = -1;
			disgrace = -1;
			winDisgrace = -1;
			switch (fightType) {
				case 0:
					minXp = Integer.parseInt(data[5]);
					xp = Integer.parseInt(data[6]);
					maxXp = Integer.parseInt(data[7]);
					winXp = Integer.parseInt(data[8]);
					guildXp = Integer.parseInt(data[9]);
					mountXp = Convert.toInt(data[10] , -1);
					drop = readItem(data[11]);
					kama = Integer.parseInt(data[12]);
					break;
				case 1:
					minHonour = Integer.parseInt(data[5]);
					honour = Integer.parseInt(data[6]);
					maxHonour = Integer.parseInt(data[7]);
					winHonour = Integer.parseInt(data[8]);
					rank = Integer.parseInt(data[9]);
					disgrace = Integer.parseInt(data[10]);
					winDisgrace = Integer.parseInt(data[11]);
					drop = readItem(data[12]);
					kama = Integer.parseInt(data[13]);
					minXp = Integer.parseInt(data[14]);
					xp = Integer.parseInt(data[15]);
					maxXp = Integer.parseInt(data[16]);
					winXp = Integer.parseInt(data[17]);
					break;
			}
		}
	}

	private static Item[] readItem(String data){
		String[] d = data.split(",");
		Item[] items = new Item[d.length];
		for(int i = 0 ; i < items.length ; i++){
			String[] amount = d[i].split("~");
			items[i] = new Item(Integer.parseInt(amount[0]) , Integer.parseInt(amount[1]));
		}
		return items;
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(4)
				.write(bonus == -1 ? Integer.toString(duration) : duration + ";" + bonus)
				.writeInt(senderId)
				.writeInt(fightType);
		StringJoiner joiner = new StringJoiner(";");
		if(code == 6)
			joiner.add(writeItem(drop)).add(Integer.toString(kama));
		else {
			joiner.add(Integer.toString(characterId))
					.add(name)
					.add(Integer.toString(level))
					.add(dead ? "1" : "0");
			switch (fightType) {
				case 0:
					joiner.add(Integer.toString(minXp))
							.add(Integer.toString(xp))
							.add(Integer.toString(winXp))
							.add(Integer.toString(guildXp))
							.add(Integer.toString(mountXp))
							.add(writeItem(drop))
							.add(Integer.toString(kama));
					break;
				case 1:
					joiner.add(Integer.toString(minHonour))
							.add(Integer.toString(honour))
							.add(Integer.toString(maxHonour))
							.add(Integer.toString(winHonour))
							.add(Integer.toString(rank))
							.add(Integer.toString(disgrace))
							.add(Integer.toString(winDisgrace))
							.add(writeItem(drop))
							.add(Integer.toString(kama))
							.add(Integer.toString(minXp))
							.add(Integer.toString(xp))
							.add(Integer.toString(maxXp))
							.add(Integer.toString(winXp));
					break;
			}
		}
		stream.write(joiner.toString());
	}

	private static String writeItem(Item[] items) {
		StringJoiner sb = new StringJoiner(",");
		for(Item item : items)
			sb.add(item.getData() + "~" + item.getAmount());
		return sb.toString();
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getDuration() {
		return duration;
	}

	public int getBonus() {
		return bonus;
	}

	public int getFightType() {
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
