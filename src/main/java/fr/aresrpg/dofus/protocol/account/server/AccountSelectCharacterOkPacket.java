package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.structures.character.Character;
import fr.aresrpg.dofus.structures.character.Item;

import java.io.IOException;

public class AccountSelectCharacterOkPacket implements Packet{
	private Character character;

	@Override
	public void read(DofusStream stream) throws IOException {
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
		String items = stream.read();
		character = new Character(id , pseudo , level , guild , sex , gfxID , color1 , color2 , color3 , new Item[0]);
	}

	@Override
	public void write(DofusStream stream) throws IOException {
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
				.write(""); // Items
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "AccountSelectCharacterOkPacket(character=" + character + ")[" + getId() + ']';
	}
}
