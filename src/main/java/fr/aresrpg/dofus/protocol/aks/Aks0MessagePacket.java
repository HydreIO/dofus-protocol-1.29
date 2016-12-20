package fr.aresrpg.dofus.protocol.aks;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class Aks0MessagePacket implements ServerPacket {

	private int msgId;
	private String msgDatas;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		this.msgId = Integer.parseInt(data);
		if (stream.available() > 0) this.msgDatas = stream.read();
	}

	/**
	 * @return the msgId
	 */
	public int getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId
	 *            the msgId to set
	 */
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the msgDatas
	 */
	public String getMsgDatas() {
		return msgDatas;
	}

	/**
	 * @param msgDatas
	 *            the msgDatas to set
	 */
	public void setMsgDatas(String msgDatas) {
		this.msgDatas = msgDatas;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(msgDatas == null ? 1 : 2).write(msgId + "");
		if (msgDatas != null) stream.write(msgDatas);

	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "Aks0MessagePacket [msgId=" + msgId + ", msgDatas=" + msgDatas + "]";
	}

}
