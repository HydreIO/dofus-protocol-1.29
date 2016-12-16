/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.structures.game.FightType;

/**
 * 
 * @since
 */
public class GameJoinPacket implements ServerPacket {

	private int state;
	private FightType fightType;
	private boolean isSpectator;
	private int startTimer;
	private boolean cancelButton;
	private boolean isDuel;

	@Override
	public void read(DofusStream stream) {
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
	public void write(DofusStream stream) {
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
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}


}
