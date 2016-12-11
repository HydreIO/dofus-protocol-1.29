package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.ProtocolRegistry.Bound;
import fr.aresrpg.dofus.protocol.game.actions.client.GameLaunchSpellAction;

public enum GameActions {
	ERROR(0, GameErrorAction.class, Bound.SERVER),
	MOVE(1, GameMoveAction.class, Bound.BOTH),
	LAUNCH_SPELL(300, GameLaunchSpellAction.class, Bound.CLIENT);

	private final int id;
	private final Class<? extends GameAction> action;
	private final Bound bound;

	GameActions(int id, Class<? extends GameAction> action, Bound bound) {
		this.id = id;
		this.action = action;
		this.bound = bound;
	}

	public int getId() {
		return id;
	}

	public Class<? extends GameAction> getAction() {
		return action;
	}

	public Bound getBound() {
		return bound;
	}

	public static GameActions getAction(int id, Bound bound) {
		for (GameActions a : values())
			if (a.id == id && (a.bound == Bound.BOTH || a.bound == bound))
				return a;
		return null;
	}

	public static GameActions getAction(Class<? extends GameAction> action) {
		for (GameActions a : values())
			if (a.action == action)
				return a;
		return null;
	}

}
