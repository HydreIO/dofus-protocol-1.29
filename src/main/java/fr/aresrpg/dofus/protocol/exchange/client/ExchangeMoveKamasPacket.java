/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ExchangeMoveKamasPacket implements ClientPacket {

	private int amount;

	/**
	 * @param amount
	 */
	public ExchangeMoveKamasPacket(int amount) {
		super();
		this.amount = amount;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	@Override
	public void read(DofusStream stream) {
		this.amount = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(amount);
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeMoveKamasPacket [amount=" + amount + "]";
	}

}
