/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.actions;

import fr.aresrpg.dofus.protocol.DofusStream;
import fr.aresrpg.dofus.structures.Orientation;
import fr.aresrpg.dofus.util.Compressor;
import fr.aresrpg.dofus.util.Crypt;

import java.util.ArrayList;
import java.util.List;

public class GameMoveAction implements GameAction {
	private List<PathFragment> path = new ArrayList<>();

	@Override
	public void read(DofusStream stream) {
		String data = stream.read();
		for (int i = 0; i < data.length(); i += 3)
			add(Compressor.uncompressCellId(data.substring(i + 1, i + 3)), Orientation.valueOf(Crypt.indexOfHash(data.charAt(i))));
	}

	protected void add(int cellId, Orientation dir) {
		path.add(new PathFragment(cellId, dir));
	}

	@Override
	public void write(DofusStream stream) {
		StringBuilder sb = new StringBuilder();
		for (PathFragment point : path)
			sb.append(Crypt.hashToIndex(point.getDirection().ordinal())).append(Compressor.compressCellId(point.getCellId()));
		stream.allocate(1).write(sb.toString());
	}

	public GameMoveAction setPath(List<PathFragment> path) {
		this.path = path;
		return this;
	}

	/**
	 * @return the path
	 */
	public List<PathFragment> getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "GameMoveAction{" + "path=" + path + '}';
	}

	public static class PathFragment {
		private int cellId;
		private Orientation direction;

		/**
		 * @param cellId
		 * @param direction
		 */
		public PathFragment(int cellId, Orientation direction) {
			super();
			this.cellId = cellId;
			this.direction = direction;
		}

		/**
		 * @return the cellId
		 */
		public int getCellId() {
			return cellId;
		}

		/**
		 * @param cellId
		 *            the cellId to set
		 */
		public void setCellId(int cellId) {
			this.cellId = cellId;
		}

		/**
		 * @return the direction
		 */
		public Orientation getDirection() {
			return direction;
		}

		/**
		 * @param direction
		 *            the direction to set
		 */
		public void setDirection(Orientation direction) {
			this.direction = direction;
		}

		@Override
		public String toString() {
			return "PathFragment [cellId=" + cellId + ", direction=" + direction + "]";
		}

	}
}
