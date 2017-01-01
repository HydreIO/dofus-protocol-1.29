/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.map;

import fr.aresrpg.dofus.util.Maps;

import java.util.Arrays;
import java.util.function.Predicate;

public class DofusMap {
	private int id;
	private int width;
	private int height;
	private int musicId;
	private int capabilities;
	private boolean outdoor;
	private int backgroundId;
	private Cell[] cells;

	public DofusMap(int id, int width, int height, int musicId, int capabilities, boolean outdoor, int backgroundId, Cell[] cells) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.musicId = musicId;
		this.capabilities = capabilities;
		this.outdoor = outdoor;
		this.backgroundId = backgroundId;
		this.cells = cells;
	}

	public Cell getCell(Predicate<Cell> cell) {
		for (Cell c : cells)
			if (cell.test(c)) return c;
		return null;
	}

	public Cell getCell(int x, int y) {
		return getCell(Maps.getId(x, y, width, height));
	}

	public Cell getCell(int id) {
		return cells[id];
	}

	public int getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	/**
	 * @return the outdoor
	 */
	public boolean isOutdoor() {
		return outdoor;
	}

	/**
	 * @param outdoor
	 *            the outdoor to set
	 */
	public void setOutdoor(boolean outdoor) {
		this.outdoor = outdoor;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param width
	 *            the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @param height
	 *            the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @param musicId
	 *            the musicId to set
	 */
	public void setMusicId(int musicId) {
		this.musicId = musicId;
	}

	/**
	 * @param capabilities
	 *            the capabilities to set
	 */
	public void setCapabilities(int capabilities) {
		this.capabilities = capabilities;
	}

	/**
	 * @param backgroundId
	 *            the backgroundId to set
	 */
	public void setBackgroundId(int backgroundId) {
		this.backgroundId = backgroundId;
	}

	/**
	 * @param cells
	 *            the cells to set
	 */
	public void setCells(Cell[] cells) {
		this.cells = cells;
	}

	public int getHeight() {
		return height;
	}

	public Cell[] getCells() {
		return cells;
	}

	public void replaceCell(int index, Cell c) {
		cells[index] = c;
	}

	/**
	 * @return the backgroundId
	 */
	public int getBackgroundId() {
		return backgroundId;
	}

	/**
	 * @return the capabilities
	 */
	public int getCapabilities() {
		return capabilities;
	}

	/**
	 * @return the musicId
	 */
	public int getMusicId() {
		return musicId;
	}

	@Override
	public String toString() {
		return "DofusMap [id=" + id + ", width=" + width + ", height=" + height + ", musicId=" + musicId + ", capabilities=" + capabilities + ", outdoor=" + outdoor + ", backgroundId=" + backgroundId
				+ ", cells=" + Arrays.toString(cells) + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		return obj instanceof DofusMap && ((DofusMap) obj).getId() == getId();
	}
}
