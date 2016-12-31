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
import fr.aresrpg.dofus.structures.game.Effect;
import fr.aresrpg.dofus.util.Convert;
import fr.aresrpg.dofus.util.StringUtils;

import java.util.Arrays;
import java.util.StringJoiner;

public class GameEffectPacket implements ServerPacket {
	private Effect effect;
	private long entities[];

	@Override
	public void read(DofusStream stream) {
		String[] data = StringUtils.split(stream.read(), ";");
		String[] rawEntities = data[1].split(",");
		entities = new long[rawEntities.length];
		for (int i = 0; i < entities.length; i++)
			entities[i] = Convert.toLong(rawEntities[i]);
		effect = new Effect(
				Integer.parseInt(data[0]),
				Convert.toInt(data[2]),
				Convert.toLong(data[3]),
				Convert.toInt(data[4]),
				data[5],
				Convert.toInt(data[6]),
				Convert.toInt(data[7]));
	}

	@Override
	public void write(DofusStream stream) {
		StringJoiner joiner = new StringJoiner(";");
		StringJoiner rawEntities = new StringJoiner(",");
		for (int i = 0; i < entities.length; i++)
			rawEntities.add(Long.toString(entities[i]));
		joiner.add(Integer.toString(effect.getTypeId()))
				.add(rawEntities.toString())
				.add(Integer.toString(effect.getParam1()))
				.add(Long.toString(effect.getParam2()))
				.add(Integer.toString(effect.getParam3()))
				.add(effect.getParam4())
				.add(Integer.toString(effect.getRemainingTurn()))
				.add(Integer.toString(effect.getSpellId()));
		stream.allocate(1).write(joiner.toString());
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public Effect getEffect() {
		return effect;
	}

	public void setEffect(Effect effect) {
		this.effect = effect;
	}

	/**
	 * @return the entities
	 */
	public long[] getEntities() {
		return entities;
	}

	/**
	 * @param entities
	 *            the entities to set
	 */
	public void setEntities(long[] entities) {
		this.entities = entities;
	}

	@Override
	public String toString() {
		return "GameEffectPacket{" +
				"effect=" + effect +
				", entities=" + Arrays.toString(entities) +
				'}';
	}
}
