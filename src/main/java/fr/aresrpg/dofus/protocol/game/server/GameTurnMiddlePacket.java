package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.structures.game.FightEntity;
import fr.aresrpg.dofus.util.StringUtils;

public class GameTurnMiddlePacket implements ServerPacket {
	private FightEntity[] entities;

	@Override
	public void read(DofusStream stream) {
		entities = new FightEntity[stream.available()];
		for(int i = 0 ; i < entities.length ; i++){
			String[] data = StringUtils.split(stream.read() , ";");
			entities[i] = new FightEntity(
					Integer.parseInt(data[0]),
					Integer.parseInt(data[2]),
					Integer.parseInt(data[7]),
					Integer.parseInt(data[3]),
					Integer.parseInt(data[4])
			);
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(entities.length);
		for(FightEntity e : entities)
			stream.write(e.getId() + ";0;" + e.getLp() +
					";" + e.getAp() + ";" + e.getMp() + ";;" +
				e.getLpMax());

	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}
}
