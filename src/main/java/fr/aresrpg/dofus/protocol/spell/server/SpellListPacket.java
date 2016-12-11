package fr.aresrpg.dofus.protocol.spell.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.structures.Spell;
import fr.aresrpg.dofus.util.Crypt;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SpellListPacket implements Packet{
	private List<Spell> spells;

	@Override
	public void read(DofusStream stream) {
		spells = new ArrayList<>();
		String[] data = stream.read().split(";");
		for(String d : data) {
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
		for(Spell s : spells)
			sb.add(s.getId() + s.getLevel() + Character.toString(Crypt.hashToIndex(s.getPosition())));
		stream.allocate(1);
	}

	@Override
	public void handle(PacketHandler handler) {
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
