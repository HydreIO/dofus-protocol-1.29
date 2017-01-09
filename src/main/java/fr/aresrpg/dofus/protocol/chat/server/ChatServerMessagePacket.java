package fr.aresrpg.dofus.protocol.chat.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ChatServerMessagePacket implements ServerPacket {

	private String msg;

	@Override
	public void read(DofusStream stream) {
		this.msg = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(msg);
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ChatServerMessagePacket [msg=" + msg + "]";
	}

}
