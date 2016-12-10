package fr.aresrpg.dofus.structures.game;

import fr.aresrpg.dofus.protocol.game.actions.GameActionEnum;

/**
 * 
 * @since
 */
public enum DuelResponse {

	ACCEPTED,
	DECLINED,
	REQUEST;

	public GameActionEnum toAction() {
		switch (this) {
			case ACCEPTED:
				return GameActionEnum.ACCEPT_FIGHT;
			case DECLINED:
				return GameActionEnum.DECLINE_FIGHT;
			case REQUEST:
				return GameActionEnum.ASK_FIGHT;
			default:
				return null;
		}
	}

}
