package fr.aresrpg.dofus.util;

import fr.aresrpg.dofus.structures.PathDirection;
import fr.aresrpg.dofus.structures.map.Cell;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Pathfinding {

	public static List<Point> getPath(int xFrom, int yFrom, int xTo, int yTo, Cell[] cell, int width) {
		PriorityQueue<Node> openList = new PriorityQueue<>();
		Set<Node> closedList = new HashSet<>();
		Map<Node , Node> cameFrom = new HashMap<>();
		Node from = new Node(xFrom , yFrom);
		cameFrom.put(from , null);
		openList.add(from);
		while(!openList.isEmpty()) {
			Node node = openList.poll();
			closedList.add(node);
			if(node.x == xTo && node.y == yTo)
				return recreatePath(cameFrom , node);
			else
				for(Node n : getNeighbors(node)) {
					if(!isValid(n , cell, width))
						continue;
					if(closedList.contains(n))
						continue;
					n.cost = node.cost + (xTo - n.x)*(xTo - n.x) + (yTo - n.y)*(yTo - n.y);
					Node present = openList.stream().filter(u -> u.x == n.x && u.y == n.y).findFirst().orElse(null);
					if(openList.contains(n)) {
						openList.remove(n);
					}
					if(present != null){
						if(present.cost < n.cost)
							continue;
						else
							openList.remove(present);
					}
					openList.add(n);
					cameFrom.put(n , node);
				}
		}
		return null;
	}


	private static Node[] getNeighbors(Node node) {
		Node[] nodes = new Node[8];
		int i = 0;
		for(int x = -1 ; x <= 1 ; x++)
			for(int y = -1 ; y <= 1 ; y++)
				if (!(x == 0 && y == 0)){
					if(x == 0 || y == 0)
						nodes[i++] = new Node(node.x + (x * 2) , node.y + (y * 2));
					else
						nodes[i++] = new Node(node.x + x , node.y + y);
				}

		return nodes;
	}

	public static PathDirection getDirection(int xFrom , int yFrom , int xTo , int yTo){
		int deltaX = xTo - xFrom;
		int deltaY = yTo - yFrom;

		if(Math.abs(deltaX) == 2 && deltaY == 0)
			return deltaX > 0 ? PathDirection.DOWN_RIGHT : PathDirection.UP_LEFT;
		else if(Math.abs(deltaY) == 2 && deltaX == 0)
			return deltaY > 0 ? PathDirection.DOWN_LEFT : PathDirection.UP_RIGHT;
		else if(Math.abs(deltaX) == 1 && deltaY == -1)
			return deltaX > 0 ? PathDirection.RIGHT : PathDirection.UP;
		else if(Math.abs(deltaX) == 1 && deltaY == 1)
			return deltaX > 0 ? PathDirection.DOWN : PathDirection.LEFT;
		else
			return null;
	}

	public static Map<Integer , PathDirection> makeShortPath(List<Point> points , int width){
		if(points == null)
			return null;
		if(points.size() < 2)
			return null;

		Map<Integer, PathDirection> map = new LinkedHashMap<>();
		PathDirection direction = getDirection(points.get(0).x , points.get(0).y ,
				points.get(1).x , points.get(1).y);
		Point last = points.get(1);
		for(int i = 2 ; i < points.size() ; i++){
			PathDirection current = getDirection(last.x , last.y ,
					points.get(i).x , points.get(i).y);
			if(current != direction) {
				map.put(Maps.getId(last.x , last.y , width) , direction);
				direction = current;
			}
			last = points.get(i);
		}

		map.put(Maps.getId(last.x , last.y , width) , direction);
		return map;
	}

	private static boolean isValid(Node n , Cell[] cells , int width){
		int id = Maps.getId(n.x , n.y , width);
		if(id >= 0 && id < cells.length) {
			Cell cell = cells[id];
			return cell.getMovement() != 0;
		} else
			return false;
	}

	private static List<Point> recreatePath(Map<Node, Node> cameFrom, Node node) {
		List<Point> p = new ArrayList<>();
		p.add(new Point(node.x , node.y));
		Node n = node;
		while((n = cameFrom.get(n)) != null)
			p.add(new Point(n.x , n.y));
		Collections.reverse(p);
		return p;
	}

	public static class Node implements Comparable<Node>{
		int x;
		int y;
		int cost;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
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
			return Integer.compare(cost , node.cost);
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
