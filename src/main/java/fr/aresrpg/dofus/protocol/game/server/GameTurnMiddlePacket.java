/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.game.FightEntity;

import java.util.Arrays;
import java.util.stream.IntStream;

public class GameTurnMiddlePacket implements ServerPacket {
	private FightEntity[] entities;

	@Override
	public void read(DofusStream stream) {
		stream.read();
		entities = new FightEntity[stream.available()];
		IntStream.range(0, entities.length).forEach(i -> entities[i] = FightEntity.parse(stream.read()));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(entities.length + 1);
		stream.write("");
		Arrays.stream(entities).forEach(e -> stream.write(e.serialize()));
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	/**
	 * @return the entities
	 */
	public FightEntity[] getEntities() {
		return entities;
	}

	@Override
	public String toString() {
		return "GameTurnMiddlePacket [entities=" + Arrays.toString(entities) + "]";
	}

}
