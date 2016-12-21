/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.ProtocolRegistry.Bound;
import fr.aresrpg.dofus.protocol.game.actions.client.*;
import fr.aresrpg.dofus.protocol.game.actions.server.*;

public enum GameActions {

	UNKNOW(-1, UnknownAction.class, Bound.BOTH),

	// SERVER
	ERROR(0, GameErrorAction.class, Bound.SERVER),
	LIFE_CHANGE(100, GameLifeChangeAction.class, Bound.SERVER, 108, 110),
	PA_CHANGE(102, GamePaChangeAction.class, Bound.SERVER, 101, 111, 120, 168),
	PM_CHANGE(129, GamePmChangeAction.class, Bound.SERVER, 127, 128, 78, 169),
	KILL(103, GameKillAction.class, Bound.SERVER),
	SUMMON(180, GameSummonAction.class, Bound.SERVER, 181/* 780 (player invoc osa) */),
	FIGHT_JOIN_ERROR(903, GameJoinErrorAction.class, Bound.SERVER),

	// CLIENT
	INTERRACT(500, GameInteractionAction.class, Bound.CLIENT),
	LAUNCH_SPELL(300, GameLaunchSpellAction.class, Bound.CLIENT),
	DUEL(900, GameDuelAction.class, Bound.CLIENT),
	ACCEPT_DUEL(901, GameAcceptDuelAction.class, Bound.CLIENT),
	REFUSE_DUEL(902, GameRefuseDuelAction.class, Bound.CLIENT),

	// BOTH
	MOVE(1, GameMoveAction.class, Bound.BOTH),

	;

	private final int[] id;
	private final Class<? extends GameAction> action;
	private final Bound bound;

	GameActions(int id, Class<? extends GameAction> action, Bound bound, int... otherIds) {
		this.id = otherIds.length == 0 ? new int[] { id } : concat(id, otherIds);
		this.action = action;
		this.bound = bound;
	}

	/**
	 * @return the id
	 */
	public int[] getId() {
		return id;
	}

	public Class<? extends GameAction> getAction() {
		return action;
	}

	public Bound getBound() {
		return bound;
	}

	private boolean hasId(int id) {
		for (int i : getId())
			if (i == id) return true;
		return false;
	}

	private static int[] concat(int first, int... others) {
		int[] val = new int[others.length + 1];
		val[0] = first;
		for (int i = 0; i < others.length; i++)
			val[i + 1] = others[i];
		return val;
	}

	public static GameActions getAction(int id, Bound bound) {
		for (GameActions a : values())
			if (a.hasId(id) && (a.bound == Bound.BOTH || a.bound == bound))
				return a;
		return UNKNOW;
	}

	public static GameActions getAction(Class<? extends GameAction> action) {
		for (GameActions a : values())
			if (a.action == action)
				return a;
		return UNKNOW;
	}

}
