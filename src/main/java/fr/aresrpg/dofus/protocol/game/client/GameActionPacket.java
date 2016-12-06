package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;
import fr.aresrpg.dofus.protocol.game.actions.GameMoveAction;
import fr.aresrpg.dofus.protocol.game.actions.UnknownAction;
import fr.aresrpg.dofus.util.StringUtils;

import java.io.IOException;

public class GameActionPacket implements Packet{

	private int id;
	private GameAction action;

	@Override
	public void read(DofusStream stream) throws IOException {
		String data = stream.read();
		id = Integer.parseInt(data.substring(0 , 3));
		switch (id) {
			case 1:
				action = new GameMoveAction();
				break;
			default:
				action = new UnknownAction();
				break;
		}
		stream.write(data.substring(3));
		stream.setReadIndex(0);
		action.read(stream);
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		action.write(stream);
		String data = stream.read();
		stream.setWriteIndex(0);
		stream.write(StringUtils.padLeft(Integer.toString(id) , 3 , '0') + data);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "GameActionPacket(id=" + id+ ", action=" + action + ")[" + getId() + ']';
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
