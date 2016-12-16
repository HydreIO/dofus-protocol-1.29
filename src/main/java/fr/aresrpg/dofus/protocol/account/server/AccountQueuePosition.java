package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;
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
		this.subscribed = Convert.toInt(stream.read(), 0) == 1;
		this.positionInQueue = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(5).write(position == -1 ? "" : String.valueOf(position)).writeInt(totalSubscriber).writeInt(totalNoSubscribed).writeInt(subscribed ? 1 : 0).writeInt(positionInQueue);
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	/**
	 * @return the totalSubscriber
	 */
	public int getTotalSubscriber() {
		return totalSubscriber;
	}

	/**
	 * @param totalSubscriber
	 *            the totalSubscriber to set
	 */
	public void setTotalSubscriber(int totalSubscriber) {
		this.totalSubscriber = totalSubscriber;
	}

	/**
	 * @return the totalNoSubscribed
	 */
	public int getTotalNoSubscribed() {
		return totalNoSubscribed;
	}

	/**
	 * @param totalNoSubscribed
	 *            the totalNoSubscribed to set
	 */
	public void setTotalNoSubscribed(int totalNoSubscribed) {
		this.totalNoSubscribed = totalNoSubscribed;
	}

	/**
	 * @return the subscribed
	 */
	public boolean isSubscribed() {
		return subscribed;
	}

	/**
	 * @param subscribed
	 *            the subscribed to set
	 */
	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	/**
	 * @return the positionInQueue
	 */
	public int getPositionInQueue() {
		return positionInQueue;
	}

	/**
	 * @param positionInQueue
	 *            the positionInQueue to set
	 */
	public void setPositionInQueue(int positionInQueue) {
		this.positionInQueue = positionInQueue;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "AccountQueuePosition(position:" + position + "|totalSubscriber:" + totalSubscriber + "|totalNoSubscribed:" + totalNoSubscribed + "|subscribed:" + subscribed + "|positioninqueue:"
				+ positionInQueue
				+ ")[" + getId() + "]";
	}
}
