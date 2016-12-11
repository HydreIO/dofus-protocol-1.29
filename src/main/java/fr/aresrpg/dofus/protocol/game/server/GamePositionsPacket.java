package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.util.HashMap;
import java.util.Map;

public class GamePositionsPacket implements Packet{
	private Map<Integer , Integer> positions;

	@Override
	public void read(DofusStream stream) {
		positions = new HashMap<>();
		stream.read();
		while(stream.available() > 0){
			String[] data = stream.read().split(";");
			positions.put(Integer.parseInt(data[0]) , Integer.parseInt(data[1]));
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1 + positions.size()).write("");
		for(Map.Entry<Integer , Integer> entry : positions.entrySet())
			stream.write(entry.getKey() + ";" + entry.getValue());
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public Map<Integer, Integer> getPositions() {
		return positions;
	}

	public GamePositionsPacket setPositions(Map<Integer, Integer> positions) {
		this.positions = positions;
		return this;
	}

	@Override
	public String toString() {
		return "GamePositionsPacket(positions=" + positions +
				")[" + getId() + ']';
	}
}
