package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;
import fr.aresrpg.dofus.util.Convert;

import java.io.IOException;

public class AccountQueuePosition implements Packet {

	private int position;
	private int totalSubscriber;
	private int totalNoSubscribed;
	private boolean subscribed;
	private int positionInQueue;

	@Override
	public void read(DofusStream stream) throws IOException {
		String pos = stream.read();
		this.position = pos.isEmpty() ? -1 : Integer.parseInt(pos);
		this.totalSubscriber = stream.readInt();
		this.totalNoSubscribed = stream.readInt();
		this.subscribed = Convert.toInt(stream.read() , 0) == 1;
		this.positionInQueue = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(5).write(position == -1 ? "" : String.valueOf(position)).writeInt(totalSubscriber).writeInt(totalNoSubscribed).writeInt(subscribed ? 1 : 0).writeInt(positionInQueue);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "AccountQueuePosition(position:" + position + "|totalSubscriber:" + totalSubscriber + "|totalNoSubscribed:" + totalNoSubscribed + "|subscribed:" + subscribed + "|positioninqueue:" + positionInQueue
				+ ")[" + getId() + "]";
	}
}
