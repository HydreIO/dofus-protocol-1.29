package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

/**
 * 
 * @since
 */
public class GameSetPlayerPositionPacket implements Packet {

	private int cellNum;

	@Override
	public void read(DofusStream stream) {
		this.cellNum = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(cellNum);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
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
	public String toString() {
		return "GameSetPlayerPositionPacket(cellNum:" + cellNum + ")[" + getId() + "]";
	}
}
