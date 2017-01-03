package testlol;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test2 extends Application {

	private static final int range = 10;
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
		grid[9][9].player = true;
		grid[8][10].obstacle = true;
		grid[10][10].obstacle = true;
		grid[9][11].obstacle = true;
		Canvas c = new Canvas(600, 600);
		GraphicsContext gc = c.getGraphicsContext2D();
		calculateShadow(grid , 9 , 9);
		for (int x = 0; x < grid.length; x++)
			for (int y = 0; y < grid[x].length; y++) {
				if (grid[x][y].obstacle)
					gc.setFill(Color.AQUA);
				else if (grid[x][y].player)
					gc.setFill(Color.RED);
				else if (grid[x][y].accesible)
					gc.setFill(Color.GREEN);
				else
					gc.setFill(Color.TRANSPARENT);
				gc.fillRect(x * 30, y * 30, 30, 30);
				gc.strokeRect(x * 30, y * 30, 30, 30);
			}
		r.getChildren().add(c);
		stage.setScene(new Scene(r));
		stage.show();
	}


	void calculateShadow(Cell[][] map, int ox,int oy) {
		for (int x = 0; x < map.length; x++)
			for (int y = 0; y < map.length; y++)
				if(map[x][y].obstacle)
					castShadow(x, y, ox, oy, true);
	}


	public static void main(String[] args) {
		Application.launch(args);
	}

	public class Cell {
		boolean player = false;
		boolean obstacle = false;
		boolean accesible = true;
	}

	void castShadow(int x, int y, int ox , int oy, boolean mod) {
		x -= ox;
		y -= oy;

		/** Scan with Quadrants **/

		/** Absolute Values **/
		int ax = x;
		if(ax < 0)
			ax = -ax;

		int ay = y;
		if(ay < 0)
			ay = -ay;

		int flipX = 0;
		if (x != 0) {
			flipX = x / ax;
		}

		int flipY = 0;
		if (y != 0) {
			flipY = y / ay;
		}

		/** Min Max Slopes **/
		double slope1 = getSlope((ax - 0.5), (ay + 0.5));
		double slope2 = getSlope((ax + 0.5), (ay - 0.5));

		System.out.println("Slopes : " + slope1 + " " + slope2);

		boolean flag = true;

		for (int cy = ay; cy < size/2; cy++) {
			for (int cx = ax; cx < size/2; cx++) {
				if (cx != x || cy != y) {
					/** Skip main cell */
					double slope = getSlope(cx, cy);

					if ((slope > slope1) && (slope < slope2 || (slope2 < 0 && cx > x))) {
						if (!flag) {
							ax = cx;
							flag = true;
						}

						mirrorHide(cx, cy, ox , oy, flipX, flipY, mod);
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

	void mirrorHide(double cx, int cy, int ox , int oy, int flipX, int flipY, boolean mod) {
		if (flipX >= 0) {
			mirrorHide(cx, cy,ox , oy, flipY, mod);
		}
		if (flipX <= 0) {
			mirrorHide(-cx, cy,ox , oy, flipY, mod);
		}
	}

	void mirrorHide(double cx, int cy, int ox, int oy, int flipY, boolean mod) {
		if (flipY >= 0) {
			shadow(cx + ox, cy + oy, mod);
		}
		if (flipY <= 0) {
			shadow(cx + ox,-cy + oy, mod);
		}
	}

	Cell getCell(int x, int y) {
		return grid[x][y];
	}

	void shadow(double cx, int y, boolean mod) {
		Cell cell = getCell((int) cx, y);
		cell.accesible = !mod;
	}

	double getSlope(double a, double b) {
		if (b == 0) { return 99; }
		return a / b;
	}

}