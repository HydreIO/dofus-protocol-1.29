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
import fr.aresrpg.dofus.protocol.game.GameActionPacket;
import fr.aresrpg.dofus.protocol.game.actions.*;
import fr.aresrpg.dofus.util.Convert;

public class GameServerActionPacket implements GameActionPacket, ServerPacket {

	private GameActions type;
	private GameAction action;
	private int lastAction = -1;
	private int entityId;

	/**
	 * @param type
	 * @param action
	 * @param lastAction
	 * @param entityId
	 */
	public GameServerActionPacket(GameActions type, GameAction action, int lastAction, int entityId) {
		this.type = type;
		this.action = action;
		this.lastAction = lastAction;
		this.entityId = entityId;
	}

	public GameServerActionPacket() {
	}

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";", 4);
		this.lastAction = Convert.toInt(data[0], -1);
		int id = Integer.parseInt(data[1]);
		this.type = GameActions.getAction(id, ProtocolRegistry.Bound.CLIENT);
		action = createAction(this.type, id);
		if (id == 0) // Action Error
			return;
		entityId = Integer.parseInt(data[2]);
		stream.write(data[3]);
		stream.setReadIndex(0);
		action.read(stream);
	}

	private static GameAction createAction(GameActions a, int id) {
		if (a == null)
			return new UnknownAction().setId(id);
		try {
			return a.getAction().newInstance();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void write(DofusStream stream) {
		action.write(stream);
		String data = stream.read();
		stream.setWriteIndex(0);
		if (action.getId()[0] == 0)
			stream.write(";0");
		else
			stream.write((lastAction == -1 ? "" : String.valueOf(lastAction)) + ";" + action.getId()[0] + ";" + entityId + ";" + data);
	}

	@Override
	public void handle(PacketHandler handler) {
		ServerPacket.super.handle(handler);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public GameActionPacket setAction(GameAction action) {
		this.action = action;
		return this;
	}

	@Override
	public GameAction getAction() {
		return action;
	}

	public int getLastAction() {
		return lastAction;
	}

	public GameServerActionPacket setLastAction(int lastAction) {
		this.lastAction = lastAction;
		return this;
	}

	public int getEntityId() {
		return entityId;
	}

	public GameServerActionPacket setEntityId(int entityId) {
		this.entityId = entityId;
		return this;
	}

	@Override
	public String toString() {
		return "GameServerActionPacket [type=" + type + ", action=" + action + ", lastAction=" + lastAction + ", entityId=" + entityId + "]";
	}

}
