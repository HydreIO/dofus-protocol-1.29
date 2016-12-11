package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.protocol.game.movement.*;
import fr.aresrpg.dofus.structures.PathDirection;
import fr.aresrpg.dofus.structures.game.GameMovementAction;
import fr.aresrpg.dofus.structures.game.GameMovementType;
import fr.aresrpg.dofus.util.DofusTitle;

import java.util.*;

// GM| +240;1;0;2451939;Joe-larecolte;4;40^100;0;0,0,0,2451941;f10000;fb0000;f7cc9b;215c,,,,;0;;;;;0;;|+269;1
public class GameMovementPacket implements Packet {
	private GameMovementType type;
	private Map<GameMovementAction, MovementAction> actors = new HashMap<>();

	@Override
	public void read(DofusStream stream) {
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
					gfx = gfx.substring(0, gfx.length() - 1);
					loc18 = true;
				}
				if (gfx.charAt(0) == '*') {
					loc19 = false;
					gfx = gfx.substring(1);
				}
				String[] gfx1 = gfx.split("^"); // loc20
				int gfx2 = Integer.parseInt(gfx1.length == 2 ? gfx1[0] : gfx); // loc21
				GameMovementAction action = GameMovementAction.fromId(Integer.parseInt(actionIdData[0])); // loc23
				String loc24 = actionIdData.length == 2 ? actionIdData[1] : ""; // loc24
				DofusTitle loc25;
				if (loc24.length() > 0) {
					String[] loc26 = loc24.split("*");
					loc25 = new DofusTitle(Integer.parseInt(loc26[0]), loc26[1]);
				}
				int loc27 = 100;
				int loc28 = 100;
				if (gfx1.length == 2) {
					String loc29 = gfx1[1];
					int loc29number;
					boolean isnan = true;
					try {
						loc29number = Integer.parseInt(loc29);
					} catch (Exception e) {
						isnan = false;
					}
					if (isnan) {
						String[] loc30 = loc29.split("x");
						loc27 = loc30.length == 2 ? Integer.parseInt(loc30[0]) : 100;
						loc28 = loc30.length == 2 ? Integer.parseInt(loc30[1]) : 100;
					} else
						loc27 = loc28 = Integer.parseInt(loc29);
				}
				switch (action) {
					case CREATE_INVOCATION:
						actors.put(action, new MovementCreateInvocation(action.getId(), gfx2, loc27, loc28, loc18, cellid, direction, Integer.parseInt(data[7]), Integer.parseInt(data[8]),
								Integer.parseInt(data[9]),
								Integer.parseInt(data[10]), Arrays.stream(data[11].split(",")).mapToInt(Integer::parseInt).toArray()));
						break;
					case CREATE_MONSTER:
						actors.put(action,
								new MovementCreateMonster(action.getId(), gfx2, loc27, loc28, loc18, cellid, direction, Integer.parseInt(data[7]), Integer.parseInt(data[8]), Integer.parseInt(data[9]),
										Integer.parseInt(data[10]), Arrays.stream(data[11].split(",")).mapToInt(Integer::parseInt).toArray()));
						break;
					case CREATE_MONSTER_GROUP:
						String[] loc35 = data[8].split(",");
						new MovementCreateMonsterGroup(action.getId(), Integer.parseInt(data[7]), loc27, loc28, loc18, cellid, direction, Integer.parseInt(loc35[0]), Integer.parseInt(loc35[1]),
								Integer.parseInt(loc35[2]), Arrays.stream(data[9].split(",")).mapToInt(Integer::parseInt).toArray(), bonusvalue);
						break;
					case CREATE_MUTANT_WITH_NAME:
						break;
					case CREATE_MUTANT_WITHOUT_NAME:
						break;
					case CREATE_NPC:
						// TODO
						break;
					case CREATE_OFFLINE_PLAYER:
						break;
					case CREATE_PARK_MOUNT:
						break;
					case CREATE_PERCO:
						break;
					case CREATE_PRISM:
						break;
					case DEFAULT: // switch default (player)
						break;
				}
				break;
			case REMOVE:
				actors.put(GameMovementAction.NONE, new MovementRemoveActor(Integer.parseInt(datas)));
				break;
			default:
				return;
		}
	}

	@Override
	public void write(DofusStream stream) {

	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "GameMovementPacket [actors=" + actors + "]";
	}
}
