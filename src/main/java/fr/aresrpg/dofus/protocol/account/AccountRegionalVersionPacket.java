package fr.aresrpg.dofus.protocol.account;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountRegionalVersionPacket implements Packet {
	public static final int NOT_SET = Integer.MIN_VALUE;
	private int region = NOT_SET;

	@Override
	public void read(DofusStream stream) {
		if (stream.available() > 0)
			region = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		if (region != NOT_SET)
			stream.allocate(1).writeInt(region);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getRegion() {
		return region;
	}

	public AccountRegionalVersionPacket setRegion(int region) {
		this.region = region;
		return this;
	}

	@Override
	public String toString() {
		return "AccountRegionalVersionPacket(" + (region == NOT_SET ? "" : "region:" + region) + ")[" + getId() + "]";
	}
}
