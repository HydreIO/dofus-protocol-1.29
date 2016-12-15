package fr.aresrpg.dofus.protocol.dialog.server;

import fr.aresrpg.dofus.protocol.*;

/**
 * 
 * @since
 */
public class DialogCreateOkPacket implements ServerPacket {

	private int id;

	// les autres info sont dans les langs il me semble
	// var _loc5 = this.api.datacenter.Sprites.getItemAt(_loc4);
	// loc6 = loc5.color1 2 3
	// {name: _loc5.name, gfx: _loc5.gfxID, id: _loc4, customArtwork: _loc5.customArtwork, colors: _loc6});
	/**
	 * @param id
	 */
	public DialogCreateOkPacket(int id) {
		super();
		this.id = id;
	}

	@Override
	public void read(DofusStream stream) {
		this.id = stream.readInt();
	}

	/**
	 * @return the id
	 */
	public int getNpcId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setNpcId(int id) {
		this.id = id;
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1).writeInt(id);
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "DialogCreateOkPacket [id=" + id + "]";
	}

}
