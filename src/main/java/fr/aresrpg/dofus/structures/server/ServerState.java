/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.server;

public enum ServerState {
	OFFLINE(0),
	ONLINE(1),
	SAVING(2);

	private final int code;

	ServerState(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static ServerState valueOf(int code) {
		if(code >= 0 && code < values().length)
			return values()[code];
		else
			return null;
	}
}
