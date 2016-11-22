package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

public class AccountQueuePosition implements Packet {

	private int position;
	private int totalSubscriber;
	private int totalNoSubscribed;
	private int subscribed;
	private int positionInQueue;

	@Override
	public void read(DofusStream stream) throws IOException {
		String pos = stream.read();
		this.position = pos.isEmpty() ? -1 : Integer.parseInt(pos);
		this.totalSubscriber = stream.readInt();
		this.totalNoSubscribed = stream.readInt();
		this.subscribed = stream.readInt();
		this.positionInQueue = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(5).write(position == -1 ? "" : String.valueOf(position)).writeInt(totalSubscriber).writeInt(totalNoSubscribed).writeInt(subscribed).writeInt(positionInQueue);
	}

	@Override
	public void handle(PacketHandler handler) {

	}

	@Override
	public String toString() {
		return "AccountQueuePosition(position:" + position + "|totalabo:" + totalSubscriber + "|totalnonabo:" + totalNoSubscribed + "|subscribed:" + subscribed + "|positioninqueue:" + positionInQueue
				+ ")[" + getId() + "]";
	}
}
