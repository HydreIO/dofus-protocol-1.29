package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.util.StringUtils;

import java.io.IOException;
import java.util.Arrays;

public class GameMovementPacket implements Packet {
	private int cell;
	private String name;

	@Override
	public void read(DofusStream stream) throws IOException {
		stream.read(); //Split separator
		while(stream.available() > 0){
			String s = stream.read();
			boolean item = false;
			switch (s.charAt(0)) {
				case '~':
					item = true;
				case '+':
					break;
				case '-':
					continue;
			}
			String[] data = StringUtils.split(s.substring(1), ";");
			this.cell = Integer.parseInt(data[0]);
			int dir = Integer.parseInt(data[1]);
			int bonusValue = Integer.parseInt(data[2]);
			int entityId = Integer.parseInt(data[3]);
			this.name = data[4];
			String[] actionIdData = data[5].split(","); //if length == 2 id + title
			int actionId = Integer.parseInt(actionIdData[0]);
			boolean noFlip = false;
			boolean ghostMode = true;
			String gfx= data[6];
			if(gfx.charAt(gfx.length() -1) == '*'){
				gfx = gfx.substring(0 , gfx.length() -1);
				noFlip = true;
			}
			if(gfx.charAt(0) == '*') {
				gfx = gfx.substring(1);
				ghostMode = false;
			}
			String[] gfxData = gfx.split("\\^");
			int gfxId = Integer.parseInt(gfxData[0]);
			/*switch (actionId) {
				case 1:
			}*/
			break;
		}
	}

	@Override
	public void write(DofusStream stream) throws IOException {

	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getCell() {
		return cell;
	}

	public String getName() {
		return name;
	}
}
