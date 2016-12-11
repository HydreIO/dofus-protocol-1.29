package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.structures.character.Character;
import fr.aresrpg.dofus.structures.item.Item;

import java.util.StringJoiner;

public class AccountSelectCharacterOkPacket implements Packet {
	private Character character;

	@Override
	public void read(DofusStream stream) {
		stream.read();//Skip separator
		int id = stream.readInt();
		String pseudo = stream.read();
		int level = stream.readInt();
		int guild = stream.readInt();
		int sex = stream.readInt();
		int gfxID = stream.readInt();
		int color1 = Integer.parseInt(stream.read(), 16);
		int color2 = Integer.parseInt(stream.read(), 16);
		int color3 = Integer.parseInt(stream.read(), 16);
		String[] rawItems = stream.read().split(";");
		Item[] items = new Item[rawItems.length];
		for(int i = 0 ; i < rawItems.length ; i++)
			items[i] = Item.parseItem(rawItems[i]);
		character = new Character(id , pseudo , level , guild , sex , gfxID , color1 , color2 , color3 , items);
	}

	@Override
	public void write(DofusStream stream) {
		StringJoiner rawItems = new StringJoiner(";");
		for(Item i : character.getItems())
			rawItems.add(Item.serializeItem(i));
		stream.allocate(11).write("") //Set separator
				.writeInt(character.getId())
				.write(character.getPseudo())
				.writeInt(character.getLevel())
				.writeInt(character.getGuild())
				.writeInt(character.getSex())
				.writeInt(character.getGfxId())
				.write(Integer.toString(character.getColor1(), 16))
				.write(Integer.toString(character.getColor2(), 16))
				.write(Integer.toString(character.getColor3(), 16))
				.write(rawItems.toString()); // Items
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}
}
