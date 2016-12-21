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

public class InfoMessagePacket implements ServerPacket {

	private String messageId;
	private String extraDatas;

	@Override
	public void read(DofusStream stream) {
		String[] data = stream.read().split(";");
		this.messageId = data[0];
		StringBuilder b = new StringBuilder();
		for (int i = 1; i < data.length; i++)
			b.append(data[i]);
		this.extraDatas = b.toString();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(messageId + (extraDatas != null && !extraDatas.isEmpty() ? ";" + extraDatas : ""));
	}

	/**
	 * @return the messageId
	 */
	public String getMessageId() {
		return messageId;
	}

	public InfosMessage getMessage() {
		return InfosMessage.fromId(Integer.parseInt(getMessageId()));
	}

	/**
	 * @return the extraDatas
	 */
	public String getExtraDatas() {
		return extraDatas;
	}

	public InfoMessagePacket setMessageId(String messageId) {
		this.messageId = messageId;
		return this;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "InfoMessagePacket [message=" + getMessage() + "(" + messageId + "), extraDatas=" + extraDatas + "]";
	}

}
