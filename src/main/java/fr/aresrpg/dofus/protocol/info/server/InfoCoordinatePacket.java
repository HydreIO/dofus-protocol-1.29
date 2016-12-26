package fr.aresrpg.dofus.protocol.info.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.util.StringJoiner;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @since
 */
public class InfoCoordinatePacket implements ServerPacket {

	private List<MovingPlayer> players = new ArrayList<>();

	@Override
	public void read(DofusStream stream) {
		while (stream.available() > 0)
			players.add(MovingPlayer.parse(stream.read()));
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(players.size());
		players.forEach(p -> stream.write(p.serialize()));
	}

	/**
	 * @return the players
	 */
	public List<MovingPlayer> getPlayers() {
		return players;
	}

	/**
	 * @param players
	 *            the players to set
	 */
	public void setPlayers(List<MovingPlayer> players) {
		this.players = players;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

	public static class MovingPlayer {
		private int x, y;
		private int mapId;
		private int type;
		private int playerId;
		private String playerName;

		public MovingPlayer(int x, int y, int mapId, int type, int playerId, String playerName) {
			this.x = x;
			this.y = y;
			this.mapId = mapId;
			this.type = type;
			this.playerId = playerId;
			this.playerName = playerName;
		}

		public static MovingPlayer parse(String data) {
			String[] datas = data.split(";");
			int x = Integer.parseInt(datas[0]);
			int y = Integer.parseInt(datas[1]);
			int mapid = Integer.parseInt(datas[2]);
			int type = Integer.parseInt(datas[3]);
			int playerid = Integer.parseInt(datas[4]);
			String pname = datas.length > 5 ? datas[5] : "";
			return new MovingPlayer(x, y, mapid, type, playerid, pname);
		}

		public String serialize() {
			return new StringJoiner(";").add(x).add(y).add(mapId).add(type).add(playerId).add(playerName).toString();
		}

		public Point getCoords() {
			return new Point(x, y);
		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @param x
		 *            the x to set
		 */
		public void setX(int x) {
			this.x = x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}

		/**
		 * @param y
		 *            the y to set
		 */
		public void setY(int y) {
			this.y = y;
		}

		/**
		 * @return the mapId
		 */
		public int getMapId() {
			return mapId;
		}

		/**
		 * @param mapId
		 *            the mapId to set
		 */
		public void setMapId(int mapId) {
			this.mapId = mapId;
		}

		/**
		 * @return the type
		 */
		public int getType() {
			return type;
		}

		/**
		 * @param type
		 *            the type to set
		 */
		public void setType(int type) {
			this.type = type;
		}

		/**
		 * @return the playerId
		 */
		public int getPlayerId() {
			return playerId;
		}

		/**
		 * @param playerId
		 *            the playerId to set
		 */
		public void setPlayerId(int playerId) {
			this.playerId = playerId;
		}

		/**
		 * @return the playerName
		 */
		public String getPlayerName() {
			return playerName;
		}

		/**
		 * @param playerName
		 *            the playerName to set
		 */
		public void setPlayerName(String playerName) {
			this.playerName = playerName;
		}

		@Override
		public String toString() {
			return "MovingPlayer [x=" + x + ", y=" + y + ", mapId=" + mapId + ", type=" + type + ", playerId=" + playerId + ", playerName=" + playerName + "]";
		}

	}

}
