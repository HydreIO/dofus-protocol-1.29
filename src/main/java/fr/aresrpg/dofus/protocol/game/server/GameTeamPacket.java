package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * 
 * @since
 */
public class GameTeamPacket implements ServerPacket {

	private int firstId;
	private TeamEntity[] entities;

	@Override
	public void read(DofusStream stream) {
		this.firstId = stream.readInt();
		this.entities = new TeamEntity[stream.available()];
		IntStream.range(0, stream.available()).forEach(i -> entities[i] = TeamEntity.parse(stream.read()));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(1 + entities.length).writeInt(firstId);
		Arrays.stream(entities).forEach(e -> stream.write(e.serialize()));
	}

	/**
	 * @return the firstId
	 */
	public int getFirstId() {
		return firstId;
	}

	/**
	 * @param firstId
	 *            the firstId to set
	 */
	public void setFirstId(int firstId) {
		this.firstId = firstId;
	}

	/**
	 * @return the entities
	 */
	public TeamEntity[] getEntities() {
		return entities;
	}

	/**
	 * @param entities
	 *            the entities to set
	 */
	public void setEntities(TeamEntity[] entities) {
		this.entities = entities;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	@Override
	public String toString() {
		return "GameTeamPacket [firstId=" + firstId + ", entities=" + Arrays.toString(entities) + "]";
	}

	public static class TeamEntity {

		private boolean add;
		private long entityId;
		private String name;
		private int lvl;

		/**
		 * @param add
		 * @param entityId
		 * @param name
		 * @param lvl
		 */
		public TeamEntity(boolean add, long entityId, String name, int lvl) {
			this.add = add;
			this.entityId = entityId;
			this.name = name;
			this.lvl = lvl;
		}

		public static TeamEntity parse(String datas) {
			String[] loc7 = datas.split(";");
			return new TeamEntity(loc7[0].charAt(0) == '+', Integer.parseInt(loc7[0].substring(1)), loc7[1], Integer.parseInt(loc7[2]));
		}

		public String serialize() {
			return (isAdd() ? "+" : "-") + getEntityId() + ";" + name + ";" + lvl;
		}

		/**
		 * @return the add
		 */
		public boolean isAdd() {
			return add;
		}

		/**
		 * @param add
		 *            the add to set
		 */
		public void setAdd(boolean add) {
			this.add = add;
		}

		/**
		 * @return the entityId
		 */
		public long getEntityId() {
			return entityId;
		}

		/**
		 * @param entityId
		 *            the entityId to set
		 */
		public void setEntityId(long entityId) {
			this.entityId = entityId;
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

		/**
		 * @return the lvl
		 */
		public int getLvl() {
			return lvl;
		}

		/**
		 * @param lvl
		 *            the lvl to set
		 */
		public void setLvl(int lvl) {
			this.lvl = lvl;
		}

		@Override
		public String toString() {
			return "TeamEntity [add=" + add + ", entityId=" + entityId + ", name=" + name + ", lvl=" + lvl + "]";
		}

	}

}
