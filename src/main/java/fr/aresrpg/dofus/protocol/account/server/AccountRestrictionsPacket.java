package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountRestrictionsPacket implements Packet {
	private int restrictions;

	@Override
	public void read(DofusStream stream) {
		restrictions = stream.readIntRadix(36);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeIntRadix(restrictions , 36);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getRestrictions() {
		return restrictions;
	}

	public AccountRestrictionsPacket setRestrictions(int restrictions) {
		this.restrictions = restrictions;
		return this;
	}

	@Override
	public String toString() {
		return "AccountRestrictionsPacket(restrictions=" + restrictions +
				")[" + getId() + ']';
	}
}
