package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemAddErrorPacket implements ServerPacket {

	private AddResult result;

	public ItemAddErrorPacket() {
	}

	/**
	 * @param result
	 */
	public ItemAddErrorPacket(AddResult result) {
		this.result = result;
	}

	@Override
	public void read(DofusStream stream) {
		this.result = AddResult.valueOf(stream.readChar());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeChar(result.getCode());
	}

	/**
	 * @return the result
	 */
	public AddResult getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(AddResult result) {
		this.result = result;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public static enum AddResult {
		FULL('F'),
		LVL_TOO_LOW('L'),
		ALREADY_EQUIPED('A');

		private char code;

		private AddResult(char code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public char getCode() {
			return code;
		}

		public static AddResult valueOf(char code) {
			if (code == 'F') return FULL;
			else if (code == 'L') return LVL_TOO_LOW;
			else if (code == 'A') return ALREADY_EQUIPED;
			else throw new IllegalArgumentException("The char '" + code + "' is invalid");
		}
	}

	@Override
	public String toString() {
		return "ItemAddErrorPacket [result=" + result + "]";
	}

}
