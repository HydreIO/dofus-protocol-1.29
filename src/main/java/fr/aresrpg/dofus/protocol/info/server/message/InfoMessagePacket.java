package fr.aresrpg.dofus.protocol.info.server.message;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.InfosMessage;

public class InfoMessagePacket implements Packet {

	private int messageId;
	private String extraDatas;

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";");
		this.messageId = Integer.parseInt(data[0]);
		for (int i = 1; i < data.length - 1; i++)
			this.extraDatas += data[i];
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(messageId);
	}

	public int getMessageId() {
		return messageId;
	}

	public InfosMessage getMessage() {
		return InfosMessage.fromId(getMessageId());
	}

	/**
	 * @return the extraDatas
	 */
	public String getExtraDatas() {
		return extraDatas;
	}

	public InfoMessagePacket setMessageId(int messageId) {
		this.messageId = messageId;
		return this;
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "InfoMessagePacket [messageId=" + messageId + ", extraDatas=" + extraDatas + "]";
	}

}
