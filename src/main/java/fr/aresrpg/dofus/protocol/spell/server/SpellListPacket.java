/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.spell.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Spell;
import fr.aresrpg.dofus.util.Crypt;

import java.util.*;

public class SpellListPacket implements ServerPacket {
	private List<Spell> spells;

	@Override
	public void read(DofusStream stream) {
		spells = new ArrayList<>();
		String[] data = stream.read().split(";");
		for (String d : data) {
			String[] subData = d.split("~");
			spells.add(new Spell(
					Integer.parseInt(subData[0]),
					Integer.parseInt(subData[1]),
					Crypt.indexOfHash(subData[2].charAt(0))));
		}
	}

	@Override
	public void write(DofusStream stream) {
		StringJoiner sb = new StringJoiner(";");
		for (Spell s : spells) {
			sb.add(s.getId() + "~" + s.getLevel() + "~" + Character.toString(Crypt.hashToIndex(s.getPosition())));
		}
		stream.allocate(1).write(sb.toString() + ";");
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public List<Spell> getSpells() {
		return spells;
	}

	public SpellListPacket setSpells(List<Spell> spells) {
		this.spells = spells;
		return this;
	}

	@Override
	public String toString() {
		return "SpellListPacket{" +
				"spells=" + spells +
				'}';
	}
}
