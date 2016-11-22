package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class AccountLoginErrPacket implements Packet {
	private Error err;
	// In minute
	private int time;
	private String version;

	@Override
	public void read(DofusStream stream) {
		String value = stream.read();
		err = Error.valueOf(value.charAt(0));
		if (err == Error.KICKED)
			time = Integer.parseInt(value.substring(1)) * 24 * 64 + stream.readInt() * 64 + stream.readInt();
		else if (err == Error.BAD_VERSION)
			version = value.substring(1);
	}

	@Override
	public void write(DofusStream stream) {
		switch (err) {
			case KICKED:
				Duration d = Duration.ofMinutes(time);
				long days = d.get(ChronoUnit.DAYS);
				long hours = d.minusDays(days).get(ChronoUnit.HOURS);
				long minutes = d.minus(Duration.ofDays(days).plusHours(hours)).get(ChronoUnit.MINUTES);
				stream.allocate(3).write(String.valueOf(err.getKey() + days)).writeInt((int) hours).writeInt((int) minutes);
				return;
			case BAD_VERSION:
				stream.allocate(1).write(err.getKey() + version);
				return;
			default:
				stream.allocate(1).write(String.valueOf(err.getKey()));
				return;
		}
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
		return "AccountLoginErrPacket(err:" + err.name() + (err == Error.KICKED ? "|time:" + time : "") + (err == Error.BAD_VERSION ? "|version:'" + version + '\'' : "") + ")[" + getId() + "]";
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
			for (Error s : values())
				if (s.getKey() == key)
					return s;
			return null;
		}
	}
}
