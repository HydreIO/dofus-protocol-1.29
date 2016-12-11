package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class AccountQuestionPacket implements Packet {
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

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "AccountQuestionPacket(question:'" + question + "\')[" + getId() + "]";
	}
}
