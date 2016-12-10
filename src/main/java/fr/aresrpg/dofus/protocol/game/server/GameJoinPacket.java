package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.game.FightType;

import java.io.IOException;

/**
 * 
 * @since
 */
public class GameJoinPacket implements Packet {

	private int state;
	private FightType fightType;
	private boolean isSpectator;
	private int startTimer;
	private boolean cancelButton;
	private boolean isDuel;

	@Override
	public void read(DofusStream stream) throws IOException {
		this.state = stream.readInt();
		this.cancelButton = stream.readInt() != 0;
		this.isDuel = stream.readInt() != 0;
		this.isSpectator = stream.readInt() != 0;
		this.startTimer = stream.readInt();
		this.fightType = FightType.fromId(stream.readInt());
	}

	@Override
	public String toString() {
		return "GameJoinPacket(state:" + state + "|fightType:" + fightType + "|isSpectator:" + isSpectator + "|timer:" + startTimer + "|cancelButton:" + cancelButton + "|isDuel:" + isDuel
				+ ")[" + getId() + "]";
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(6).writeInt(getState()).writeInt(isCancelButton() ? 1 : 0).writeInt(isDuel() ? 1 : 0).writeInt(isSpectator() ? 1 : 0).writeInt(getStartTimer())
				.writeInt(getFightType().getId());
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
	public FightType getFightType() {
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
	public boolean isDuel() {
		return isDuel;
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
