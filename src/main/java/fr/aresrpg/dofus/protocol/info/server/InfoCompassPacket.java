package fr.aresrpg.dofus.protocol.info.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class InfoCompassPacket implements ServerPacket {

	private int x = -5000, y = -5000;

	@Override
	public void read(DofusStream stream) {
		if (stream.available() < 1) return;
		this.x = stream.readInt();
		this.y = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		if (x == -5000) return;
		stream.allocate(2).writeInt(x).writeInt(y);
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "InfosCompassPacket [x=" + x + ", y=" + y + "]";
	}

}
