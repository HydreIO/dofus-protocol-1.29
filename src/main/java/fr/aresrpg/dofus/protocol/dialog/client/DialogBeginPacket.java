package fr.aresrpg.dofus.protocol.dialog.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class DialogBeginPacket implements ClientPacket {

	private int npcId;

	/**
	 * @param npcId
	 */
	public DialogBeginPacket(int npcId) {
		this.npcId = npcId;
	}

	@Override
	public void read(DofusStream stream) {
		this.npcId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(npcId);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	/**
	 * @return the npcId
	 */
	public int getNpcId() {
		return npcId;
	}

	/**
	 * @param npcId
	 *            the npcId to set
	 */
	public void setNpcId(int npcId) {
		this.npcId = npcId;
	}

	@Override
	public String toString() {
		return "DialogBeginPacket [npcId=" + npcId + "]";
	}

}
