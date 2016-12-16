/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemDropErrorPacket implements ServerPacket {

	private DropResult result;

	public ItemDropErrorPacket() {
	}

	/**
	 * @param result
	 */
	public ItemDropErrorPacket(DropResult result) {
		this.result = result;
	}

	@Override
	public void read(DofusStream stream) {
		this.result = DropResult.valueOf(stream.readChar());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeChar(result.getCode());
	}

	/**
	 * @return the result
	 */
	public DropResult getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(DropResult result) {
		this.result = result;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public static enum DropResult {
		FULL('F'),
		CANT_DROP('E');

		private char code;

		private DropResult(char code) {
			this.code = code;
		}

		/**
		 * @return the code
		 */
		public char getCode() {
			return code;
		}

		public static DropResult valueOf(char code) {
			if (code == 'F') return FULL;
			else if (code == 'E') return CANT_DROP;
			else throw new IllegalArgumentException("The char '" + code + "' is invalid");
		}
	}

	@Override
	public String toString() {
		return "ItemDropOkPacket [result=" + result + "]";
	}

}
