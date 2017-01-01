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

	private final int width = 7;
	private final int height = 10;
	private final int cellsW = 124;
	private final int range = 4;

	@Override
	public void start(Stage stage) throws Exception {
		DofusMapView v = new DofusMapView();
		stage.setTitle("Hello World");
		/*
		 * stage.setScene(new Scene(v));
		 * //[RCV:]<< GameMapDataPacket(mapId=1140, subid='0710011719', decryptKey='
		 * 203b3c4e4e25324244564a4f755a7b574965445b6e2532426429423c70552d493a5f26674d24235a5e6f2c204e4844634a70296642557552753a712e41282036612f586d7f68753e5f3379325d56752777284f6e545547715b627d666c263f5f465441445929247021536c7f5e606a4270412532426f4560237d6f49622d34695047346b42586550436f6a7e7563727d5b7f227f736c7c6451293b
		 * ')[GDM]
		 * DofusMap map = Maps.loadMap(
		 * SwfVariableExtractor.extractVariable(Maps.downloadMap(1140 , "0710011719")),
		 * "203b3c4e4e25324244564a4f755a7b574965445b6e2532426429423c70552d493a5f26674d24235a5e6f2c204e4844634a70296642557552753a712e41282036612f586d7f68753e5f3379325d56752777284f6e545547715b627d666c263f5f465441445929247021536c7f5e606a4270412532426f4560237d6f49622d34695047346b42586550436f6a7e7563727d5b7f227f736c7c6451293b"
		 * );
		 * v.setMap(map);
		 * stage.setResizable(true);
		 * stage.show();
		 * System.out.println(map.getHeight());
		 * //System.out.println(map.getWidth() + " " + map.getCells().length);
		 */
		Cell[] cells = new Cell[cellsW];
		for (int i = 0; i < cells.length; i++)
			cells[i] = new Cell(false, false);
		cells[50] = new Cell(true, false);
		cells[68] = new Cell(false, true);
		cells[69] = new Cell(false, true);
		Canvas canvas1 = new Canvas();
		Canvas canvas2 = new Canvas();
		canvas1.setWidth(800);
		canvas2.setWidth(800);
		canvas1.setHeight(800);
		canvas2.setHeight(800);
		drawCell(canvas1.getGraphicsContext2D(), canvas2.getGraphicsContext2D(), cells);
		drawRay(canvas1.getGraphicsContext2D());
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
			else
				gc.setFill(Color.LIGHTGRAY);
			double xp = Maps.getX(i, width, height) * 30 + 30;
			double yp = Maps.getY(i, width, height) * 30 + 30;
			if (i == 6) System.out.println(xp + "," + yp);
			gc.fillRect(xp, yp, 29, 29);
			/*
			 * gc.fillPolygon(new double[] { xp, xp + 20, xp, xp - 20 },
			 * new double[] { yp + 20, yp, yp - 20, yp }, 4);
			 */
			//gid.fillText(i + "", xp, yp + gc.getFont().getSize() / 3);
			gid.fillText(i + "", xp + 9, yp + 16);

		}
	}

	private void drawRay(GraphicsContext gc) {
		int y = Maps.getY(50, width, height);
		int x = Maps.getX(50, width, height);
		int r = range * 4 * 2;
		float inc = (float) ((2f * Math.PI) / (float) r);
		float a = 0;
		gc.setFill(Color.CORNFLOWERBLUE);
		for (int i = 0; i <= r; i++) {
			float ax = (float) Math.cos(a);
			float ay = (float) Math.sin(a);
			System.out.println(ay);
			for (int e = 1; e <= range; e++) {
				System.out.println(i + " " + y + " " + (y + ay * e));
				int id = Maps.getId((int) (x + ax * e), (int) Math.rint(y + ay * e), width, height);
				t(gc, id);
			}
			gc.setStroke(Color.BLUEVIOLET);
			gc.strokeLine(x * 30 + 45, y * 30 + 45,
					(x + ax * range * 2) * 30 + 30, y * 30 + ay * range * 2 * 30 + 30);
			a += inc;
		}
	}

	private void t(GraphicsContext gc, int id) {
		int y = Maps.getY(id, width, height);
		float x = Maps.getX(id, width, height);
		double xp = x * 30 + 32;
		double yp = y * 30 + 32;
		gc.fillRect(xp, yp, 25, 25);
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

	public class Cell {
		boolean player;
		boolean obstacle;

		public Cell(boolean player, boolean obstacle) {
			this.player = player;
			this.obstacle = obstacle;
		}
	}
}