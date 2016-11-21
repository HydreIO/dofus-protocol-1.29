package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

public class AccountLoginErrPacket implements Packet{
	private Error err;

	//In minute
	private int time;
	private String version;

	@Override
	public void read(DofusStream stream) {
		String value = stream.read();
		err = Error.valueOf(value.charAt(0));
		if(err == Error.KICKED)
			time = Integer.parseInt(value.substring(1)) * 24 * 64 + stream.readInt() * 64 + stream.readInt();
		else if(err == Error.BAD_VERSION)
			version = value.substring(1);
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
				(err == Error.KICKED ? ", time=" + time : "")+
				(err == Error.BAD_VERSION ? ", version='" + version + '\'' : "")+
				'}';
	}

	public enum Error {
		ALREADY_LOGGED('a'),
		BANNED('b'),
		ALREADY_LOGGED_GAME_SERVER('c'),
		DISCONNECTED('d'),
		OLD_ACCOUNT_USE_NEW('e'),
		BAD_PASSWORD('f'),
		KICKED('k'),
		ACCOUNT_IN_MAINTENANCE('m'),
		CONNECTION_NOT_FINISHED('n'),
		CHOSE_NICKNAME('r'),
		OLD_ACCOUNT('o'),
		INVALID_ACCOUNT('p'),
		NICKNAME_ALREADY_USED('s'),
		BAD_VERSION('v'),
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
