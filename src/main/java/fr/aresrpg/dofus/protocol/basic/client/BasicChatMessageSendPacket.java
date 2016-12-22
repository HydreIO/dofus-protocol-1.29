package fr.aresrpg.dofus.protocol.basic.client;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Chat;

/**
 * 
 * @since
 */
public class BasicChatMessageSendPacket implements ClientPacket { // "BM" + sDest + "|" + sMessage + "|" + _loc16

	private String msg;
	private String dest;

	@Override
	public void read(DofusStream stream) {
		this.dest = stream.read();
		this.msg = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).write(dest).write(msg);
	}

	/**
	 * @return the dest
	 */
	public String getDest() {
		return dest;
	}

	public boolean isPm() {
		return dest.length() != 1;
	}

	public Chat getChatType() {
		return Chat.valueOf(getDest().charAt(0));
	}

	/**
	 * @param chat
	 *            the chat to set
	 */
	public void setChat(Chat chat) {
		this.dest = chat.getCode() + "";
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param dest
	 *            the dest to set
	 */
	public void setDest(String dest) {
		this.dest = dest;
	}

	/**
	 * @param msg
	 *            the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "BasicChatMessageSendPacket [msg=" + msg + ", dest=" + dest + "]";
	}

}
