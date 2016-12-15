package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.protocol.game.GameActionPacket;
import fr.aresrpg.dofus.protocol.game.actions.*;
import fr.aresrpg.dofus.util.StringUtils;

public class GameClientActionPacket implements GameActionPacket, ClientPacket {
	private GameActions type;
	private GameAction action;

	/**
	 * @param type
	 * @param action
	 */
	public GameClientActionPacket(GameActions type, GameAction action) {
		this.type = type;
		this.action = action;
	}

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		int id = Integer.parseInt(data.substring(0, 3));
		this.type = GameActions.getAction(id, ProtocolRegistry.Bound.CLIENT);
		action = createAction(this.type, id);
		stream.write(data.substring(3));
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
		stream.write(StringUtils.padLeft(Integer.toString(action.getId()[0]), 3, '0') + data);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public void handle(PacketHandler handler) {
		ClientPacket.super.handle(handler);
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

	/**
	 * @return the type
	 */
	public GameActions getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(GameActions type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "GameClientActionPacket [type=" + type + ", action=" + action + "]";
	}

}
