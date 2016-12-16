package fr.aresrpg.dofus.protocol.exchange.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ExchangeCraftPublicPacket implements ServerPacket {

	private boolean craftPublicMode;

	private int itemid = -1;
	private int multiCraftSkill = -1;

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		if (data.length() == 1)
			this.craftPublicMode = data.equals("+");
		else {
			this.craftPublicMode = data.charAt(0) == '+';
			data = data.substring(1);
			this.itemid = Integer.parseInt(data);
			if (craftPublicMode && stream.peek().length() > 0) this.multiCraftSkill = Integer.parseInt(stream.read().split(";")[0]);
		}
	}

	@Override
	public void write(DofusStream stream) {
		if (itemid == -1) stream.allocate(1).writeChar(craftPublicMode ? '+' : '-'); // TODO verif car je ne sais pas si c'est '-' le deuxieme
		else stream.allocate(2).write((craftPublicMode ? "+" : "-") + itemid).write(multiCraftSkill == -1 ? "" : toString().valueOf(multiCraftSkill));
	}

	/**
	 * @return the craftPublicMode
	 */
	public boolean isCraftPublicMode() {
		return craftPublicMode;
	}

	/**
	 * @param craftPublicMode
	 *            the craftPublicMode to set
	 */
	public void setCraftPublicMode(boolean craftPublicMode) {
		this.craftPublicMode = craftPublicMode;
	}

	/**
	 * @return the itemid
	 */
	public int getItemid() {
		return itemid;
	}

	/**
	 * @param itemid
	 *            the itemid to set
	 */
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	/**
	 * @return the multiCraftSkill
	 */
	public int getMultiCraftSkill() {
		return multiCraftSkill;
	}

	/**
	 * @param multiCraftSkill
	 *            the multiCraftSkill to set
	 */
	public void setMultiCraftSkill(int multiCraftSkill) {
		this.multiCraftSkill = multiCraftSkill;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ExchangeCraftPublicPacket [craftPublicMode=" + craftPublicMode + ", itemid=" + itemid + ", multiCraftSkill=" + multiCraftSkill + "]";
	}

}
