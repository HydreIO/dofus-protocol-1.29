package fr.aresrpg.dofus.protocol.spell.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class SpellMoveToUsedPacket implements ClientPacket {

	private int spellId;
	private int position;

	@Override
	public void read(DofusStream stream) {
		this.spellId = stream.readInt();
		this.position = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(spellId).writeInt(spellId);
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

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public String toString() {
		return "SpellMoveToUsedPacket [spellId=" + spellId + ", position=" + position + "]";
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

}
