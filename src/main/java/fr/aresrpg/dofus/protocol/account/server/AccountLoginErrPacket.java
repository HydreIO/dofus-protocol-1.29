/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;

import java.time.Duration;

public class AccountLoginErrPacket implements ServerPacket {
	private Error err;
	// In minute
	private int time;
	private String version;

	public AccountLoginErrPacket() {
	}

	/**
	 * @param err
	 * @param time
	 * @param version
	 */
	public AccountLoginErrPacket(Error err, int time, String version) {
		this.err = err;
		this.time = time;
		this.version = version;
	}

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
				long days = d.toDays();
				long hours = d.minusDays(days).toHours();
				long minutes = d.minus(Duration.ofDays(days).plusHours(hours)).toMinutes();
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

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param err
	 *            the err to set
	 */
	public void setErr(Error err) {
		this.err = err;
	}

	/**
	 * @param time
	 *            the time to set
	 */
	public void setTime(int time) {
		this.time = time;
	}

	/**
	 * @param version
	 *            the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
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
