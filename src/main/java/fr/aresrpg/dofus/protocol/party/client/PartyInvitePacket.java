package fr.aresrpg.dofus.protocol.party.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class PartyInvitePacket implements ClientPacket {

	private String pname;

	@Override
	public void read(DofusStream stream) {
		this.pname = stream.read();
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

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(getPname());
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyInvitePacket [pname=" + pname + "]";
	}

}
