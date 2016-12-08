package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameMovementPacket implements Packet {
	private List<Integer> cell;
	private List<String> name;

	@Override
	public void read(DofusStream stream) throws IOException {
		cell = new ArrayList<>();
		name = new ArrayList<>();
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
			this.cell.add(Integer.parseInt(data[0]));
			int dir = Integer.parseInt(data[1]);
			int bonusValue = Integer.parseInt(data[2]);
			int entityId = Integer.parseInt(data[3]);
			this.name.add(data[4]);
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
		}
	}

	@Override
	public void write(DofusStream stream) throws IOException {

	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public List<Integer> getCell() {
		return cell;
	}

	public List<String> getName() {
		return name;
	}
}
