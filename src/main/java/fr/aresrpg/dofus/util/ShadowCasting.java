package fr.aresrpg.dofus.util;

import fr.aresrpg.dofus.structures.map.Cell;
import fr.aresrpg.dofus.structures.map.DofusMap;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ShadowCasting {

	private ShadowCasting() {
	}

	public static List<Cell> getAccesibleCells(int originId, int range, DofusMap map, Predicate<Cell> obstacle) {
		List<Cell> accessible = new ArrayList<>();
		List<Cell> inaccessible = new ArrayList<>();
		Cell orig = map.getCells()[originId];
		for (int i = 0; i < map.getCells().length; i++) {
			Cell c = map.getCells()[i];
			if (Maps.distanceManathan(originId, i, map.getWidth(), map.getHeight()) <= range)
				accessible.add(c);
			if (obstacle.test(c))
				inaccessible.addAll(castShadow(c.getXRot(), c.getYRot(), orig.getXRot(), orig.getYRot(), range, map));
		}
		accessible.removeAll(inaccessible);
		return accessible;
	}

	private static List<Cell> castShadow(int px, int py, int ox, int oy, int range, DofusMap map) {
		int x = px - ox;
		int y = py - oy;

		int ax = x;
		if (ax < 0) ax = -ax;

		int ay = y;
		if (ay < 0) ay = -ay;

		int flipX = 0;
		if (x != 0) flipX = x / ax;

		int flipY = 0;
		if (y != 0) flipY = y / ay;

		double slope1 = getSlope((ax - 0.5), (ay + 0.5));
		double slope2 = getSlope((ax + 0.5), (ay - 0.5));

		boolean flag = true;

		List<Cell> inaccessible = new ArrayList<>();
		for (int cy = ay; cy < range; cy++)
			for (int cx = ax; cx < range; cx++)
				if ((cx != x || cy != y) && (cx * cx + cy * cy) <= range * range) {
					// Skip main cell
					double slope = getSlope(cx, cy);

					if ((slope > slope1) && (slope < slope2 || (slope2 < 0 && cx > x))) {
						if (!flag) {
							ax = cx;
							flag = true;
						}

						mirrorHide(cx, cy, ox, oy, flipX, flipY, inaccessible, map);
					} else
						if (flag)
							break;

					if (flag)
						flag = false;
					else
						break;

				}
		return inaccessible;
	}

	private static void mirrorHide(int cx, int cy, int ox, int oy, int flipX, int flipY, List<Cell> accessible, DofusMap map) {
		if (flipX >= 0)
			mirrorHide(cx, cy, ox, oy, flipY, accessible, map);
		if (flipX <= 0)
			mirrorHide(-cx, cy, ox, oy, flipY, accessible, map);
	}

	private static void mirrorHide(int cx, int cy, int ox, int oy, int flipY, List<Cell> inaccessible, DofusMap map) {
		if (flipY >= 0)
			inaccessible.add(shadow(cx + ox, cy + oy, map));
		if (flipY <= 0)
			inaccessible.add(shadow(cx + ox, -cy + oy, map));
	}

	private static Cell getCell(int x, int y, DofusMap map) {
		if (x > map.getHeight() + map.getWidth() || y > map.getHeight() + map.getWidth())
			return null;
		int id = Maps.getIdRotated(x, y, map.getWidth(), map.getHeight());
		if (Maps.isInMap(x, map.getWidth(), map.getHeight()) && id >= 0 && id < map.getCells().length)
			return map.getCells()[id];
		else
			return null;
	}

	private static Cell shadow(int x, int y, DofusMap map) {
		Cell cell = getCell(x, y, map);
		if (cell != null)
			return cell;
		else
			return null;
	}

	private static double getSlope(double a, double b) {
		if (b == 0) { return 99; }
		return a / b;
	}
}
