package testlol;

import fr.aresrpg.dofus.structures.map.DofusMap;
import fr.aresrpg.dofus.util.DofusMapView;
import fr.aresrpg.dofus.util.Maps;
import fr.aresrpg.dofus.util.SwfVariableExtractor;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class Test extends Application {

	private final int width = 15;
	private final int height = 17;
	private final int cellsW = 479;
	private final int range = 4;

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
		*/Cell[] cells = new Cell[cellsW];
		for (int i = 0; i < cells.length; i++)
			cells[i] = new Cell(false, false);
		cells[50] = new Cell(true, false);
		cells[68] = new Cell(false, true);
		cells[69] = new Cell(false, true);
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

	private void drawRay(GraphicsContext gc) {
		Point p = /*Maps.getXY(50 , width , height);*/null;
		int y = p.y;
		int x = p.x;
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
				int id = Maps.getIdRotated((int) (x + ax * e), (int) Math.rint(y + ay * e), width, height);
				t(gc, id);
			}
			gc.setStroke(Color.BLUEVIOLET);
			gc.strokeLine(x * 30 + 45, y * 30 + 45,
					(x + ax * range * 2) * 30 + 30, y * 30 + ay * range * 2 * 30 + 30);
			a += inc;
		}
	}

	private void t(GraphicsContext gc, int id) {
		int y = Maps.getYRotated(id, width, height);
		float x = Maps.getXRotated(id, width, height);
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