package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.structures.Community;

public class AccountCommunityPacket implements ServerPacket {

	private Community community;

	@Override
	public void read(DofusStream stream) {
		community = Community.valueOf(stream.readInt());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(community.getCode());
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public Community getCommunity() {
		return community;
	}

	public AccountCommunityPacket setCommunity(Community community) {
		this.community = community;
		return this;
	}

	@Override
	public String toString() {
		return "AccountCommunityPacket(" + "community:" + community + ")[" + getId() + "]";
	}
}
