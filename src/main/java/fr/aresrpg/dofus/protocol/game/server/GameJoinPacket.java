package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

/**
 * 
 * @since
 */
public class GameJoinPacket implements Packet {

	private int state;
	private int fightType;
	private boolean isSpectator;
	private int startTimer;
	private boolean cancelButton; // a veriff
	private boolean hasChallenge; // a veriff

	@Override
	public void read(DofusStream stream) throws IOException {
		this.state = stream.readInt();
		this.cancelButton = stream.readInt() != 0;
		this.hasChallenge = stream.readInt() != 0;
		this.isSpectator = stream.readInt() != 0;
		this.startTimer = stream.readInt();
		this.fightType = stream.readInt();
	}

	@Override
	public String toString() {
		return "GameJoinPacket(state:" + state + "|fightType:" + fightType + "|isSpectator:" + isSpectator + "|timer:" + startTimer + "|cancelButton:" + cancelButton + "|hasChallenge:" + hasChallenge
				+ ")[" + getId() + "]";
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(6).writeInt(getState()).writeInt(isCancelButton() ? 1 : 0).writeInt(hasChallenge() ? 1 : 0).writeInt(isSpectator() ? 1 : 0).writeInt(getStartTimer()).writeInt(getFightType());
	}

	/**
	 * @return the startTimer
	 */
	public int getStartTimer() {
		return startTimer;
	}

	/**
	 * @return the fightType
	 */
	public int getFightType() {
		return fightType;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @return the cancelButton
	 */
	public boolean isCancelButton() {
		return cancelButton;
	}

	/**
	 * @return the hasChallenge
	 */
	public boolean hasChallenge() {
		return hasChallenge;
	}

	/**
	 * @return the isSpectator
	 */
	public boolean isSpectator() {
		return isSpectator;
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
