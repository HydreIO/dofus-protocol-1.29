package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.protocol.game.movement.MovementCreateInvocation;
import fr.aresrpg.dofus.structures.PathDirection;
import fr.aresrpg.dofus.structures.game.*;
import fr.aresrpg.dofus.util.StringUtils;

import java.io.IOException;
import java.util.*;

// GM| +240;1;0;2451939;Joe-larecolte;4;40^100;0;0,0,0,2451941;f10000;fb0000;f7cc9b;215c,,,,;0;;;;;0;;|+269;1
public class GameMovementPacket implements Packet {
	private Set<GameMovementActor> actors = new HashSet<>();

	@Override
	public void read(DofusStream stream) throws IOException {
		stream.read();
		stream.forEach(this::parseActor);
	}

	private void parseActor(String datas) {
		GameMovementType type = GameMovementType.fromChar(datas.charAt(0));
		String[] data = datas.substring(1).split(";"); // loc10
		switch (type) {
			case UPDATE:
			case SHOW:
				int cellid = Integer.parseInt(data[0]); // loc11
				PathDirection direction = PathDirection.valueOf(Integer.parseInt(data[1])); // loc12
				int bonusvalue = Integer.parseInt(data[2]); // loc13
				int id = Integer.parseInt(data[3]); // loc14
				String pseudo = data[4]; // loc15
				String[] actionIdData = data[5].split(","); // loc16 & split = loc22
				String gfx = data[6]; // loc17
				boolean loc18 = false;
				boolean loc19 = true;
				if (gfx.charAt(gfx.length() - 1) == '*') {

				}
				String[] gfx1 = gfx.split("^"); // loc20
				String gfx2 = gfx1.length == 2 ? gfx1[0] : gfx; // loc21
				GameMovementAction action = GameMovementAction.fromId(Integer.parseInt(actionIdData[0])); // loc23
				String loc24 = actionIdData.length == 2 ? actionIdData[1] : ""; // loc24
				switch (action) {
					case CREATE_INVOCATION:
						new MovementCreateInvocation(action.getId(), id, id, id, false, cellid, direction, id, cellid, bonusvalue, id, null);
						break;
					case CREATE_MONSTER:
						break;
					default:
						break;
				}
				break;
			case REMOVE:
				actors.add(GameMovementActor.withRemove(Integer.parseInt(datas)));
				break;
			default:
				return;
		}
	}

	public void reazd(DofusStream stream) throws IOException {
		cell = new ArrayList<>();
		name = new ArrayList<>();
		stream.read(); // Split separator
		while (stream.available() > 0) {
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
			// System.out.println("BYTEEEEEEEEEEEEEEEEEEEEE [" + Arrays.toString(data[4].getBytes()) + "]");
			String[] actionIdData = data[5].split(","); // if length == 2 id + title
			int actionId = Integer.parseInt(actionIdData[0]);
			boolean noFlip = false;
			boolean ghostMode = true;
			String gfx = data[6];
			if (gfx.charAt(gfx.length() - 1) == '*') {
				gfx = gfx.substring(0, gfx.length() - 1);
				noFlip = true;
			}
			if (gfx.charAt(0) == '*') {
				gfx = gfx.substring(1);
				ghostMode = false;
			}
			String[] gfxData = gfx.split("\\^");
			int gfxId = Integer.parseInt(gfxData[0]);
			/*
			 * switch (actionId) {
			 * case 1:
			 * }
			 */
		}
	}

	@Override
	public String toString() {
		return "GameMovementPacket [cell=" + cell + ", name=" + name + "]";
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
