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

import fr.aresrpg.dofus.protocol.game.actions.GameMoveAction.PathFragment;
import fr.aresrpg.dofus.structures.Orientation;
import fr.aresrpg.dofus.structures.item.Interractable;
import fr.aresrpg.dofus.structures.map.Cell;

import java.awt.Point;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

public class Pathfinding {

	public static List<Point> getCellPath(int xFrom, int yFrom, int xTo, int yTo, Cell[] cell, int width, int height, boolean useDiagonale) {
		return getPath(xFrom, yFrom, xTo, yTo, cell, width, height, false, useDiagonale, n -> true, (a, b) -> true);
	}

	public static List<Point> getCellPath(int xFrom, int yFrom, int xTo, int yTo, Cell[] cell, int width, int height, boolean useDiagonale, Predicate<Point> canTake) {
		return getPath(xFrom, yFrom, xTo, yTo, cell, width, height, false, useDiagonale, canTake, (a, b) -> true);
	}

	public static List<Point> getMapPath(int xFrom, int yFrom, int xTo, int yTo, BiPredicate<Point, Point> canMoveOnFrom) {
		return getPath(xFrom, yFrom, xTo, yTo, null, 0, 0, true, false, n -> true, canMoveOnFrom);
	}

	public static List<Point> getMapPath(int xFrom, int yFrom, int xTo, int yTo) {
		return getMapPath(xFrom, yFrom, xTo, yTo, (a, b) -> true);
	}

	public static List<Point> getPath(int xFrom, int yFrom, int xTo, int yTo, Cell[] cell, int width, int height, boolean isBigMap, boolean useDiagonale, Predicate<Point> canTake,
		BiPredicate<Point, Point> canMoveOnFrom) {
		PriorityQueue<Node> openList = new PriorityQueue<>();
		Set<Node> closedList = new HashSet<>();
		Map<Node, Node> cameFrom = new HashMap<>();
		Node from = new Node(xFrom, yFrom);
		cameFrom.put(from, null);
		openList.add(from);
		while (!openList.isEmpty()) {
			Node node = openList.poll();
			closedList.add(node);
			if (node.x == xTo && node.y == yTo)
				return recreatePath(cameFrom, node);
			else {
				Node[] nodes = useDiagonale ? getNeighbors(node) : getNeighborsWithoutDiagonals(node);
				Predicate<Node> closed = closedList::contains;
				Predicate<Node> tester = isBigMap ? n -> closed.test(n) || !canMoveOnFrom.test(new Point(node.x, node.y), new Point(n.x, n.y))
						: n -> closed.test(n) || !isValid(n, cell, width, height, n.x == xTo && n.y == yTo) || !canTake.test(new Point(n.x, n.y));
				for (Node n : nodes) {
					if (tester.test(n)) continue;
					n.cost = node.cost + (xTo - n.x) * (xTo - n.x) + (yTo - n.y) * (yTo - n.y);
					Node present = openList.stream().filter(u -> u.x == n.x && u.y == n.y).findFirst().orElse(null);
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

	public static float getPathTime(List<Point> path, Cell[] cells, int width, int height, boolean mount) {
		if (path == null) throw new NullPointerException("The path is null !");
		boolean walk = path.size() < 6;
		Point last = path.get(0);
		Cell c = cells[Maps.getId(last.x, last.y, width, height)];
		int lastGroundLevel = c.getGroundLevel();
		int lastGroundSlope = c.getGroundSlope();
		float time = 0f;
		for (int i = 1; i < path.size(); i++) {
			Point point = path.get(i);
			Cell cell = cells[Maps.getId(point.x, point.y, width, height)];
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
		nodes[0] = new Node(node.x + 1, node.y);
		nodes[1] = new Node(node.x - 1, node.y);
		nodes[2] = new Node(node.x, node.y + 1);
		nodes[3] = new Node(node.x, node.y - 1);

		nodes[4] = new Node(node.x + 1, node.y + 1);
		nodes[5] = new Node(node.x - 1, node.y - 1);
		nodes[6] = new Node(node.x - 1, node.y + 1);
		nodes[7] = new Node(node.x + 1, node.y - 1);
		return nodes;
	}

	public static Node[] getNeighborsWithoutDiagonals(Node node) {
		Node[] nodes = new Node[4];
		nodes[0] = new Node(node.x + 1, node.y);
		nodes[1] = new Node(node.x - 1, node.y);
		nodes[2] = new Node(node.x, node.y + 1);
		nodes[3] = new Node(node.x, node.y - 1);
		return nodes;
	}

	public static Orientation getDirection(int xFrom, int yFrom, int xTo, int yTo) {
		int deltaX = xTo - xFrom;
		int deltaY = yTo - yFrom;

		if (Math.abs(deltaX) == 1 && deltaY == 0)
			return deltaX > 0 ? Orientation.UP : Orientation.DOWN;
		else if (Math.abs(deltaY) == 1 && deltaX == 0)
			return deltaY > 0 ? Orientation.RIGHT : Orientation.LEFT;
		else if (Math.abs(deltaX) == 1 && deltaY == -1)
			return deltaX > 0 ? Orientation.UP_RIGHT : Orientation.DOWN_RIGHT;
		else if (Math.abs(deltaX) == 1 && deltaY == 1)
			return deltaX > 0 ? Orientation.UP_LEFT : Orientation.DOWN_LEFT;
		else
			return null;
	}

	public static Orientation getDirectionForMap(int xFrom, int yFrom, int xTo, int yTo) {
		if (xFrom < xTo) return Orientation.RIGHT;
		else if (xFrom > xTo) return Orientation.LEFT;
		if (yFrom < yTo) return Orientation.DOWN;
		else if (yFrom > yTo) return Orientation.UP;
		return null;
	}

	public static List<PathFragment> makeShortPath(List<Point> points, int width, int height) {
		if (points == null)
			return null;
		if (points.size() < 2)
			return null;
		List<PathFragment> path = new ArrayList<>();
		Orientation direction = getDirection(points.get(0).x, points.get(0).y,
				points.get(1).x, points.get(1).y);
		Point last = points.get(1);
		for (int i = 2; i < points.size(); i++) {
			Orientation current = getDirection(last.x, last.y,
					points.get(i).x, points.get(i).y);
			if (current != direction) {
				path.add(new PathFragment(Maps.getId(last.x, last.y, width, height), direction));
				direction = current;
			}
			last = points.get(i);
		}
		path.add(new PathFragment(Maps.getId(last.x, last.y, width, height), direction));
		return path;
	}

	private static boolean isValid(Node n, Cell[] cells, int width, int height, boolean last) {
		if (!Maps.isInMap(n.x, n.y, width, height)) return false;
		int id = Maps.getId(n.x, n.y, width, height);
		if (id >= 0 && id < cells.length) {
			Cell cell = cells[id];
			return last ? cell.getMovement() != 0 : isValidCell(cell);
		} else return false;
	}

	private static boolean isValidCell(Cell cell) {
		if (Interractable.isInterractable(cell.getLayerObject2Num())) return false;
		return cell.getMovement() == 4 || cell.getMovement() == 6;
	}

	private static List<Point> recreatePath(Map<Node, Node> cameFrom, Node node) {
		List<Point> p = new ArrayList<>();
		p.add(new Point(node.x, node.y));
		Node n = node;
		while ((n = cameFrom.get(n)) != null)
			p.add(new Point(n.x, n.y));
		Collections.reverse(p);
		return p;
	}

	public static class Node implements Comparable<Node> {
		int x;
		int y;
		int cost;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
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
