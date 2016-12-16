package fr.aresrpg.dofus.protocol.party.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class PartyFollowPacket implements ClientPacket {

	private String pname;
	private boolean follow;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		this.follow = data.charAt(0) == '+';
		this.pname = data.substring(1);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write((follow ? "+" : "-") + pname);
	}

	/**
	 * @return the pname
	 */
	public String getPname() {
		return pname;
	}

	/**
	 * @param pname
	 *            the pname to set
	 */
	public void setPname(String pname) {
		this.pname = pname;
	}

	/**
	 * @return the follow
	 */
	public boolean isFollow() {
		return follow;
	}

	/**
	 * @param follow
	 *            the follow to set
	 */
	public void setFollow(boolean follow) {
		this.follow = follow;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyFollowPacket [pname=" + pname + ", follow=" + follow + "]";
	}

}
