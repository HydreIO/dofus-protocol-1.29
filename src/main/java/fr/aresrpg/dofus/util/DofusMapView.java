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
import fr.aresrpg.dofus.structures.map.Cell;
import fr.aresrpg.dofus.structures.map.DofusMap;
import fr.aresrpg.dofus.util.Pathfinding.Node;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;

import javafx.beans.property.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class DofusMapView extends Region {

	private ObjectProperty<DofusMap> map;
	private BooleanProperty full;
	private BooleanProperty cellId;
	private ObjectProperty<List<Node>> path;
	private ObjectProperty<List<Cell>> accessible;
	private ObjectProperty<List<Cell>> highlight;
	private IntegerProperty shadowOrigin;
	private IntegerProperty shadowRange;
	private IntegerProperty currentPosition;
	private ConcurrentMap<Integer, Integer> mobs = new ConcurrentHashMap();
	private ConcurrentMap<Integer, Integer> players = new ConcurrentHashMap<>();
	private ConcurrentMap<Integer, Integer> npcs = new ConcurrentHashMap<>();
	private Canvas cellCanvas;
	private Canvas idCanvas;
	private Canvas pathCanvas;
	private IntConsumer onCellClick;
	private Color pathColor;

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
		this.accessible = new SimpleObjectProperty<>();
		this.highlight = new SimpleObjectProperty<>();
		this.currentPosition = new SimpleIntegerProperty(-1);
		this.shadowRange = new SimpleIntegerProperty(-1);
		this.shadowOrigin = new SimpleIntegerProperty(-1);
		widthProperty().addListener((obs, oldValue, newValue) -> draw());
		heightProperty().addListener((obs, oldValue, newValue) -> draw());
		this.currentPosition.addListener((obs, oldValue, newValue) -> drawCells());
		this.map.addListener((obs, oldValue, newValue) -> drawCells());
		this.full.addListener((obs, oldValue, newValue) -> drawCells());
		this.path.addListener((obs, oldValue, newValue) -> drawPath());
		this.accessible.addListener((obs, oldValue, newValue) -> drawCells());
		this.highlight.addListener((obs, oldValue, newValue) -> drawCells());
		this.cellId.addListener((obs, oldValue, newValue) -> idCanvas.setVisible(newValue));

		setOnMouseClicked(mouseEvent -> {
			if (this.onCellClick == null)
				return;
			if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
				double width = getWidth();
				double height = getHeight();
				DofusMap map = this.map.get();
				boolean full = this.full.get();
				if (map == null)
					return;
				int mWidth = map.getWidth();
				int mHeight = map.getHeight();
				double multiplier = Math.min(width / mWidth, height / mHeight);
				double dMultiplier = multiplier / 2;
				int id = Maps.getId((int) Math.round(mouseEvent.getX() / dMultiplier) - (full ? 1 : 0), (int) Math.round(mouseEvent.getY() / dMultiplier) - (full ? 1 : 0), map.getWidth());
				if (id > 0 && id < map.getCells().length)
					onCellClick.accept(id);
			}
		});

	}

	public void addEntity(int id, int cellid) {
		if (id > 1000) addPlayer(id, cellid);
		else {
			if (mobs.containsKey(id)) mobs.put(id, cellid);
			if (npcs.containsKey(id)) npcs.put(id, cellid);
		}
	}

	public void addPlayer(int id, int cellid) {
		this.players.put(id, cellid);
		drawCells();
	}

	public void addMob(int id, int cellid) {
		this.mobs.put(id, cellid);
		drawCells();
	}

	public void addNpc(int id, int cellid) {
		this.npcs.put(id, cellid);
		drawCells();
	}

	public void removeActor(int id) {
		this.players.remove(id);
		this.mobs.remove(id);
		this.npcs.remove(id);
		drawCells();
	}

	public void clearActors() {
		this.players.clear();
		this.mobs.clear();
		this.npcs.clear();
		drawCells();
	}

	/**
	 * @param onCellClick
	 *            the onCellClick to set
	 */
	public void setOnCellClick(IntConsumer onCellClick) {
		this.onCellClick = onCellClick;
	}

	public void clearPath() {
		if (this.path != null && this.path.get() != null) {
			this.path.setValue(null);
			drawPath();
		}
	}

	protected synchronized void drawCells() {
		double width = getWidth();
		double height = getHeight();
		DofusMap map = this.map.get();
		boolean full = this.full.get();
		int currentPos = this.currentPosition.get();
		if (map == null)
			return;
		int mWidth = map.getWidth();
		int mHeight = map.getHeight();
		double multiplier = Math.min(width / mWidth, height / mHeight);
		double dMultiplier = multiplier / 2;

		height = mHeight * multiplier - (full ? 0 : multiplier);
		width = mWidth * multiplier - (full ? 0 : multiplier);
		setCanvasHeight(height);
		setCanvasWidth(width);
		Cell[] cells = map.getCells();

		GraphicsContext gc = cellCanvas.getGraphicsContext2D();
		gc.clearRect(0, 0, width, height); // Clear canvas

		GraphicsContext gid = idCanvas.getGraphicsContext2D();
		gid.clearRect(0, 0, width, height); // Clear canvas
		gid.setFill(Color.BLACK);
		gid.setTextAlign(TextAlignment.CENTER);

		for (int i = 0; i < cells.length; i++) {
			Cell c = cells[i];
			switch (c.getMovement()) {
				case 0:
					gc.setFill(Color.TRANSPARENT);
					break;
				case 1:
					gc.setFill(Color.BLUEVIOLET);
					break;
				case 2:
					gc.setFill(Color.ALICEBLUE);
					break;
				case 3:
					gc.setFill(Color.GRAY);
					break;
				case 4:
					gc.setFill(Color.LIGHTGRAY);
					break;
				case 5:
					gc.setFill(Color.CHARTREUSE);
					break;
				case 6:
					gc.setFill(Color.SANDYBROWN);
					break;
				case 7:
					gc.setFill(Color.AQUA);
					break;
				default:
					throw new IllegalStateException("Unknown movement " + c.getMovement());
			}
			if (c.getLayerObject1Num() == 1030 || c.getLayerObject2Num() == 1030
					|| c.getLayerObject1Num() == 1029 || c.getLayerObject2Num() == 1029)
				gc.setFill(Color.ORANGE);
			double xp = Maps.getX(i, mWidth) * dMultiplier
					+ (full ? dMultiplier : 0);
			double yp = Maps.getY(i, mWidth) * dMultiplier
					+ (full ? dMultiplier : 0);

			// line of sight
			if (this.accessible != null && this.accessible.get() != null && c.isWalkeable()) {
				if (c.distanceManathan(shadowOrigin.get()) <= this.shadowRange.get()) gc.setFill(Color.LIGHTSKYBLUE);
				if (this.accessible.get().contains(c)) gc.setFill(Color.ROYALBLUE);
			}

			if (this.highlight != null && this.highlight.get() != null && this.highlight.get().contains(c)) gc.setFill(Color.RED);

			gc.fillPolygon(new double[] { xp, xp + dMultiplier, xp, xp - dMultiplier },
					new double[] { yp + dMultiplier, yp, yp - dMultiplier, yp }, 4);
			switch (c.getLayerObject2Num()) {
				case 7511:
					gc.setFill(Color.LIGHTYELLOW);
					gc.drawImage(Constants.wheat, xp - dMultiplier / 2, yp - dMultiplier / 2, dMultiplier, dMultiplier);
					break;
				case 7514:
					gc.setFill(Color.GREENYELLOW);
					gc.drawImage(Constants.chanvre, xp - dMultiplier / 2, yp - dMultiplier / 2, dMultiplier, dMultiplier);
					break;
				case 7515:

					break;
				default:
					break;
			}
			final int cl = i;
			if (i == currentPos) drawOval(gc, Color.BROWN, xp - dMultiplier / 2, yp - dMultiplier / 2, dMultiplier, dMultiplier);
			else this.players.values().stream().filter(po -> po == cl).forEach(v -> gc.drawImage(Constants.player, xp - dMultiplier / 2, yp - dMultiplier / 2, dMultiplier, dMultiplier)); // else pour pas dessiner 2x si player
			this.mobs.values().stream().filter(po -> po == cl).forEach(v -> gc.drawImage(Constants.mob, xp - dMultiplier / 2, yp - dMultiplier / 2, dMultiplier, dMultiplier));
			this.npcs.values().stream().filter(po -> po == cl).forEach(v -> gc.drawImage(Constants.npc, xp - dMultiplier / 2, yp - dMultiplier / 2, dMultiplier, dMultiplier));
			// gid.fillText((Maps.getColumn(i, mWidth) + "," + Maps.getLine(i, mWidth)), xp, yp + gc.getFont().getSize() / 4);
			gid.fillText(Integer.toString(i), xp, yp + gc.getFont().getSize() / 4);
		}
	}

	private void drawOval(GraphicsContext gc, Color c, double x, double y, double w, double h) {
		gc.setFill(c);
		gc.fillOval(x, y, w, h);
	}

	protected void drawPath() {
		double width = getWidth();
		double height = getHeight();
		DofusMap map = this.map.get();
		boolean full = this.full.get();
		List<Node> path = this.path.get();
		if (map == null) return;
		if (path == null) return;
		path = path.stream().map(p -> {
			int id = Maps.getIdRotated(p.getX(), p.getY(), map.getWidth(), map.getHeight());
			return new Node(Maps.getX(id, map.getWidth()), Maps.getY(id, map.getWidth()));
		}).collect(Collectors.toList());
		int mWidth = map.getWidth();
		int mHeight = map.getHeight();
		double multiplier = Math.min(width / mWidth, height / mHeight);
		double dMultiplier = multiplier / 2;

		height = mHeight * multiplier - (full ? 0 : multiplier);
		width = mWidth * multiplier - (full ? 0 : multiplier);

		pathCanvas.setHeight(height);
		pathCanvas.setWidth(width);

		GraphicsContext g = pathCanvas.getGraphicsContext2D();
		g.clearRect(0, 0, width, height); // Clear canvas

		System.out.println(path);
		if (path == null || path.size() < 1)
			return;
		g.setStroke(pathColor == null ? Color.SEAGREEN : pathColor);

		for (int i = 0; i < path.size() - 1; i++) {
			g.strokeLine(path.get(i).x * dMultiplier + dMultiplier, path.get(i).y * dMultiplier + dMultiplier, path.get(i + 1).x * dMultiplier + dMultiplier,
					path.get(i + 1).y * dMultiplier + dMultiplier);
		}
	}

	private void draw() {
		drawCells();
		drawPath();
	}

	public void setAccessible(List<Cell> acc, int origin, int range) {
		this.shadowRange.set(range);
		this.shadowOrigin.set(origin);
		this.accessible.set(acc);
	}

	public void highlightCells(List<Cell> cells) {
		this.highlight.set(cells);
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
		clearActors();
		draw();
	}

	public ObjectProperty<DofusMap> mapProperty() {
		return map;
	}

	public List<Node> getPath() {
		return path.get();
	}

	public void setPath(List<Node> path, Color color) {
		this.pathColor = color;
		this.path.set(path);
	}

	public ObjectProperty<List<Node>> pathProperty() {
		return path;
	}

	public void setCurrentPosition(int currentPosition) {
		this.currentPosition.set(currentPosition);
	}

	public int getCurrentPosition() {
		return currentPosition.get();
	}
}
