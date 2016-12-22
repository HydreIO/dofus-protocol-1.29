package fr.aresrpg.dofus.protocol.friend.client;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class FriendRemovePacket implements ClientPacket {

	private String name;

	@Override
	public void read(DofusStream stream) {
		if (stream.available() < 1 || stream.peek().length() == 0 || stream.peek().equals("*")) return;
		this.name = stream.read();
	}

	@Override
	public void write(DofusStream stream) {
		if (name != null)
			stream.allocate(1).write(name);
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void handleClient(ClientPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "FriendRemovePacket [name=" + name + "]";
	}

}
