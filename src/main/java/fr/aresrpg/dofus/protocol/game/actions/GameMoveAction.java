package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.structures.PathDirection;
import fr.aresrpg.dofus.util.Compressor;
import fr.aresrpg.dofus.util.Crypt;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameMoveAction extends GameAction {
	private Map<Integer, PathDirection> path;
	private Map<Integer, String> mob; // quand un mob bouge c'est la forme "GA;1;-6;afUdf8" 1 = move | -6 = index du mob reçu dans le packet GM | afUdf8 = path
	// pour un player c'est GA0 et l'id du joueur a la place de l'index du mob

	@Override
	public void readClient(DofusStream stream) {
		String data = stream.read();
		path = new LinkedHashMap<>();
		for (int i = 0; i < data.length(); i += 3) {
			path.put(Compressor.uncompressCellId(data.substring(i + 1, i + 3)),
					PathDirection.valueOf(Crypt.indexOfHash(data.charAt(i))));
		}
	}

	@Override
	public void writeClient(DofusStream stream) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<Integer, PathDirection> point : path.entrySet())
			sb.append(Crypt.hashToIndex(point.getValue().ordinal()))
					.append(Compressor.compressCellId(point.getKey()));
		stream.allocate(1).write(sb.toString());
	}

	@Override
	public void readServer(DofusStream stream) {
		// TODO

	}

	@Override
	public void writeServer(DofusStream stream) {
		// TODO

	}

	public GameMoveAction setPath(Map<Integer, PathDirection> path) {
		this.path = path;
		return this;
	}

	public Map<Integer, PathDirection> getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "GameMoveAction{" +
				"path=" + path +
				'}';
	}

}
