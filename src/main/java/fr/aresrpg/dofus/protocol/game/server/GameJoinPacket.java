/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.game.FightType;
import fr.aresrpg.dofus.structures.game.GameType;

/**
 * 
 * @since
 */
public class GameJoinPacket implements ServerPacket {

	private GameType state;
	private FightType fightType;
	private boolean isSpectator;
	private int startTimer;
	private boolean cancelButton;
	private boolean isDuel;

	@Override
	public void read(DofusStream stream) {
		this.state = GameType.valueOf(stream.readInt());
		this.cancelButton = stream.readInt() != 0;
		this.isDuel = stream.readInt() != 0;
		this.isSpectator = stream.readInt() != 0;
		this.startTimer = stream.readInt();
		this.fightType = FightType.fromId(stream.readInt());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(6).writeInt(getState().getType()).writeInt(isCancelButton() ? 1 : 0).writeInt(isDuel() ? 1 : 0).writeInt(isSpectator() ? 1 : 0).writeInt(getStartTimer())
				.writeInt(getFightType().getId());
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(GameType state) {
		this.state = state;
	}

	/**
	 * @param fightType
	 *            the fightType to set
	 */
	public void setFightType(FightType fightType) {
		this.fightType = fightType;
	}

	/**
	 * @param isSpectator
	 *            the isSpectator to set
	 */
	public void setSpectator(boolean isSpectator) {
		this.isSpectator = isSpectator;
	}

	/**
	 * @param startTimer
	 *            the startTimer to set
	 */
	public void setStartTimer(int startTimer) {
		this.startTimer = startTimer;
	}

	/**
	 * @param cancelButton
	 *            the cancelButton to set
	 */
	public void setCancelButton(boolean cancelButton) {
		this.cancelButton = cancelButton;
	}

	/**
	 * @param isDuel
	 *            the isDuel to set
	 */
	public void setDuel(boolean isDuel) {
		this.isDuel = isDuel;
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
	public GameType getState() {
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
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "GameJoinPacket [state=" + state + ", fightType=" + fightType + ", isSpectator=" + isSpectator + ", startTimer=" + startTimer + ", cancelButton=" + cancelButton + ", isDuel=" + isDuel
				+ "]";
	}

}
