package fr.aresrpg.dofus.protocol.fight.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.game.FightInfos;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @since
 */
public class FightListPacket implements ServerPacket {

	private List<FightInfos> fights = new ArrayList<>();

	@Override
	public void read(DofusStream stream) {
		while (stream.available() > 0) {
			String data = stream.read();
			if (!data.isEmpty()) fights.add(FightInfos.parse(data));
		}
	}

	/**
	 * @return the fights
	 */
	public List<FightInfos> getFights() {
		return fights;
	}

	/**
	 * @param fights
	 *            the fights to set
	 */
	public void setFights(List<FightInfos> fights) {
		this.fights = fights;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(fights.size());
		fights.forEach(f -> stream.write(f.serialize()));
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "FightListPacket [fights=" + fights + "]";
	}

}
