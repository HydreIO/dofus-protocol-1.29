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
	private int playerId;
	private String pseudo;
	private String msg;

	@Override
	public void read(DofusStream stream) {
		this.chat = stream.peek().isEmpty() ? null : Chat.valueOf(stream.readChar());
		if (chat == null) {
			chat = Chat.COMMON;
			stream.read();
		}
		this.playerId = stream.readInt();
		this.pseudo = stream.read();
		this.msg = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(4).writeChar(chat.getCode()).writeInt(playerId).write(pseudo).write(msg);
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
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(int playerId) {
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
