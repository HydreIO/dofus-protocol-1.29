package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.protocol.ProtocolRegistry;
import fr.aresrpg.dofus.protocol.game.GameActionPacket;
import fr.aresrpg.dofus.protocol.game.actions.GameAction;
import fr.aresrpg.dofus.protocol.game.actions.GameActions;
import fr.aresrpg.dofus.protocol.game.actions.UnknownAction;
import fr.aresrpg.dofus.util.Convert;

public class GameServerActionPacket implements GameActionPacket{
	private GameAction action;
	private int lastAction;
	private int entityId;

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";" , 4);
		this.lastAction = Convert.toInt(data[0] , 0);
		int id = Integer.parseInt(data[1]);
		action = createAction(id);
		if(id == 0) //Action Error
			return;
		entityId = Integer.parseInt(data[2]);
		stream.write(data[3]);
		stream.setReadIndex(0);
		action.read(stream);
	}

	private static GameAction createAction(int id){
		GameActions a = GameActions.getAction(id , ProtocolRegistry.Bound.SERVER);
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
		if(action.getId() == 0)
			stream.write(";0");
		else
			stream.write(lastAction + ";" + action.getId() + ";" + entityId + ";" + data);
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
		return "GameServerActionPacket(action=" + action +
				", lastAction=" + lastAction + ")[" + getId() + ']';
	}
}
