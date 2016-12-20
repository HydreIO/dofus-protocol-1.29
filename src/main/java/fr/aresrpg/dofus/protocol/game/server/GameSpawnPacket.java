package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Alignment;
import fr.aresrpg.dofus.structures.game.*;
import fr.aresrpg.dofus.util.StringJoiner;

/**
 * 
 * @since
 */
public class GameSpawnPacket implements ServerPacket { // Gc+2221954;4|2221954;196;0;-1|-2;167;1;-1

	private boolean created;
	private FightSpawn fight;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		this.created = data.charAt(0) == '+';
		String[] first = data.substring(1).split(";");
		int loc6 = Integer.parseInt(first[0]);
		int loc7 = first.length == 1 ? 0 : Integer.parseInt(first[1]); // si length == 1 c'est un remove donc on s'en branle de l'id--
		double color1 = (Math.cos(loc6) + 1) * 8388607;
		this.fight = new FightSpawn(loc6, FightType.fromId(loc7));
		if (!isCreated()) return;
		while (stream.available() > 0) {
			String[] loc11 = stream.read().split(";");
			int id = Integer.parseInt(loc11[0]);
			int cell = Integer.parseInt(loc11[1]);
			int type = Integer.parseInt(loc11[2]);
			int alignment = Integer.parseInt(loc11[3]);
			FightTeam loc17 = new FightTeam(id, cell, color1, type, Alignment.valueOf(alignment));
			getFight().getTeams().add(loc17);
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(fight.getTeams().size() + 1).write((created ? "+" : "-") + fight.getId() + ";" + fight.getType().getId());
		for (FightTeam t : fight.getTeams())
			stream.write(new StringJoiner(";").add(t.getId()).add(t.getCellNum()).add(t.getType()).add(t.getAlignment().getCode()).toString());
	}

	/**
	 * @return the created
	 */
	public boolean isCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(boolean created) {
		this.created = created;
	}

	/**
	 * @return the fight
	 */
	public FightSpawn getFight() {
		return fight;
	}

	/**
	 * @param fight
	 *            the fight to set
	 */
	public void setFight(FightSpawn fight) {
		this.fight = fight;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "GameSpawnPacket [created=" + created + ", fight=" + fight + "]";
	}

}
