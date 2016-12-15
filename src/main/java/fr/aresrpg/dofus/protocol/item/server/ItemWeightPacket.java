package fr.aresrpg.dofus.protocol.item.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class ItemWeightPacket implements ServerPacket {

	private int currentWeight;
	private int maxWeight;

	public ItemWeightPacket() {
	}

	/**
	 * @param currentWeight
	 * @param maxWeight
	 */
	public ItemWeightPacket(int currentWeight, int maxWeight) {
		super();
		this.currentWeight = currentWeight;
		this.maxWeight = maxWeight;
	}

	/**
	 * @return the currentWeight
	 */
	public int getCurrentWeight() {
		return currentWeight;
	}

	/**
	 * @param currentWeight
	 *            the currentWeight to set
	 */
	public void setCurrentWeight(int currentWeight) {
		this.currentWeight = currentWeight;
	}

	/**
	 * @return the maxWeight
	 */
	public int getMaxWeight() {
		return maxWeight;
	}

	/**
	 * @param maxWeight
	 *            the maxWeight to set
	 */
	public void setMaxWeight(int maxWeight) {
		this.maxWeight = maxWeight;
	}

	@Override
	public void read(DofusStream stream) {
		this.currentWeight = stream.readInt();
		this.maxWeight = stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(2).writeInt(currentWeight).writeInt(maxWeight);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "ItemWeightPacket [currentWeight=" + currentWeight + ", maxWeight=" + maxWeight + "]";
	}

}
