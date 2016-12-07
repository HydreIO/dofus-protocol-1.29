package fr.aresrpg.dofus.javafx;

import fr.aresrpg.dofus.structures.map.Cell;
import fr.aresrpg.dofus.structures.map.DofusMap;
import fr.aresrpg.dofus.util.Maps;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.awt.*;
import java.util.List;

public class DofusMapView extends Region {
	private ObjectProperty<DofusMap> map;
	private BooleanProperty full;
	private BooleanProperty cellId;
	private ObjectProperty<List<Point>> path;
	private Canvas cellCanvas;
	private Canvas idCanvas;
	private Canvas pathCanvas;

	public DofusMapView() {
		this.cellCanvas = new Canvas();
		this.idCanvas = new Canvas();
		this.pathCanvas = new Canvas();
		getChildren().add(cellCanvas);
		getChildren().add(idCanvas);
		getChildren().add(pathCanvas);
		this.map = new SimpleObjectProperty<>();
		this.full = new SimpleBooleanProperty(true);
		this.cellId = new SimpleBooleanProperty(true);
		this.path = new SimpleObjectProperty<>();
		widthProperty().addListener((obs , oldValue , newValue) -> draw());
		heightProperty().addListener((obs , oldValue , newValue) -> draw());
		this.map.addListener((obs , oldValue , newValue) -> drawCells());
		this.full.addListener((obs , oldValue , newValue) -> drawCells());
		this.path.addListener((obs , oldValue , newValue) -> drawPath());
		this.cellId.addListener((obs , oldValue , newValue) -> idCanvas.setVisible(newValue));
	}


	protected void drawCells() {
		double width = getWidth();
		double height = getHeight();
		DofusMap map = this.map.get();
		boolean full = this.full.get();
		if (map == null)
			return;
		int mWidth = map.getWidth();
		int mHeight = map.getHeight();
		double multiplier = Math.min(width / mWidth , height / mHeight);
		double dMultiplier = multiplier/2;

		height = mHeight * multiplier - (full ? 0 : multiplier);
		width = mWidth * multiplier - (full ? 0 : multiplier);
		setCanvasHeight(height);
		setCanvasWidth(width);
		Cell[] cells = map.getCells();

		GraphicsContext gc = cellCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, width , height); //Clear canvas

		GraphicsContext gid = idCanvas.getGraphicsContext2D();
		gid.clearRect(0, 0, width , height); //Clear canvas
		gid.setFill(Color.BLACK);
		gid.setTextAlign(TextAlignment.CENTER);

		for (int i = 0; i < cells.length; i++) {
			Cell c = cells[i];
			switch (c.getMovement()) {
				case 0:
					gc.setFill(Color.TRANSPARENT);
					break;
				case 2:
					gc.setFill(Color.BLUE);
					break;
				case 4:
					gc.setFill(Color.LIGHTGRAY);
					break;
				case 6:
					gc.setFill(Color.YELLOW);
					break;
				case 1:
					gc.setFill(Color.RED);
					break;
				case 3:
					gc.setFill(Color.GRAY);
					break;
				case 5:
					gc.setFill(Color.MAGENTA);
					break;
				default:
					throw new IllegalStateException("Unknown movement " + c.getMovement());
			}
			double xp = Maps.getColumn(i, mWidth) * dMultiplier
					+ (full ? dMultiplier : 0);
			double yp = Maps.getLine(i, mWidth) * dMultiplier
					+ (full ? dMultiplier : 0);
			gc.fillPolygon(new double[]{xp, xp + dMultiplier, xp, xp - dMultiplier},
					new double[]{yp + dMultiplier, yp, yp - dMultiplier, yp}, 4);
			gid.fillText(Integer.toString(i), xp, yp + gc.getFont().getSize() / 4);
		}

	}

	protected void drawPath() {
		double width = getWidth();
		double height = getHeight();
		DofusMap map = this.map.get();
		boolean full = this.full.get();
		List<Point> path = this.path.get();
		if(path == null || path.size() < 1)
			return;
		if (map == null)
			return;
		int mWidth = map.getWidth();
		int mHeight = map.getHeight();
		double multiplier = Math.min(width / mWidth , height / mHeight);
		double dMultiplier = multiplier/2;

		height = mHeight * multiplier - (full ? 0 : multiplier);
		width = mWidth * multiplier - (full ? 0 : multiplier);

		pathCanvas.setHeight(height);
		pathCanvas.setWidth(width);

		GraphicsContext g = pathCanvas.getGraphicsContext2D();
		g.clearRect(0, 0, width , height); //Clear canvas
		g.setStroke(Color.BLUEVIOLET);

		for(int i = 0 ; i < path.size() -1; i++) {
			g.strokeLine(path.get(i).x * dMultiplier + dMultiplier
					, path.get(i).y * dMultiplier + dMultiplier
					, path.get(i + 1).x * dMultiplier + dMultiplier
					, path.get(i + 1).y * dMultiplier + dMultiplier);
		}
	}

	private void draw() {
		drawCells();
		drawPath();
	}

	private void setCanvasWidth(double width) {
		cellCanvas.setWidth(width);
		idCanvas.setWidth(width);
	}

	private void setCanvasHeight(double height) {
		cellCanvas.setHeight(height);
		idCanvas.setHeight(height);
	}


	public DofusMap getMap() {
		return map.get();
	}

	public void setMap(DofusMap map) {
		this.map.set(map);
	}

	public ObjectProperty<DofusMap> mapProperty() {
		return map;
	}

	public List<Point> getPath() {
		return path.get();
	}

	public void setPath(List<Point> path) {
		this.path.set(path);
	}

	public ObjectProperty<List<Point>> pathProperty() {
		return path;
	}
}
