/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.party.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class PartyFollowAllPacket implements ClientPacket {

	private boolean stop;
	private String pname;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		this.stop = data.charAt(0) == '-';
		this.pname = data.substring(1);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write((stop ? "-" : "+") + pname);
	}

	/**
	 * @return the stop
	 */
	public boolean isStop() {
		return stop;
	}

	/**
	 * @param stop
	 *            the stop to set
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
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
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "PartyFollowAllPacket [stop=" + stop + ", pname=" + pname + "]";
	}

}
