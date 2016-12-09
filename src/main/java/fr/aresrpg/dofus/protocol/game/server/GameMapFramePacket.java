package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.map.Frame;
import fr.aresrpg.dofus.util.Convert;
import fr.aresrpg.dofus.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GameMapFramePacket implements Packet {
	private Map<Integer, Frame> frames;

	/**
	 * @return the frames
	 */
	public Map<Integer, Frame> getFrames() {
		return frames;
	}

	@Override
	public void read(DofusStream stream) throws IOException {
		stream.read(); // Skip separator
		frames = new HashMap<>(stream.available());
		while (stream.available() > 0) {
			String[] data = StringUtils.split(stream.read(), ";");
			int cellId = Integer.parseInt(data[0]);
			int id = Convert.toInt(data[1], 0);
			Boolean interactive = data[2].isEmpty() ? null : Integer.parseInt(data[2]) == 1;
			frames.put(cellId, new Frame(id, interactive));
		}
	}

	@Override
	public void write(DofusStream stream) throws IOException {
		stream.allocate(frames.size() + 1).write(""); // Separator
		frames.forEach((k, v) -> stream.write(k.intValue() + ";" + v.getId() + ";" + (v.isInteractive() == null ? "" : v.isInteractive().booleanValue() ? "1" : "0")));
	}

	@Override
	public void handle(PacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "GameMapFramePacket(frames=" + frames + ")[" + getId() + ']';
	}
}
