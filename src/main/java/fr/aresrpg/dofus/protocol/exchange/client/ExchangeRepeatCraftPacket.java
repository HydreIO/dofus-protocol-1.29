package fr.aresrpg.dofus.protocol.exchange.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ExchangeRepeatCraftPacket implements ClientPacket {

	private int amount;
	private boolean cancel;

	public ExchangeRepeatCraftPacket(int amount) {
		this.amount = amount;
	}

	/**
	 * @param cancel
	 *            useless param just for avoid ambiguous construct
	 */
	public ExchangeRepeatCraftPacket(boolean cancel) {
		this.cancel = true;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the cancel
	 */
	public boolean isCancel() {
		return cancel;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}

	/**
	 * @param cancel
	 *            the cancel to set
	 */
	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		char c = data.charAt(0);
		if (c == 'r') this.cancel = true;
		else if (c == 'R') this.amount = Integer.parseInt(data.substring(1));
		else throw new IllegalArgumentException("The char '" + c + "' is invalid !");
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).write(isCancel() ? "r" : "R" + getAmount());
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeRepeatCraftPacket [amount=" + amount + ", cancel=" + cancel + "]";
	}

}
