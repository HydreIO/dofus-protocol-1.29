package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.protocol.ProtocolRegistry;
import fr.aresrpg.dofus.protocol.game.GameActionPacket;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;
import fr.aresrpg.dofus.protocol.game.actions.GameActions;
import fr.aresrpg.dofus.protocol.game.actions.UnknownAction;
import fr.aresrpg.dofus.util.StringUtils;

public class GameClientActionPacket implements GameActionPacket{
	private GameAction action;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		int id = Integer.parseInt(data.substring(0 , 3));
		action = createAction(id);
		stream.write(data.substring(3));
		stream.setReadIndex(0);
		action.read(stream);
	}

	private static GameAction createAction(int id){
		GameActions a = GameActions.getAction(id , ProtocolRegistry.Bound.CLIENT);
		if(a == null)
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
		stream.write(StringUtils.padLeft(Integer.toString(action.getId()) , 3 , '0') + data);
	}

	@Override
	public void handle(PacketHandler handler) {
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

	@Override
	public String toString() {
		return "GameClientActionPacket(action=" + action +
				")[" + getId() + ']';
	}
}
