/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.subarea.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.structures.Alignment;
import fr.aresrpg.dofus.structures.map.Subarea;
import fr.aresrpg.dofus.util.Convert;

import java.util.Arrays;

public class SubareaListPacket implements ServerPacket {
	private Subarea[] subareas;

	@Override
	public void read(DofusStream stream) {
		subareas = new Subarea[stream.available()];
		for(int i = 0 ; i < subareas.length ; i++) {
			String[] data = stream.read().split(";");
			subareas[i] = new Subarea(Convert.toInt(data[0]), Alignment.valueOf(data.length > 1 ? Convert.toInt(data[1]) : 0));
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(subareas.length);
		for (Subarea s : subareas)
			stream.write(Convert.toDofusString(s.getId()) + ";" + s.getAlignment().ordinal());
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public Subarea[] getSubareas() {
		return subareas;
	}

	public SubareaListPacket setSubareas(Subarea[] subareas) {
		this.subareas = subareas;
		return this;
	}

	@Override
	public String toString() {
		return "SubareaListPacket{" +
				"subareas=" + Arrays.toString(subareas) +
				'}';
	}
}
