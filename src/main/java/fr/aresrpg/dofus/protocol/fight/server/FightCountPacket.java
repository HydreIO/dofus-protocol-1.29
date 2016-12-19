package fr.aresrpg.dofus.protocol.fight.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class FightCountPacket implements ServerPacket {

	private int count;

	@Override
	public void read(DofusStream stream) {
		if (stream.available() > 0) this.count = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(count);
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count
	 *            the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "FightCountPacket [count=" + count + "]";
	}

}
