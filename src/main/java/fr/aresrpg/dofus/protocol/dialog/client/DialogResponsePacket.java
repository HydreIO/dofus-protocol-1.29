/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.dialog.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class DialogResponsePacket implements ClientPacket {

	private int questionId;
	private int responseId;

	/**
	 * @param questionId
	 * @param responseId
	 */
	public DialogResponsePacket(int questionId, int responseId) {
		this.questionId = questionId;
		this.responseId = responseId;
	}

	public DialogResponsePacket() {
	}

	/**
	 * @return the questionId
	 */
	public int getQuestionId() {
		return questionId;
	}

	/**
	 * @param questionId
	 *            the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	/**
	 * @return the responseId
	 */
	public int getResponseId() {
		return responseId;
	}

	/**
	 * @param responseId
	 *            the responseId to set
	 */
	public void setResponseId(int responseId) {
		this.responseId = responseId;
	}

	@Override
	public void read(DofusStream stream) {
		this.questionId = stream.readInt();
		this.responseId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(questionId).writeInt(responseId);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "DialogResponsePacket [questionId=" + questionId + ", responseId=" + responseId + "]";
	}

}
