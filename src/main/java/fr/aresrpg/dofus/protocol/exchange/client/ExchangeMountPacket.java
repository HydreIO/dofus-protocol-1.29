package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ExchangeMountPacket implements ClientPacket {

	private MountExchange exchange;
	private int mountOrCertifid;

	/**
	 * @param exchange
	 * @param mountOrCertifid
	 */
	public ExchangeMountPacket(MountExchange exchange, int mountOrCertifid) {
		this.exchange = exchange;
		this.mountOrCertifid = mountOrCertifid;
	}

	/**
	 * @return the exchange
	 */
	public MountExchange getExchange() {
		return exchange;
	}

	/**
	 * @param exchange
	 *            the exchange to set
	 */
	public void setExchange(MountExchange exchange) {
		this.exchange = exchange;
	}

	/**
	 * @return the mountOrCertifid
	 */
	public int getMountOrCertifid() {
		return mountOrCertifid;
	}

	/**
	 * @param mountOrCertifid
	 *            the mountOrCertifid to set
	 */
	public void setMountOrCertifid(int mountOrCertifid) {
		this.mountOrCertifid = mountOrCertifid;
	}

	@Override
	public void read(DofusStream stream) {
		String d = stream.read();
		this.exchange = MountExchange.fromChar(d.charAt(0));
		this.mountOrCertifid = Integer.parseInt(d.substring(1));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(getExchange().getC() + String.valueOf(getMountOrCertifid()));
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeMountPacket [exchange=" + exchange + ", mountOrCertifid=" + mountOrCertifid + "]";
	}

	public static enum MountExchange {
		IN_SHED_FROM_INV('p'),
		IN_INV_FROM_SHED('g'),
		IN_CERTIF_FROMSHED('c'),
		IN_SHED_FROM_CERTIF('C'),
		KILL_MOUNT('f');

		private char c;

		/**
		 * @param c
		 */
		private MountExchange(char c) {
			this.c = c;
		}

		/**
		 * @return the c
		 */
		public char getC() {
			return c;
		}

		public static MountExchange fromChar(char c) {
			for (MountExchange e : values())
				if (e.getC() == c) return e;
			return null;
		}

	}
}
