/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
/*******************************************************************************
 * BotFather (C) - Dofus 1.29
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.protocol.game.movement.*;
import fr.aresrpg.dofus.protocol.game.movement.MovementPlayer.PlayerInFight;
import fr.aresrpg.dofus.protocol.game.movement.MovementPlayer.PlayerOutsideFight;
import fr.aresrpg.dofus.structures.PathDirection;
import fr.aresrpg.dofus.structures.game.*;
import fr.aresrpg.dofus.structures.item.Accessory;
import fr.aresrpg.dofus.util.Convert;
import fr.aresrpg.dofus.util.Pair;

import java.util.*;

// GM| +240;1;0;2451939;Joe-larecolte;4 ;40^100 ;0;0,0,0,2451941;f10000;fb0000;f7cc9b;215c,,,,;0;;; ; ;0;; // pas combat
// GM| +404;1;0;2397625;Jowed ;3 ;30^100 ;0;0,0,0,2397726;0 ;ff0000;-1 ;,,,, ;1;;;Negro Inshape;8,4i643,2p,9zldr;0;; // pas combat

// GM| +344;1;0;2397625;Jowed ;3 ;30^100 ;0;101 ;0,0,0,2397726;0;ff0000;-1;,,,,;555;7;3;0;0;0;0;0;0;0;0;; // combat not start
// GM| +293;1;0;-2 ;493 ;-2;1212^100;3;f2c40c;bda64d;-1;0,0,0,0;14;2;3;1 // combat
public class GameMovementPacket implements ServerPacket {
	private GameMovementType type;
	private Set<Pair<GameMovementAction, MovementAction>> actors = new HashSet<>();
	private String fullpacketflemme;

	@Override
	public void read(DofusStream stream) {
		StringJoiner laflemme = new StringJoiner("|");
		stream.read();
		stream.forEach(laflemme::add);
		this.fullpacketflemme = laflemme.toString(); // comme ça jpeut envoyer dans le write
		String[] read = this.fullpacketflemme.split("\\|");
		Arrays.stream(read).forEach(this::parseActor);
	}

	public void parseActor(String datas) {
		if (datas.isEmpty()) return;
		this.type = GameMovementType.fromChar(datas.charAt(0));
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
				if (gfx.charAt(gfx.length() - 1) == '*') {
					gfx = gfx.substring(0, gfx.length() - 1);
					loc18 = true;
				}
				if (gfx.charAt(0) == '*')
					gfx = gfx.substring(1);
				String[] gfx1 = gfx.split("\\^"); // loc20
				int gfx2 = Integer.parseInt(gfx1.length > 1 ? gfx1[0] : gfx); // loc21
				GameMovementAction action = GameMovementAction.fromId(Integer.parseInt(actionIdData[0])); // loc23
				String loc24 = actionIdData.length == 2 ? actionIdData[1] : ""; // loc24
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
						loc27 = loc28 = Integer.parseInt(loc29.split(",")[0]);
				}
				switch (action) {
					case CREATE_INVOCATION:
					case CREATE_MONSTER:
						MovementMonster movementMonster = new MovementMonster(id,
								Convert.toInt(pseudo),
								action.getId(),
								gfx2,
								loc27,
								loc28,
								loc18,
								cellid,
								direction,
								Integer.parseInt(data[7]), // powerlvl
								Integer.parseInt(data[8]), // color 1
								Integer.parseInt(data[9]), // color 2
								Integer.parseInt(data[10]), // color 3
								Arrays.stream(data[11].split(",")).map(Accessory::parse).toArray(Accessory[]::new)); // accessories
						if (data.length > 12) {
							movementMonster.setLife(Convert.toInt(data[12]));
							movementMonster.setPa(Convert.toInt(data[13]));
							movementMonster.setPm(Convert.toInt(data[14]));
							if (data.length > 18) {
								movementMonster.setResi(new int[] { Convert.toInt(data[15]), Convert.toInt(data[16]), Convert.toInt(data[17]), Convert.toInt(data[18]), Convert.toInt(data[19]),
										Convert.toInt(data[20]), Convert.toInt(data[21]), });
								movementMonster.setTeam(Integer.parseInt(data[22]));
							} else {
								movementMonster.setTeam(Integer.parseInt(data[15]));
							}
						}
						actors.add(new Pair<>(action, movementMonster));
						break;
					case CREATE_MONSTER_GROUP:
						String[] loc35 = data[8].split(",");
						actors.add(new Pair<>(action,
								new MovementMonsterGroup(id, Arrays.stream(pseudo.split(",")).mapToInt(Convert::toInt).toArray(), action.getId(),
										Arrays.stream(data[7].split(",")).mapToInt(Convert::toInt).toArray(), loc27, loc28, loc18,
										cellid, direction, Convert.toHexInt(loc35[0]),
										Convert.toHexInt(loc35[1]),
										Convert.toHexInt(loc35[2]), Arrays.stream(data[9].split(",")).map(Accessory::parse).toArray(Accessory[]::new), bonusvalue)));
						break;
					case CREATE_MUTANT_WITH_NAME:
						break;
					case CREATE_MUTANT_WITHOUT_NAME:
						break;
					case CREATE_NPC:
						int extraclip;
						try {
							extraclip = Integer.parseInt(data[12]);
						} catch (Exception e) {
							extraclip = -1;
						}
						actors.add(new Pair<>(action,
								new MovementNpc(id, action.getId(), gfx2, loc27, loc28, cellid, direction, Integer.parseInt(data[7]), Convert.toHexInt(data[8]), Convert.toHexInt(data[9]),
										Convert.toHexInt(data[10]), Arrays.stream(data[11].split(",")).map(Accessory::parse).toArray(Accessory[]::new), extraclip, Convert.toInt(data[13]))));
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
						boolean isFight = data[8].length() < 4; // si combat c'est le field du lvl (max 200) donc on peut tricher pour savoir si combat ou non
						String[] loc67 = data[isFight ? 9 : 8].split(",");
						PlayerInFight pif = isFight
								? new PlayerInFight(Convert.toInt(data[8]), Integer.parseInt(data[10], 16), Integer.parseInt(data[11], 16), Integer.parseInt(data[12], 16),
										Arrays.stream(data[13].split(",")).map(Accessory::parse).toArray(Accessory[]::new),
										Convert.toInt(data[14]), Convert.toInt(data[15]), Convert.toInt(data[16]), new int[] { Convert.toInt(data[17]), Convert.toInt(data[18]),
												Convert.toInt(data[19]), Convert.toInt(data[20]), Convert.toInt(data[21]), Convert.toInt(data[22]), Convert.toInt(data[23]) },
										Convert.toInt(data[24]))
								: null;
						PlayerOutsideFight pof = isFight ? null
								: new PlayerOutsideFight(Integer.parseInt(data[9], 16), Integer.parseInt(data[10], 16), Integer.parseInt(data[11], 16),
										Arrays.stream(data[12].split(",")).map(Accessory::parse).toArray(Accessory[]::new), Convert.toInt(data[13]), Convert.toInt(data[14]), Convert.toInt(data[15]),
										data[16],
										data[17].split(","), data[18]);
						actors.add(new Pair<>(GameMovementAction.DEFAULT, new MovementPlayer(id, pseudo, action.getId(), cellid, loc27, loc28, direction, Convert.toInt(data[7]),
								new Alignement(Convert.toInt(loc67[0]), Convert.toInt(loc67[1])).setFallenAngelDemon(loc67.length > 4 ? Convert.toInt(loc67[4]) == 1 : false), Convert.toInt(loc67[2]),
								pif, pof)));
						break;
				}
				break;
			case REMOVE:
				datas = datas.substring(1);
				actors.add(new Pair<>(GameMovementAction.NONE, new MovementRemoveActor(Integer.parseInt(datas))));
				break;
		}
	}

	/**
	 * @return the type
	 */
	public GameMovementType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(GameMovementType type) {
		this.type = type;
	}

	/**
	 * @return the actors
	 */
	public Set<Pair<GameMovementAction, MovementAction>> getActors() {
		return actors;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write("|" + fullpacketflemme); // ayyy lmao
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "GameMovementPacket [type=" + type + ", actors=" + actors + "]";
	}

}
