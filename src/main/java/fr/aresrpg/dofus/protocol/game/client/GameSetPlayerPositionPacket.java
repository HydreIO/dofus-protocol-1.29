package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.*;

import java.io.IOException;

/**
 * 
 * @since
 */
public class GameSetPlayerPositionPacket implements Packet {

	private int cellNum;

	@Override
	public String toString() {
		return "GameSetPlayerPositionPacket(cellNum:" + cellNum + ")[" + getId() + "]";
	}

	@Override
	public void read(DofusStream stream) throws IOException {
		this.cellNum = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(1).writeInt(cellNum);
	}

	/**
	 * @return the cellNum
	 */
	public int getCellNum() {
		return cellNum;
	}

	/**
	 * @param cellNum
	 *            the cellNum to set
	 */
	public void setCellNum(int cellNum) {
		this.cellNum = cellNum;
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

}
