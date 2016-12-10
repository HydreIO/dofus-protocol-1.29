package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.protocol.game.actions.*;
import fr.aresrpg.dofus.structures.game.DuelResponse;
import fr.aresrpg.dofus.util.StringUtils;

import java.io.IOException;

public class GameActionPacket implements Packet {

	private int id;
	private GameAction action;
	private boolean serverAction;

	@Override
	public void read(DofusStream stream) throws IOException {
		String data = stream.read();
		serverAction = data.charAt(0) == ';';
		stream.setReadIndex(0);
		this.id = isServerAction() ? Integer.parseInt(data.substring(1, data.indexOf(';', 1))) : Integer.parseInt(data.substring(0, 3));
		parseAction(GameActionEnum.fromId(id)).setIsServer(isServerAction()).read(stream);
	}

	private GameAction parseAction(GameActionEnum action) {
		switch (action) {
			case MOVE:
				return this.action = new GameMoveAction();
			case INTERACTIVE_OBJECT:
				return this.action = new GameUseRessourceAction();
			case ASK_FIGHT:
				return this.action = new GameDefiAction(DuelResponse.REQUEST);
			case DECLINE_FIGHT:
				return this.action = new GameDefiAction(DuelResponse.DECLINED);
			case ACCEPT_FIGHT:
				return this.action = new GameDefiAction(DuelResponse.ACCEPTED);
			default:
				return this.action = new UnknownAction();
		}
	}

	/**
	 * @return the serverAction
	 */
	public boolean isServerAction() {
		return serverAction;
	}

	/**
	 * @return the action
	 */
	public GameAction getAction() {
		return action;
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		action.write(stream);
		String data = stream.read();
		stream.setWriteIndex(0);
		stream.write(StringUtils.padLeft(Integer.toString(id), 3, '0') + data);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "GameActionPacket [id=" + id + ", action=" + action + ", serverAction=" + serverAction + "]";
	}

	public GameActionPacket setAction(GameAction action) {
		this.action = action;
		return this;
	}

	public GameActionPacket setId(int id) {
		this.id = id;
		return this;
	}
}
