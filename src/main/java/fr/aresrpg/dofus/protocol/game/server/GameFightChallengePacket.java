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
import fr.aresrpg.dofus.structures.game.FightChallenge;

public class GameFightChallengePacket implements ServerPacket {

	private FightChallenge challenge;

	@Override
	public void read(DofusStream stream) {
		this.challenge = FightChallenge.parse(stream.read());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(challenge.serialize());
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	/**
	 * @return the challenge
	 */
	public FightChallenge getChallenge() {
		return challenge;
	}

	/**
	 * @param challenge
	 *            the challenge to set
	 */
	public void setChallenge(FightChallenge challenge) {
		this.challenge = challenge;
	}

	@Override
	public String toString() {
		return "GameFightChallengePacket [challenge=" + challenge + "]";
	}

}
