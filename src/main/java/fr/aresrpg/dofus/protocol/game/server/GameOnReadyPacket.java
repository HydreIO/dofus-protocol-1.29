package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

/**
 * 
 * @since
 */
public class GameOnReadyPacket implements Packet {

	private boolean ready;
	private String playerid;

	@Override
	public String toString() {
		return "GameOnReadyPacket(ready:" + ready + "|playerid:" + playerid + ")[" + getId() + "]";
	}

	@Override
	public void read(DofusStream stream) throws IOException {
		String val = stream.read();
		this.ready = val.charAt(0) == '1';
		this.playerid = val.substring(1);
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).write((ready ? "1" : "0") + playerid);
	}

	/**
	 * @return the ready
	 */
	public boolean isReady() {
		return ready;
	}

	/**
	 * @return the playerid
	 */
	public String getPlayerid() {
		return playerid;
	}

	/**
	 * @param ready
	 *            the ready to set
	 */
	public void setReady(boolean ready) {
		this.ready = ready;
	}

	/**
	 * @param playerid
	 *            the playerid to set
	 */
	public void setPlayerid(String playerid) {
		this.playerid = playerid;
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
