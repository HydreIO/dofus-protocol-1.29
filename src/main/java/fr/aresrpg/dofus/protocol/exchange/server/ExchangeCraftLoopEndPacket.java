/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.CraftLoopEndResult;

/**
 * 
 * @since
 */
public class ExchangeCraftLoopEndPacket implements ServerPacket {

	private CraftLoopEndResult result;

	@Override
	public void read(DofusStream stream) {
		this.result = CraftLoopEndResult.valueOf(stream.readInt());
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(result.ordinal() + 1);
	}

	/**
	 * @return the result
	 */
	public CraftLoopEndResult getResult() {
		return result;
	}

	/**
	 * @param result
	 *            the result to set
	 */
	public void setResult(CraftLoopEndResult result) {
		this.result = result;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeCraftLoopEndPacket [result=" + result + "]";
	}

}
