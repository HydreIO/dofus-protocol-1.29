package fr.aresrpg.dofus.protocol.account.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class AccountGetGiftsPacket implements Packet {
	private String language;

	@Override
	public void read(DofusStream stream) throws IOException {
		language = stream.read();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).write(language);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public String getLanguage() {
		return language;
	}

	public AccountGetGiftsPacket setLanguage(String language) {
		this.language = language;
		return this;
	}

	@Override
	public String toString() {
		return "AccountGetGiftsPacket(lang:'" + language + '\'' + ")[" + getId() + "]";
	}
}
