package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.ServerPacket;
import fr.aresrpg.dofus.protocol.ServerPacketHandler;
import fr.aresrpg.dofus.util.Convert;

public class AccountQueuePosition implements ServerPacket {

	private int position;
	private int totalSubscriber;
	private int totalNoSubscribed;
	private boolean subscribed;
	private int positionInQueue;

	@Override
	public void read(DofusStream stream) {
		String pos = stream.read();
		this.position = pos.isEmpty() ? -1 : Integer.parseInt(pos);
		this.totalSubscriber = stream.readInt();
		this.totalNoSubscribed = stream.readInt();
		this.subscribed = Convert.toInt(stream.read() , 0) == 1;
		this.positionInQueue = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(5).write(position == -1 ? "" : String.valueOf(position)).writeInt(totalSubscriber).writeInt(totalNoSubscribed).writeInt(subscribed ? 1 : 0).writeInt(positionInQueue);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "AccountQueuePosition(position:" + position + "|totalSubscriber:" + totalSubscriber + "|totalNoSubscribed:" + totalNoSubscribed + "|subscribed:" + subscribed + "|positioninqueue:" + positionInQueue
				+ ")[" + getId() + "]";
	}
}
