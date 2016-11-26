package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.protocol.Packet;
import fr.aresrpg.dofus.protocol.PacketHandler;

import java.io.IOException;

public class GameMapDataPacket implements Packet{
	private int mapId;
	private String subid;
	private String decryptKey;

	@Override
	public void read(DofusStream stream) throws IOException {
		stream.read(); //Skip separator
		mapId = stream.readInt();
		subid = stream.read();
		decryptKey = stream.read();
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(4).write("") //Force separator
				.writeInt(mapId).write(subid).write(decryptKey);
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	public int getMapId() {
		return mapId;
	}

	public String getSubid() {
		return subid;
	}

	public String getDecryptKey() {
		return decryptKey;
	}

	public GameMapDataPacket setMapId(int mapId) {
		this.mapId = mapId;
		return this;
	}

	public GameMapDataPacket setSubid(String subid) {
		this.subid = subid;
		return this;
	}

	public GameMapDataPacket setDecryptKey(String decryptKey) {
		this.decryptKey = decryptKey;
		return this;
	}

	@Override
	public String toString() {
		return "GameMapDataPacket(mapId=" + mapId + ", subid='" + subid + '\'' +
				", decryptKey='" + decryptKey + '\'' + ")[" + getId() + ']';
	}
}
