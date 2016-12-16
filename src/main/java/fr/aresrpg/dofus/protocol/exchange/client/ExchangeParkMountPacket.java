/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ExchangeParkMountPacket implements ClientPacket {

	private ParkMountExchange exchange;
	private int mountId;

	/**
	 * @param exchange
	 * @param mountOrCertifid
	 */
	public ExchangeParkMountPacket(ParkMountExchange exchange, int mountId) {
		this.exchange = exchange;
		this.mountId = mountId;
	}

	/**
	 * @return the exchange
	 */
	public ParkMountExchange getExchange() {
		return exchange;
	}

	/**
	 * @param exchange
	 *            the exchange to set
	 */
	public void setExchange(ParkMountExchange exchange) {
		this.exchange = exchange;
	}

	/**
	 * @return the mountId
	 */
	public int getMountId() {
		return mountId;
	}

	/**
	 * @param mountId
	 *            the mountId to set
	 */
	public void setMountId(int mountId) {
		this.mountId = mountId;
	}

	@Override
	public void read(DofusStream stream) {
		String d = stream.read();
		this.exchange = ParkMountExchange.fromChar(d.charAt(0));
		this.mountId = Integer.parseInt(d.substring(1));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(getExchange().getC() + String.valueOf(getMountId()));
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeParkMountPacket [exchange=" + exchange + ", mountId=" + mountId + "]";
	}

	public static enum ParkMountExchange {
		IN_PARK_FROM_SHED('p'),
		IN_SHED_FROM_PARK('g'),
		KILL_MOUNT_IN_PARK('f');

		private char c;

		/**
		 * @param c
		 */
		private ParkMountExchange(char c) {
			this.c = c;
		}

		/**
		 * @return the c
		 */
		public char getC() {
			return c;
		}

		public static ParkMountExchange fromChar(char c) {
			for (ParkMountExchange e : values())
				if (e.getC() == c) return e;
			return null;
		}

	}
}
