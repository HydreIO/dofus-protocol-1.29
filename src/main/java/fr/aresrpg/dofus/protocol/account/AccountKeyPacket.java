/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountKeyPacket implements Packet {
	protected int key;
	protected String data;

	@Override
	public void read(DofusStream stream) {
		String buf = stream.read();
		key = Integer.parseInt(String.valueOf(buf.charAt(0)), 16);
		data = buf.substring(1);
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(key + data);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getKey() {
		return key;
	}

	public String getData() {
		return data;
	}

	public AccountKeyPacket setKey(int key) {
		this.key = key;
		return this;
	}

	public AccountKeyPacket setData(String data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return "AccountKeyPacket(" + "key:" + key + ", data:'" + data + "\'}[" + getId() + "]";
	}
}
