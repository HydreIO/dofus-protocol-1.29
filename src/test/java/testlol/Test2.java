package testlol;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test2 extends Application {

	private static final int range = 6;
	static int size = 20;
	Cell[][] grid = new Cell[size][size];

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Hello World");
		stage.show();
		Group r = new Group();
		for (int x = 0; x < grid.length; x++)
			for (int y = 0; y < grid[x].length; y++)
				grid[x][y] = new Cell();
		grid[8][11].obstacle = true;
		Canvas c = new Canvas(600, 600);
		GraphicsContext gc = c.getGraphicsContext2D();
		calculateFOV(grid, 9, 9, range);
		for (int x = 0; x < grid.length; x++)
			for (int y = 0; y < grid[x].length; y++) {
				if (grid[x][y].obstacle)
					gc.setFill(Color.AQUA);
				else if (grid[x][y].player)
					gc.setFill(Color.RED);
				else if (grid[x][y].accesible < 1)
					gc.setFill(Color.GREEN);
				else
					gc.setFill(Color.TRANSPARENT);
				gc.fillRect(x * 30, y * 30, 30, 30);
				gc.strokeRect(x * 30, y * 30, 30, 30);
			}

		//draw(gc, grid);
		r.getChildren().add(c);
		stage.setScene(new Scene(r));
		stage.show();
	}

	private void draw(GraphicsContext gc, Cell[][] grid) {
		int y = 9;
		int x = 9;
		int r = range * 4 * 2;
		float inc = (float) ((2f * Math.PI) / (float) r);
		float a = 0;
		gc.setFill(Color.RED);
		for (int i = 0; i <= r; i++) {
			float ax = (float) Math.cos(a);
			float ay = (float) Math.sin(a);
			System.out.println(ay);
			for (int e = 1; e <= range; e++) {
				int ex = (int) Math.round(x + ax * e);
				int ey = (int) Math.round(y + ay * e);
				System.out.println((x + ax * e) + " " + (y + ay * e));

				if (grid[ex][ey].obstacle)
					break;

				System.out.println(i + " " + ex + " " + ey);

				gc.fillOval(ex * 30 + 7.5, ey * 30 + 7.5, 15, 15);
				gc.strokeLine(x * 30 + 15, y * 30 + 15,
						ex * 30 + 15, ey * 30 + 15);

			}
			gc.setStroke(Color.BLUEVIOLET);
			a += inc;
		}
	}

	/**
	 * Calculates the Field Of View for the provided map from the given x, y
	 * coordinates. Returns a lightmap for a result where the values represent a
	 * percentage of fully lit.
	 *
	 * A value equal to or below 0 means that cell is not in the
	 * field of view, whereas a value equal to or above 1 means that cell is
	 * in the field of view.
	 *
	 * @param resistanceMap
	 *            the grid of cells to calculate on where 0 is transparent and 1 is opaque
	 * @param startx
	 *            the horizontal component of the starting location
	 * @param starty
	 *            the vertical component of the starting location
	 * @param radius
	 *            the maximum distance to draw the FOV
	 * @param radiusStrategy
	 *            provides a means to calculate the radius as desired
	 * @return the computed light grid
	 */
	public void calculateFOV(Cell[][] map, int startx, int starty, float radius) {
		for (int x = -1; x <= 1; x += 2) {
			for (int y = -1; y <= 1; y += 2) {
				castLight(map, startx, starty, radius, 1, 1.0f, 0.0f, 0, x, y, 0);
				castLight(map, startx, starty, radius, 1, 1.0f, 0.0f, x, 0, 0, y);
			}
		}
	}

	void calculateShadow(Cell[][] map) {
		for (int x = 0; x < map.length; x++)
			for (int y = 0; y < map.length; y++)
				castShadow(x, y, map[x][y].obstacle ? 1 : 0);
	}

	private void castLight(Cell[][] map, int startx, int starty, float radius, int row, float start, float end, int xx, int xy, int yx, int yy) {
		float newStart = 0.0f;
		if (start < end) { return; }
		boolean blocked = false;
		for (int distance = row; distance <= radius && !blocked; distance++) {
			int deltaY = -distance;
			for (int deltaX = -distance; deltaX <= 0; deltaX++) {
				int currentX = startx + deltaX * xx + deltaY * xy;
				int currentY = starty + deltaX * yx + deltaY * yy;
				float leftSlope = (deltaX - 0.5f) / (deltaY + 0.5f);
				float rightSlope = (deltaX + 0.5f) / (deltaY - 0.5f);

				if (!(currentX >= 0 && currentY >= 0 && currentX < map.length && currentY < map[0].length) || start < rightSlope) {
					continue;
				} else if (end > leftSlope) {
					break;
				}

				map[currentX][currentY].accesible = 1;

				if (blocked) { //previous cell was a blocking one
					if (map[currentX][currentY].obstacle) {//hit a wall

						newStart = rightSlope;
					} else {
						blocked = false;
						start = newStart;
					}
				} else {
					if (map[currentX][currentY].obstacle && distance < radius) {//hit a wall within sight line
						blocked = true;
						castLight(map, startx, starty, radius, distance + 1, start, leftSlope, xx, xy, yx, yy);
						newStart = rightSlope;
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public class Cell {
		boolean player = false;
		boolean obstacle = false;
		int accesible;
	}

	boolean debug = false;

	void setObstacle(int x, int y) {
		grid[x][y].obstacle = true;
	}

	void unsetObstacle(int x, int y) {
		grid[x][y].obstacle = false;
	}

	void castShadow(int x, int y, int mod) {

		if (mod > 0) setObstacle(x, y);
		else unsetObstacle(x, y);

		/** Scan with Quadrants **/

		/** Absolute Values **/
		double ax = Math.abs(x);
		int flipX = 0;
		if (x != 0) {
			flipX = x / Math.abs(x);
		}

		/** Min Max Slopes **/
		double slope1 = getSlope((ax - 0.5), (y + 0.5));
		double slope2 = getSlope((ax + 0.5), (y - 0.5));

		System.out.println("Slopes : " + slope1 + " " + slope2);

		boolean flag = true;
		double firstX = ax;

		for (int cy = y; cy <= size; cy++) {
			for (double cx = firstX; cx <= size; cx++) {
				grid[cy][cx] = new Cell();
			}
		}

		for (int cy = y; cy <= size; cy++) {
			for (double cx = firstX; cx <= size; cx++) {
				if (cx != x || cy != y) { /** Skip main cell */
					double slope = getSlope(cx, cy);

					if ((slope > slope1) && (slope < slope2 || (slope2 < 0 && cx > x))) {
						if (!flag) {
							firstX = cx;
							flag = true;
						}

						mirrorHide(cx, cy, flipX, mod);
					} else {
						if (flag) {
							break;
						}
					}

				}
			}
			if (flag) {
				flag = false;
			} else {
				break;
			}

		}
	}

	void mirrorHide(double cx, int cy, int flipX, int mod) {
		if (flipX >= 0) {
			shadow(cx, cy, mod);
		}
		if (flipX <= 0) {
			shadow(-cx, cy, mod);
		}
	}

	Cell getCell(int x, int y) {
		return grid[x][y];
	}

	void shadow(double cx, int y, int mod) {
		Cell cell = getCell((int) cx, y);
		cell.accesible += mod;
	}

	double getSlope(double a, double b) {
		if (b == 0) { return 99; }
		return a / b;
	}

}