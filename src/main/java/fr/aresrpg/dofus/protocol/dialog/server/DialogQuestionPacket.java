/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.dialog.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.util.Convert;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 
 * @since
 */
public class DialogQuestionPacket implements ServerPacket { // DQ1150|1239;819;818

	private int question;
	private String perso;
	private int[] response;
	private int[] questionParam;

	/**
	 * @param question
	 * @param response
	 * @param questionParam
	 */
	public DialogQuestionPacket(int question, int[] response, int... questionParam) {
		this.question = question;
		this.response = response;
		this.questionParam = questionParam;
	}

	public DialogQuestionPacket() {
	}

	@Override
	public void read(DofusStream stream) {
		String[] loc4 = stream.read().split(";");
		this.question = Integer.parseInt(loc4[0]);
		if (loc4.length > 1) {
			this.perso = loc4[1];
			loc4 = stream.read().split(";");
			this.response = Arrays.stream(loc4).mapToInt(Convert::toInt).toArray();
			//	if (loc4.length > 1) this.questionParam = Arrays.stream(loc4[1].split(",")).mapToInt(Convert::toInt).toArray();
		}
	}

	@Override
	public void write(DofusStream stream) {
		if (response == null) {
			stream.allocate(1).writeInt(question);
			return;
		}
		String q = question + (this.questionParam != null && this.questionParam.length != 0 ? Arrays.stream(questionParam).mapToObj(String::valueOf).collect(Collectors.joining(",")) : "");
		stream.allocate(2).write(q).write(Arrays.stream(response).mapToObj(String::valueOf).collect(Collectors.joining(";")));
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	/**
	 * @return the question
	 */
	public int getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(int question) {
		this.question = question;
	}

	/**
	 * @return the response
	 */
	public int[] getResponse() {
		return response;
	}

	/**
	 * @param response
	 *            the response to set
	 */
	public void setResponse(int[] response) {
		this.response = response;
	}

	/**
	 * @return the questionParam
	 */
	public int[] getQuestionParam() {
		return questionParam;
	}

	/**
	 * @param questionParam
	 *            the questionParam to set
	 */
	public void setQuestionParam(int[] questionParam) {
		this.questionParam = questionParam;
	}

	@Override
	public String toString() {
		return "DialogQuestionPacket [question=" + question + ", response=" + Arrays.toString(response) + ", questionParam=" + Arrays.toString(questionParam) + "]";
	}

}
