package fr.aresrpg.dofus.protocol.subarea.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.structures.Alignment;
import fr.aresrpg.dofus.structures.map.Subarea;

import java.util.ArrayList;
import java.util.List;

public class SubareaListPacket implements Packet{
	private List<Subarea> subareas;

	@Override
	public void read(DofusStream stream) {
		subareas = new ArrayList<>();
		while (stream.available() > 0){
			String[] data = stream.read().split(";");
			subareas.add(new Subarea(Integer.parseInt(data[0]) ,
					Alignment.valueOf(data[1])));
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(subareas.size());
		for(Subarea s : subareas)
			stream.write(s.getId() + ";" + s.getAlignment().ordinal());
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public List<Subarea> getSubareas() {
		return subareas;
	}

	public SubareaListPacket setSubareas(List<Subarea> subareas) {
		this.subareas = subareas;
		return this;
	}

	@Override
	public String toString() {
		return "SubareaListPacket{" +
				"subareas=" + subareas +
				'}';
	}
}
