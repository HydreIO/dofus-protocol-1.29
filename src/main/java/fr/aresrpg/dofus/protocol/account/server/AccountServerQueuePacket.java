/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.account.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since 
 */
public class AccountServerQueuePacket implements ServerPacket {
	
	private int position;
	
	public AccountServerQueuePacket() {
	}

	/**
	 * @param position
	 */
	public AccountServerQueuePacket(int position) {
		this.position = position;
	}

	@Override
	public void read(DofusStream stream) {
		this.position =stream.readInt();
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(position);
	}
	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}/**
	 * @param position the position to set
	 */
	public void setPosition(int position) {
		this.position = position;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "AccountServerQueuePacket [position=" + position + "]";
	}

}
