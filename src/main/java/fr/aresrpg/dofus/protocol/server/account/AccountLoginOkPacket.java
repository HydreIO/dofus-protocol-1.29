package fr.aresrpg.dofus.protocol.server.account;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountLoginOkPacket implements Packet{
	private int code;

	@Override
	public void read(DofusStream stream) {
		code = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {

	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}
}
