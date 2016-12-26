/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.info.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.InfosMessage;
import fr.aresrpg.dofus.structures.InfosMsgType;

public class InfoMessagePacket implements ServerPacket {

	private InfosMsgType type;
	private int messageId;
	private String extraDatas;

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";");
		String s = data[0];
		this.type = InfosMsgType.valueOf(Integer.parseInt(String.valueOf(s.charAt(0))));
		this.messageId = Integer.parseInt(s.substring(1));
		StringBuilder b = new StringBuilder();
		for (int i = 1; i < data.length; i++)
			b.append(data[i]);
		this.extraDatas = b.toString();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(type.ordinal() + "" + messageId + (extraDatas != null && !extraDatas.isEmpty() ? ";" + extraDatas : ""));
	}

	/**
	 * @return the messageId
	 */
	public int getMessageId() {
		return messageId;
	}

	public InfosMessage getMessage() {
		return InfosMessage.fromId(type, messageId);
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

	/**
	 * @return the type
	 */
	public InfosMsgType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(InfosMsgType type) {
		this.type = type;
	}

	/**
	 * @param extraDatas
	 *            the extraDatas to set
	 */
	public void setExtraDatas(String extraDatas) {
		this.extraDatas = extraDatas;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "InfoMessagePacket [type=" + type + ", messageId=" + messageId + ", extraDatas=" + extraDatas + "]";
	}
}
