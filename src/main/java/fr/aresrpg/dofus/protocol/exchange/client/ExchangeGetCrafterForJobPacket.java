package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ExchangeGetCrafterForJobPacket implements ClientPacket {

	private int jobid;

	/**
	 * @param jobid
	 */
	public ExchangeGetCrafterForJobPacket(int jobid) {
		this.jobid = jobid;
	}

	/**
	 * @return the jobid
	 */
	public int getJobid() {
		return jobid;
	}

	/**
	 * @param jobid
	 *            the jobid to set
	 */
	public void setJobid(int jobid) {
		this.jobid = jobid;
	}

	@Override
	public String toString() {
		return "ExchangeGetCrafterForJobPacket [jobid=" + jobid + "]";
	}

	@Override
	public void read(DofusStream stream) {
		this.jobid = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(getJobid());
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

}
