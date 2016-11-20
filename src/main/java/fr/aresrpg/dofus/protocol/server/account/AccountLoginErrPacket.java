package fr.aresrpg.dofus.protocol.server.account;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountLoginErrPacket implements Packet{
	private Error err;

	//In minute
	private int time;

	@Override
	public void read(DofusStream stream) {
		String value = stream.read();
		if(value.length() != 1)
			throw new IllegalStateException("Bad packet format");
		err = Error.valueOf(value.charAt(0));
		if(err == Error.INVALID_ACCOUNT_WITH_DURATION)
			time = stream.readInt() * 24 * 64 + stream.readInt() * 64 + stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {

	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public Error getErr() {
		return err;
	}

	public int getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "AccountLoginErrPacket{" +
				"err=" + err +
				(err == Error.INVALID_ACCOUNT_WITH_DURATION ? ", time=" + time : "")+
				'}';
	}

	public enum Error {
		ALREADY_CONNECTING('a'),
		BANNED('b'),
		ALREADY_CONNECTED('c'),
		DISCONNECTED('d'),
		OLD_ACCOUNT_USE_NEW('e'),
		BAD_PASSWORD('f'),
		INVALID_ACCOUNT_WITH_DURATION('k'),
		ACCOUNT_IN_MAINTENANCE('m'),
		CONNECTION_NOT_TERMINATED('n'),
		CHOSE_NICKNAME('r'),
		OLD_ACCOUNT('o'),
		INVALID_ACCOUNT('p'),
		NICKNAME_ALREADY_USED('s'),
		NEW_VERSION('v'),
		SERVER_FULL('w');


		private final char key;

		Error(char key) {
			this.key = key;
		}

		public char getKey() {
			return key;
		}

		public static Error valueOf(char key) {
			for(Error s : values())
				if(s.getKey() == key)
					return s;
			return null;
		}
	}
}
