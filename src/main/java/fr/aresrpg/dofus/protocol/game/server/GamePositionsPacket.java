/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;

import java.util.ArrayList;
import java.util.List;

public class GamePositionsPacket implements ServerPacket {

	private List<FightPosition> positions = new ArrayList<>(); // keep order

	@Override
	public void read(DofusStream stream) {
		stream.read();
		while (stream.available() > 0) {
			String[] data = stream.read().split(";");
			positions.add(new FightPosition(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2])));
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1 + positions.size()).write("");
		for (FightPosition p : positions)
			stream.write(p.getEntityId() + ";" + p.getPosition() + ";" + p.getUnknowIndex());
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "GamePositionsPacket [positions=" + positions + "]";
	}

	/**
	 * @return the positions
	 */
	public List<FightPosition> getPositions() {
		return positions;
	}

	public static class FightPosition {
		private int entityId;
		private int position;
		private int unknowIndex;

		/**
		 * @param entityId
		 * @param position
		 * @param unknowIndex
		 */
		public FightPosition(int entityId, int position, int unknowIndex) {
			super();
			this.entityId = entityId;
			this.position = position;
			this.unknowIndex = unknowIndex;
		}

		/**
		 * @return the entityId
		 */
		public int getEntityId() {
			return entityId;
		}

		/**
		 * @param entityId
		 *            the entityId to set
		 */
		public void setEntityId(int entityId) {
			this.entityId = entityId;
		}

		/**
		 * @return the position
		 */
		public int getPosition() {
			return position;
		}

		/**
		 * @param position
		 *            the position to set
		 */
		public void setPosition(int position) {
			this.position = position;
		}

		/**
		 * @return the unknowIndex
		 */
		public int getUnknowIndex() {
			return unknowIndex;
		}

		/**
		 * @param unknowIndex
		 *            the unknowIndex to set
		 */
		public void setUnknowIndex(int unknowIndex) {
			this.unknowIndex = unknowIndex;
		}

		@Override
		public String toString() {
			return "FightPosition [entityId=" + entityId + ", position=" + position + ", unknowIndex=" + unknowIndex + "]";
		}

	}
}
