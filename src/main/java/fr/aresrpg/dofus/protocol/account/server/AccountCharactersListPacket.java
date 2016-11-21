package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.protocol.util.Convert;
import fr.aresrpg.dofus.protocol.util.StringUtils;
import fr.aresrpg.dofus.structures.character.AvailableCharacter;

import java.io.IOException;
import java.util.Arrays;

public class AccountCharactersListPacket implements Packet{
	private int subscriptionTime;
	private AvailableCharacter[] characters;

	@Override
	public void read(DofusStream stream) throws IOException {
		subscriptionTime = stream.readInt();
		int nb = stream.readInt();
		characters = new AvailableCharacter[nb];
		for(int i = 0 ; i < nb ; i++){
			String[] data = StringUtils.split(stream.read() , ";");
			int id = Integer.parseInt(data[0]);
			String pseudo = data[1];
			int level = Integer.parseInt(data[2]);
			int gfxId = Integer.parseInt(data[3]);
			int color1 = Integer.parseInt(data[4] , 16);
			int color2 = Integer.parseInt(data[5] , 16);
			int color3 = Integer.parseInt(data[6] , 16);
			String[] accessoriesData = StringUtils.split(data[7], ",");
			int[] accessories = new int[accessoriesData.length];
			for(int a = 0 ; a < accessories.length ; a++)
				accessories[a] = Convert.toInt(accessoriesData[a] , 0 , 16);
			boolean merchant = Integer.parseInt(data[8]) == 1;
			int serverId = Integer.parseInt(data[9]);
			boolean isDead = Convert.toInt(data[10] , 0) == 1;
			int deathCount = Convert.toInt(data[11] , 0);
			int lvlMax = Convert.toInt(data[12] , 0);
			characters[i] = new AvailableCharacter(id , pseudo , level , gfxId , color1 , color2 ,
					color3 , accessories , merchant , serverId , isDead , deathCount , lvlMax);
		}
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		//TODO
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getSubscriptionTime() {
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
		return "AccountCharactersListPacket{" +
				"subscriptionTime=" + subscriptionTime +
				", characters=" + Arrays.toString(characters) +
				'}';
	}
}
