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
import fr.aresrpg.dofus.structures.character.AvailableCharacter;
import fr.aresrpg.dofus.util.Convert;
import fr.aresrpg.dofus.util.StringUtils;

import java.util.Arrays;
import java.util.StringJoiner;

public class AccountCharactersListPacket implements ServerPacket {
	private long subscriptionTime;
	private int persoTot;
	private AvailableCharacter[] characters;

	@Override
	public void read(DofusStream stream) {
		subscriptionTime = stream.readLong();
		this.persoTot = stream.readInt();
		characters = new AvailableCharacter[stream.available()];
		int i = 0;
		while (stream.available() > 0) {
			String[] data = StringUtils.split(stream.read(), ";");
			int id = Integer.parseInt(data[0]);
			String pseudo = data[1];
			int level = Integer.parseInt(data[2]);
			int gfxId = Integer.parseInt(data[3]);
			int color1 = Integer.parseInt(data[4], 16);
			int color2 = Integer.parseInt(data[5], 16);
			int color3 = Integer.parseInt(data[6], 16);
			String[] accessoriesData = StringUtils.split(data[7], ",");
			int[] accessories = new int[accessoriesData.length];
			for (int a = 0; a < accessories.length; a++) {
				if (accessoriesData[a] == null || accessoriesData[a].equals("null")) continue;
				accessories[a] = Convert.toInt(accessoriesData[a], 0, 16);
			}
			boolean merchant = Integer.parseInt(data[8]) == 1;
			int serverId = Integer.parseInt(data[9]);
			boolean isDead = Convert.toInt(data[10], 0) == 1;
			int deathCount = Convert.toInt(data[11], 0);
			int lvlMax = Convert.toInt(data[12], 0);
			characters[i++] = new AvailableCharacter(id, pseudo, level, gfxId, color1, color2,
					color3, accessories, merchant, serverId, isDead, deathCount, lvlMax);
		}
	}

	/**
	 * @return the total number of perso on the account (for all servers)
	 */
	public int getPersoTot() {
		return persoTot;
	}

	/**
	 * @param persoTot
	 *            the persoTot to set
	 */
	public void setPersoTot(int persoTot) {
		this.persoTot = persoTot;
	}

	/**
	 * @param subscriptionTime
	 *            the subscriptionTime to set
	 */
	public void setSubscriptionTime(long subscriptionTime) {
		this.subscriptionTime = subscriptionTime;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(characters.length + 2);
		stream.writeLong(subscriptionTime);
		stream.writeInt(this.persoTot);
		int i = 0;
		for (AvailableCharacter c : getCharacters()) {
			if (c == null)
				break;
			StringJoiner sb = new StringJoiner(";");
			sb.add(s(c.getId()))
					.add(c.getPseudo())
					.add(s(c.getLevel()))
					.add(s(c.getGfxId()))
					.add(Integer.toString(c.getColor1(), 16))
					.add(Integer.toString(c.getColor2(), 16))
					.add(Integer.toString(c.getColor3(), 16));
			StringJoiner accs = new StringJoiner(",");
			for (int a : c.getAccessories())
				accs.add(Convert.fromHexInt(a));
			sb.add(accs.toString())
					.add(c.isMerchant() ? s(1) : s(0))
					.add(s(c.getServerId()))
					.add(c.isDead() ? s(1) : s(0))
					.add(s(c.getDeathCount()))
					.add(s(c.getLvlMax()));
			stream.write(sb.toString());
			i++;
		}
		stream.allocate(i + 2); // Desallocate
	}

	private static String s(Object o) { // juste car la flemme de faire des String.valueOf dans le write
		return o.toString();
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public long getSubscriptionTime() {
		return subscriptionTime;
	}

	public AvailableCharacter[] getCharacters() {
		return characters;
	}

	public AccountCharactersListPacket setSubscriptionTime(int subscriptionTime) {
		this.subscriptionTime = subscriptionTime;
		return this;
	}

	public AccountCharactersListPacket setCharacters(AvailableCharacter[] characters) {
		this.characters = characters;
		return this;
	}

	@Override
	public String toString() {
		return "AccountCharactersListPacket [subscriptionTime=" + subscriptionTime + ", persoTot=" + persoTot + ", characters=" + Arrays.toString(characters) + "]";
	}

}
