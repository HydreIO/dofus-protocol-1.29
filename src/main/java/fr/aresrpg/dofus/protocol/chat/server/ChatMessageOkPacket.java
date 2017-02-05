/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.chat.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.Chat;

public class ChatMessageOkPacket implements ServerPacket { // cMK%|2412051|Tiger-fire|je je gagne po et sasa ^^|

	private Chat chat;
	private long playerId;
	private String pseudo;
	private String msg;
	private String extraDatas;

	@Override
	public void read(DofusStream stream) {
		this.chat = stream.peek().isEmpty() ? null : Chat.valueOf(stream.readChar());
		if (chat == null) {
			chat = Chat.COMMON;
			stream.read();
		}
		this.playerId = stream.readLong();
		this.pseudo = stream.read();
		this.msg = stream.read();
		if (stream.available() > 0) this.extraDatas = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(extraDatas == null ? 4 : 5).writeChar(chat.getCode()).writeLong(playerId).write(pseudo).write(msg);
		if (extraDatas != null) stream.write(extraDatas);
	}

	/**
	 * @return the extraDatas
	 */
	public String getExtraDatas() {
		return extraDatas;
	}

	/**
	 * @param extraDatas
	 *            the extraDatas to set
	 */
	public void setExtraDatas(String extraDatas) {
		this.extraDatas = extraDatas;
	}

	/**
	 * @return the chat
	 */
	public Chat getChat() {
		return chat;
	}

	/**
	 * @param chat
	 *            the chat to set
	 */
	public void setChat(Chat chat) {
		this.chat = chat;
	}

	/**
	 * @return the playerId
	 */
	public long getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the pseudo
	 */
	public String getPseudo() {
		return pseudo;
	}

	/**
	 * @param pseudo
	 *            the pseudo to set
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
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
		return "ChatMessageOkPacket [chat=" + chat + ", playerId=" + playerId + ", pseudo=" + pseudo + ", msg=" + msg + "]";
	}

}
