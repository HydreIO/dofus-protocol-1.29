/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.mount.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.util.Convert;

public class MountXpPacket implements ServerPacket {

	private int percent;

	@Override
	public void read(DofusStream stream) {
		percent = Convert.toInt(stream.read() , 0);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(percent);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}


	public int getPercent() {
		return percent;
	}

	public MountXpPacket setPercent(int percent) {
		this.percent = percent;
		return this;
	}

	@Override
	public String toString() {
		return "MountXpPacket(percent=" + percent + ")[" + getId() + ']';
	}
}
