package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.protocol.ProtocolRegistry.State;

/**
 * 
 * @since
 */
public class ExchangeBuyToNpcResultPacket implements ServerPacket {

	private boolean success;

	@Override
	public void read(DofusStream stream) {
		this.success = stream.read().charAt(0) == State.OK;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeChar(success ? State.OK : State.ERROR);
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
		return "ExchangeBuyToNpcResultPacket [success=" + success + "]";
	}

}
