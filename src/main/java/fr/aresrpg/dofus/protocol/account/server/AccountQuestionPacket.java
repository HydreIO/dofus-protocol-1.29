/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class AccountQuestionPacket implements ServerPacket {
	private String question;

	@Override
	public void read(DofusStream stream) {
		try {
			question = URLDecoder.decode(stream.read(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	@Override
	public void write(DofusStream stream) {
		try {
			stream.allocate(1).write(URLEncoder.encode(question, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question
	 *            the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "AccountQuestionPacket(question:'" + question + "\')[" + getId() + "]";
	}
}
