package fr.aresrpg.dofus.protocol.spell.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class SpellBoostPacket implements ClientPacket {

	private int spellId;

	@Override
	public void read(DofusStream stream) {
		this.spellId = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(spellId);
	}

	/**
	 * @return the spellId
	 */
	public int getSpellId() {
		return spellId;
	}

	/**
	 * @param spellId
	 *            the spellId to set
	 */
	public void setSpellId(int spellId) {
		this.spellId = spellId;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "SpellBoostPacket [spellId=" + spellId + "]";
	}

}
