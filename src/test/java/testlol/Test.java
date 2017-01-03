package testlol;

import fr.aresrpg.dofus.util.DofusMapView;
import fr.aresrpg.dofus.util.Maps;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Test extends Application {

	private final int width = 15;
	private final int height = 17;
	private final int cellsW = 479;
	private final int range = 10;
	Cell[] cells = new Cell[cellsW];

	@Override
	public void start(Stage stage) throws Exception {
		DofusMapView v = new DofusMapView();
		stage.setTitle("Hello World");
		/* stage.setScene(new Scene(v));
		 //GDM|7554|0905131019|50504d4065244a7a6c345c75562c5a3068215e395c487e75612532422d3330543730787e402a4a3776774657233a3e79303f3b3074742e227121657831584a253242406f786e6e226b5d6569236f30605c652252452f465524253235674e3a7d2e6c683a722933796b396c3c74332671347c2067753b40547a775c3d6f233e533241285c4050717e77482d4b7a3628362e433141682d655a694d283b4d6d4544532e7c305c76483767725e48
		DofusMap map = Maps.loadMap(
		 SwfVariableExtractor.extractVariable(Maps.downloadMap(7554 , "0905131019")),
		 "50504d4065244a7a6c345c75562c5a3068215e395c487e75612532422d3330543730787e402a4a3776774657233a3e79303f3b3074742e227121657831584a253242406f786e6e226b5d6569236f30605c652252452f465524253235674e3a7d2e6c683a722933796b396c3c74332671347c2067753b40547a775c3d6f233e533241285c4050717e77482d4b7a3628362e433141682d655a694d283b4d6d4544532e7c305c76483767725e48"
		 );
		 v.setMap(map);
		 stage.setResizable(true);
		 stage.show();
		 System.out.println("Map " + map.getHeight() + " " + map.getWidth() + " " + map.getCells().length);
		*/
		for (int i = 0; i < cells.length; i++){
			cells[i] = new Cell(false, false);
			if(Maps.distance(i , 50 , width , height) > range*range)
				cells[i].accesible = false;
		}
		cells[50] = new Cell(true, false);
		cells[93] = new Cell(false, true);
		cells[94] = new Cell(false, true);
		int ox = Maps.getXRotated(50, width , height);
		int oy = Maps.getYRotated(50 , width , height);
		castShadow(Maps.getXRotated(93, width , height) , Maps.getYRotated(93 , width , height) , ox , oy);
		castShadow(Maps.getXRotated(94, width , height) , Maps.getYRotated(94 , width , height) , ox , oy);
		Canvas canvas1 = new Canvas();
		Canvas canvas2 = new Canvas();
		canvas1.setWidth(1200);
		canvas2.setWidth(1200);
		canvas1.setHeight(1200);
		canvas2.setHeight(1200);
		drawCell(canvas1.getGraphicsContext2D(), canvas2.getGraphicsContext2D(), cells);
		//drawRay(canvas1.getGraphicsContext2D());
		Group r = new Group();
		r.getChildren().add(canvas1);
		r.getChildren().add(canvas2);
		stage.setScene(new Scene(r));
		stage.show();
	}

	private void drawCell(GraphicsContext gc, GraphicsContext gid, Cell[] cells) {
		for (int i = 0; i < cells.length; i++) {
			Cell c = cells[i];
			if (c.obstacle)
				gc.setFill(Color.BLUEVIOLET);
			else if (c.player)
				gc.setFill(Color.AQUA);
			else if(c.accesible)
				gc.setFill(Color.GREEN);
			else
				gc.setFill(Color.LIGHTGRAY);
			int xe = Maps.getXRotated(i , width , height);
			int ye = Maps.getYRotated(i , width , height);

			System.out.println(xe + " " + ye);
			System.out.println(i + " " + Maps.getIdRotated(xe , ye , width , height));
			double xp = xe * 30;
			double yp = ye * 30;
			gc.fillRect(xp, yp, 29, 29);
			/*
			 * gc.fillPolygon(new double[] { xp, xp + 20, xp, xp - 20 },
			 * new double[] { yp + 20, yp, yp - 20, yp }, 4);
			 */
			//gid.fillText(i + "", xp, yp + gc.getFont().getSize() / 3);
			gid.fillText(i + "", xp, yp + 16);

		}
	}

	void castShadow(int px, int py , int ox , int oy) {
		int x = px - ox;
		int y = py - oy;

		/** Absolute Values **/
		int ax = x;
		if(ax < 0) ax = -ax;

		int ay = y;
		if(ay < 0) ay = -ay;

		int flipX = 0;
		if (x != 0) flipX = x / ax;

		int flipY = 0;
		if (y != 0) flipY = y / ay;

		/** Min Max Slopes **/
		double slope1 = getSlope((ax - 0.5), (ay + 0.5));
		double slope2 = getSlope((ax + 0.5), (ay - 0.5));

		System.out.println("Slopes : " + slope1 + " " + slope2);

		boolean flag = true;

		for (int cy = ay; cy < range; cy++) {
			for (int cx = ax; cx < range; cx++) {
				if ((cx != x || cy != y) && (cx * cx + cy * cy) <= range*range) {
					/** Skip main cell */
					double slope = getSlope(cx, cy);

					if ((slope > slope1) && (slope < slope2 || (slope2 < 0 && cx > x))) {
						if (!flag) {
							ax = cx;
							flag = true;
						}

						mirrorHide(cx, cy, ox , oy, flipX, flipY);
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

	void mirrorHide(int cx, int cy, int ox , int oy, int flipX, int flipY) {
		if (flipX >= 0) {
			mirrorHide(cx, cy, ox , oy , flipY);
		}
		if (flipX <= 0) {
			mirrorHide(-cx, cy, ox , oy , flipY);
		}
	}

	void mirrorHide(int cx, int cy, int ox , int oy, int flipY) {
		if (flipY >= 0) {
			shadow(cx + ox, cy + oy);
		}
		if (flipY <= 0) {
			shadow(cx + ox ,-cy + oy);
		}
	}

	Cell getCell(int x, int y) {
		if(x > height + width || y > height + width)
			return null;
		int id = Maps.getIdRotated(x , y , width , height);
		if(Maps.isInMap(id , width , height))
			return cells[id];
		else
			return null;
	}

	void shadow(int x, int y) {
		Cell cell = getCell(x, y);
		if(cell != null)
			cell.accesible = false;
	}

	double getSlope(double a, double b) {
		if (b == 0) { return 99; }
		return a / b;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public class Cell {
		boolean player;
		boolean obstacle;
		boolean accesible = true;

		public Cell(boolean player, boolean obstacle) {
			this.player = player;
			this.obstacle = obstacle;
		}
	}
}