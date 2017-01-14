/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.util;

import fr.aresrpg.dofus.Constants;
import fr.aresrpg.dofus.protocol.game.actions.GameMoveAction.PathFragment;
import fr.aresrpg.dofus.structures.Orientation;
import fr.aresrpg.dofus.structures.item.Interractable;
import fr.aresrpg.dofus.structures.map.Cell;

import java.util.*;
import java.util.function.Function;

public class Pathfinding {

	public static <T extends Node> List<T> getCellPath(int idFrom, int idTo, Cell[] cells, int width, int height, Function<T, T[]> neighbors, PathValidator canTake) {
		int xFrom = cells[idFrom].getXRot() == -1 ? Maps.getXRotated(idFrom, width, height) : cells[idFrom].getXRot();
		int yFrom = cells[idFrom].getYRot() == -1 ? Maps.getYRotated(idFrom, width, height) : cells[idFrom].getYRot();
		int xTo = cells[idTo].getXRot() == -1 ? Maps.getXRotated(idTo, width, height) : cells[idTo].getXRot();
		int yTo = cells[idTo].getYRot() == -1 ? Maps.getYRotated(idTo, width, height) : cells[idTo].getYRot();
		Node node = new Node(xFrom, yFrom);
		return getPath((T) node, xTo, yTo, nn -> nn.equals(new Node(xTo, yTo)), (xF, yF, x, y) -> canTake.test(xF, yF, x, y) && isValid(x, y, cells, width, height, x == xTo && y == yTo), neighbors);
	}

	public static <T extends Node> List<T> getPath(T origin, int xto, int yto, PathEndValidator<T> endValidator, Function<T, T[]> neighbors) {
		return getPath(origin, xto, yto, endValidator, (xF, yF, xT, yT) -> true, neighbors);
	}

	public static <T extends Node> List<T> getPath(T from, int xto, int yto, PathEndValidator<T> endValidator, PathValidator validator, Function<T, T[]> neighbors) {
		PriorityQueue<T> openList = new PriorityQueue<>();
		Set<T> closedList = new HashSet<>();
		Map<T, T> cameFrom = new HashMap<>();
		cameFrom.put(from, null);
		openList.add(from);
		while (!openList.isEmpty()) {
			T node = openList.poll();
			closedList.add(node);
			if (endValidator.test(node))
				return recreatePath(cameFrom, node);
			else {
				T[] apply = neighbors.apply(node);
				for (T n : apply) {
					if (closedList.contains(n) || !validator.test(node.x, node.y, n.x, n.y))
						continue;
					n.cost = node.cost + (xto - n.x) * (xto - n.x) + (yto - n.y) * (yto - n.y);
					T present = openList.stream().filter(u -> u.equals(n)).findFirst().orElse(null);
					if (openList.contains(n)) {
						openList.remove(n);
					}
					if (present != null) {
						if (present.cost < n.cost)
							continue;
						else
							openList.remove(present);
					}
					openList.add(n);
					cameFrom.put(n, node);
				}

			}
		}
		return null;

	}

	@FunctionalInterface
	public interface PathValidator {
		boolean test(int xFrom, int yFrom, int xTo, int yTo);

		static PathValidator alwaysTrue() {
			return (a, b, c, d) -> true;
		}
	}

	@FunctionalInterface
	public interface PathEndValidator<T extends Node> {
		boolean test(T t);
	}

	public static float getPathTime(List<Node> path, Cell[] cells, int width, int height, boolean mount) {
		if (path == null) throw new NullPointerException("The path is null !");
		boolean walk = path.size() < 6;
		Node last = path.get(0);
		System.out.println("last = " + last);
		Cell c = cells[Maps.getIdRotated(last.x, last.y, width, height)];
		int lastGroundLevel = c.getGroundLevel();
		int lastGroundSlope = c.getGroundSlope();
		float time = 0f;
		for (int i = 1; i < path.size(); i++) {
			Node point = path.get(i);
			Cell cell = cells[Maps.getIdRotated(point.x, point.y, width, height)];
			Orientation direction = getDirection(last.x, last.y, point.x, point.y);
			time += 1 / (mount ? direction.getMountSpeed() : walk ? direction.getWalkSpeed() : direction.getRunSpeed());
			if (lastGroundLevel < cell.getGroundLevel())
				time += 100;
			else if (cell.getGroundLevel() > lastGroundLevel)
				time -= 100;
			else if (lastGroundSlope != cell.getGroundSlope()) {
				if (lastGroundSlope == 1)
					time += 100;
				else if (cell.getGroundSlope() == 1)
					time -= 100;
			}
			lastGroundLevel = cell.getGroundLevel();
			lastGroundSlope = cell.getGroundSlope();
			last = point;
		}
		return time;
	}

	public static Node[] getNeighbors(Node node) {
		Node[] nodes = new Node[8];
		int i = 0;
		for (int x = -1; x <= 1; x++)
			for (int y = -1; y <= 1; y++)
				if (!(x == 0 && y == 0))
					nodes[i++] = new Node(node.x + x, node.y + y);

		return nodes;
	}

