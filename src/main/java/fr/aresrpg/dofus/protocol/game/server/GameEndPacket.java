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
import fr.aresrpg.dofus.structures.game.FightResult;

/**
 * 
 * @since
 */
// 			GE	time	|player	|type 	|win	;id 	;name 	;lvl;dead	;minxp		;xp			;maxxp		;winxp	;guild	;mount	;drops 				;kamas
// Jowed =  GE	46968	|2397625|0 		|2 		;2397625;Jowed	;101;0 		;99823000	;100282955	;103885000	;11 	;0 		; 		;379~2,311~2 		;43
// Coffre = 							|2 		;-3 	;285 	;5 	;0 		; 			; 			; 			; 		; 		; 		;379~2,2583~1,311~2	;101
// Crabe = 								|0 		;-1 	;63 	;5 	;1 		; 			; 			;		 	; 		;	 	; 		; 					;
// Crabe = 								|0 		;-2 	;63 	;4 	;1 		; 			; 			; 			; 		; 		; 		; 					;
public class GameEndPacket implements ServerPacket {

	private int duration;
	private int firstPlayerId;
	private int bonus = -1;
	private FightResult result;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		if (data.contains(";")) {
			String[] loc7 = data.split(";");
			this.duration = Integer.parseInt(loc7[0]);
			this.bonus = Integer.parseInt(loc7[1]);
		} else this.duration = Integer.parseInt(data);
		this.firstPlayerId = stream.readInt();
		boolean pvp = stream.readInt() == 1;
		this.result = new FightResult(pvp);
		while (stream.available() > 0)
			this.result.parseEntity(stream.read());
	}

	private String serializeBonus() {
		if (hasBonus()) return duration + ";" + bonus;
		return String.valueOf(duration);
	}

	public boolean hasBonus() {
		return bonus != -1;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(3).write(serializeBonus()).writeInt(firstPlayerId).write(result.serialize());
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	/**
	 * @return the duration
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * @param duration
	 *            the duration to set
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * @return the firstPlayerId
	 */
	public int getFirstPlayerId() {
		return firstPlayerId;
	}

	/**
	 * @param firstPlayerId
	 *            the firstPlayerId to set
	 */
	public void setFirstPlayerId(int firstPlayerId) {
		this.firstPlayerId = firstPlayerId;
	}

	/**
	 * @return the bonus
	 */
	public int getBonus() {
		return bonus;
	}

	/**
	 * @param bonus
	 *            the bonus to set
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	/**
	 * @return the result
	 */
	public FightResult getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(FightResult result) {
		this.result = result;
	}

	@Override
	public String toString() {
		return "GameEndPacket [duration=" + duration + ", firstPlayerId=" + firstPlayerId + ", bonus=" + bonus + ", result=" + result + "]";
	}

}
