/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.client;

import fr.aresrpg.dofus.protocol.ClientPacket;
import fr.aresrpg.dofus.protocol.ClientPacketHandler;
import fr.aresrpg.dofus.protocol.DofusStream;

/**
 * 
 * @since
 */
public class GameSetPlayerPositionPacket implements ClientPacket {

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
	public void handleClient(ClientPacketHandler handler) {
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
