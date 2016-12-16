package fr.aresrpg.dofus.protocol.party.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class PartyLeaderPacket implements ServerPacket {

	private String newLeader;

	@Override
	public void read(DofusStream stream) {
		this.newLeader = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(newLeader);
	}

	/**
	 * @return the newLeader
	 */
	public String getNewLeader() {
		return newLeader;
	}

	/**
	 * @param newLeader
	 *            the newLeader to set
	 */
	public void setNewLeader(String newLeader) {
		this.newLeader = newLeader;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyLeaderPacket [newLeader=" + newLeader + "]";
	}

}
