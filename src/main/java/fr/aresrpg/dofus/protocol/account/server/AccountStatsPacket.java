package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Alignment;
import fr.aresrpg.dofus.structures.Rank;
import fr.aresrpg.dofus.structures.game.Alignement;

/**
 * 
 * @since
 */
public class AccountStatsPacket implements ServerPacket {

	private int xp;
	private int xplow;
	private int xphigh;
	private int kama;
	private int bonuspoints;
	private int bonuspointsSpell;
	private boolean hasFakeAlignment;
	private Alignement alignment;
	private Alignement fakeAlignment;
	private Rank rank;
	private int life;
	private int lifeMax;
	private int energy;
	private int energyMax;
	private int initiative;
	private int discernment;

	@Override
	public void read(DofusStream stream) {
		String[] loc5 = stream.read().split(",");
		this.xp = Integer.parseInt(loc5[0]);
		this.xplow = Integer.parseInt(loc5[1]);
		this.xphigh = Integer.parseInt(loc5[2]);
		this.kama = stream.readInt();
		this.bonuspoints = stream.readInt();
		this.bonuspointsSpell = stream.readInt();
		loc5 = stream.read().split(",");
		int loc6 = 0;
		if (loc5[0].indexOf("~") != 0) {
			String[] loc7 = loc5[0].split("~");
			this.hasFakeAlignment = !loc7[0].equals(loc7[1]);
			loc5[0] = loc7[0];
			loc6 = Integer.parseInt(loc7[0]);
		}
		int loc8 = Integer.parseInt(loc5[0]);
		int loc9 = Integer.parseInt(loc5[1]);
		this.alignment = new Alignement(Alignment.valueOf(loc8), loc9);
		this.fakeAlignment = new Alignement(Alignment.valueOf(loc6), loc9);
		int loc10 = Integer.parseInt(loc5[2]);
		int loc11 = Integer.parseInt(loc5[3]);
		int loc12 = Integer.parseInt(loc5[4]);
		boolean loc13 = loc5[5].equals("1");
		this.rank = new Rank(loc10, loc11, loc12, loc13);
		loc5 = stream.read().split(",");
		this.life = Integer.parseInt(loc5[0]);
		this.lifeMax = Integer.parseInt(loc5[1]);
		loc5 = stream.read().split(",");
		this.energy = Integer.parseInt(loc5[0]);
		this.energyMax = Integer.parseInt(loc5[1]);
		this.initiative = stream.readInt();
		this.discernment = stream.readInt();
		int[][] loc15 = new int[3][];
		for (int loc16 = 3; loc16 > -1; --loc16)
			loc15[loc16] = new int[];
		for (int loc17 = 9; loc17 < 51; ++loc17) {
			loc5 = stream.read().split(",");
			int loc18 = Integer.parseInt(loc5[0]);
			int loc19 = Integer.parseInt(loc5[1]);
			int loc20 = Integer.parseInt(loc5[2]);
			int loc21 = Integer.parseInt(loc5[3]);

		}
	}

	@Override
	public void write(DofusStream stream) {
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

}