	public static Node[] getNeighborsWithoutDiagonals(Node node) {
		return new Node[] {
				new Node(node.x + 1, node.y),
				new Node(node.x - 1, node.y),
				new Node(node.x, node.y + 1),
				new Node(node.x, node.y - 1)
		};
	}

	public static Orientation getDirection(int xFrom, int yFrom, int xTo, int yTo) {
		int deltaX = xTo - xFrom;
		int deltaY = yTo - yFrom;

		if (Math.abs(deltaX) == 1 && deltaY == 0)
			return deltaX > 0 ? Orientation.UP_RIGHT : Orientation.DOWN_LEFT;
		else if (Math.abs(deltaY) == 1 && deltaX == 0)
			return deltaY > 0 ? Orientation.DOWN_RIGHT : Orientation.UP_LEFT;
		else if (Math.abs(deltaX) == 1 && deltaY == -1)
			return deltaX > 0 ? Orientation.UP : Orientation.LEFT;
		else if (Math.abs(deltaX) == 1 && deltaY == 1)
			return deltaX > 0 ? Orientation.RIGHT : Orientation.DOWN;
		else
			return null;
	}

	public static Orientation getDirectionForMap(int xFrom, int yFrom, int xTo, int yTo) {
		int deltaX = xTo - xFrom;
		int deltaY = yTo - yFrom;

		if (Math.abs(deltaX) == 1 && deltaY == 0)
			return deltaX > 0 ? Orientation.RIGHT : Orientation.LEFT;
		else if (Math.abs(deltaY) == 1 && deltaX == 0)
			return deltaY > 0 ? Orientation.DOWN : Orientation.UP;
		else if (Math.abs(deltaX) == 1 && deltaY == -1)
			return deltaX > 0 ? Orientation.UP_RIGHT : Orientation.UP_LEFT;
		else if (Math.abs(deltaX) == 1 && deltaY == 1)
			return deltaX > 0 ? Orientation.DOWN_RIGHT : Orientation.DOWN_LEFT;
		else
			return null;
	}

	public static List<PathFragment> makeShortPath(List<Node> points, int width, int height) {
		if (points == null)
			return null;
		if (points.size() < 2)
			return null;
		List<PathFragment> path = new ArrayList<>();
		Orientation direction = getDirection(points.get(0).x, points.get(0).y,
				points.get(1).x, points.get(1).y);
		Node last = points.get(1);
		for (int i = 2; i < points.size(); i++) {
			Orientation current = getDirection(last.x, last.y,
					points.get(i).x, points.get(i).y);
			if (current != direction) {
				path.add(new PathFragment(Maps.getIdRotated(last.x, last.y, width, height), direction));
				direction = current;
			}
			last = points.get(i);
		}
		path.add(new PathFragment(Maps.getIdRotated(last.x, last.y, width, height), direction));
		return path;
	}

	private static boolean isValid(int x, int y, Cell[] cells, int width, int height, boolean last) {
		if (!Maps.isInMapRotated(x, y, width, height))
			return false;
		int id = Maps.getIdRotated(x, y, width, height);
		if (id >= 0 && id < cells.length) {
			Cell cell = cells[id];
			boolean interrvalid = Interractable.isZaap(cell.getInterractableId()) || Interractable.isZaapi(cell.getInterractableId());
			return last ? cell.getMovement() != 0 && (interrvalid || cell.getMovement() != 1) : isValidCell(cell);
		} else return false;
	}

	private static boolean isValidCell(Cell cell) {
		for (int i : Constants.TELEPORT_TEXTURES)
			if (cell.getLayerObject1Num() == i || cell.getLayerObject2Num() == i) return false;
		return cell.getMovement() != 1 && !Interractable.isInterractable(cell.getLayerObject2Num()) && cell.getMovement() != 0;
	}

	private static <T extends Node> List<T> recreatePath(Map<T, T> cameFrom, T node) {
		List<T> p = new ArrayList<>();
		p.add(node);
		T n = node;
		while ((n = cameFrom.get(n)) != null)
			p.add(n);
		Collections.reverse(p);
		return p;
	}

	public static class Node implements Comparable<Node> {
		int x;
		int y;
		protected int cost;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		/**
		 * @param cost
		 *            the cost to set
		 */
		public void setCost(int cost) {
			this.cost = cost;
		}

		/**
		 * @return the x
		 */
		public int getX() {
			return x;
		}

		/**
		 * @return the y
		 */
		public int getY() {
			return y;
		}

		/**
		 * @return the cost
		 */
		public int getCost() {
			return cost;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Node node = (Node) o;
			return x == node.x &&
					y == node.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		@Override
		public int compareTo(Node node) {
			return Integer.compare(cost, node.cost);
		}

		public int distanceManathan(int x, int y) {
			return Math.abs(x - this.x) + Math.abs(y - this.y);
		}

		@Override
		public String toString() {
			return "Node{" +
					"x=" + x +
					", y=" + y +
					", cost=" + cost +
					'}';
		}
	}
}
