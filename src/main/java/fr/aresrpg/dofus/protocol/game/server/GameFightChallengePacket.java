package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.structures.game.FightChallenge;

public class GameFightChallengePacket implements ServerPacket {

	private FightChallenge challenge;

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";");
		setChallenge(new FightChallenge(
				Integer.parseInt(data[0]),
				data[1].equals("1"),
				Integer.parseInt(data[2]),
				Integer.parseInt(data[3]),
				Integer.parseInt(data[4]),
				Integer.parseInt(data[5]),
				Integer.parseInt(data[6])
		));
	}

	@Override
	public void write(DofusStream stream) {

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
	 * @param challenge the challenge to set
	 */
	public void setChallenge(FightChallenge challenge) {
		this.challenge = challenge;
	}

}
