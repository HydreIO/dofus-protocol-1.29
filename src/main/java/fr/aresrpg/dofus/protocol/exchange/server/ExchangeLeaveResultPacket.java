package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ExchangeLeaveResultPacket implements ServerPacket {

	private boolean success;

	@Override
	public void read(DofusStream stream) {
		this.success = stream.read().equals("a");
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(success ? "a" : "");
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeLeaveResultPacket [success=" + success + "]";
	}

}
