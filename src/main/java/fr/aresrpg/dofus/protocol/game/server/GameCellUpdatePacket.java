package fr.aresrpg.dofus.protocol.game.server;

import fr.aresrpg.dofus.protocol.*;
import fr.aresrpg.dofus.structures.map.Cell;
import fr.aresrpg.dofus.structures.map.DofusMap;
import fr.aresrpg.dofus.util.Compressor;
import fr.aresrpg.dofus.util.StringJoiner;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 
 * @since
 */
public class GameCellUpdatePacket implements ServerPacket {

	private Map<Cell, Integer> updated = new HashMap<>();

	@Override
	public void read(DofusStream stream) {
		while (stream.available() > 0) {
			String[] datas = stream.read().split(";");
			int id = Integer.parseInt(datas[0]);
			String celldata = datas[1].substring(0, 10);
			String maxHexString = datas[1].substring(10);
			int permanentlvl = datas[2].equals('0') ? 0 : 1;
			Cell cell = Compressor.uncompressCells(celldata)[0];
			updated.put(cell, Integer.parseInt(maxHexString, 16));
			cell.setPermanentLevel(permanentlvl);
		}
	}

	@Override
	public void write(DofusStream stream) {
		stream.allocate(updated.size());
		for (Entry<Cell, Integer> e : updated.entrySet()) {
			StringJoiner joiner = new StringJoiner(";");
			Cell cell = e.getKey();
			String cellData = Compressor.compressCell(cell);
			String maxHexString = Integer.toHexString(e.getValue());
			joiner.add(cell.getId());
			joiner.add(cellData + maxHexString);
			joiner.add(cell.getPermanentLevel());
			stream.write(joiner.toString());
		}
	}

	public void addCell(Cell modified, Cell original) {
		int val = 0;
		if (modified.isLayerObjectExternalAutoSize() != original.isLayerObjectExternalAutoSize()) val |= 65536;
		if (modified.isLayerObjectExternalInteractive() != original.isLayerObjectExternalInteractive()) val |= 32768;
		if (modified.getLayerObjectExternal() != original.getLayerObjectExternal()) val |= 16384;
		if (modified.isActive() != original.isActive()) val |= 8192;
		if (modified.isLineOfSight() != original.isLineOfSight()) val |= 4096;
		if (modified.getMovement() != original.getMovement()) val |= 2048;
		if (modified.getGroundLevel() != original.getGroundLevel()) val |= 1024;
		if (modified.getGroundSlope() != original.getGroundSlope()) val |= 512;
		if (modified.getLayerGroundNum() != original.getLayerGroundNum()) val |= 256;
		if (modified.isLayerGroundFlip() != original.isLayerGroundFlip()) val |= 128;
		if (modified.getLayerGroundRot() != original.getLayerGroundRot()) val |= 64;
		if (modified.getLayerObject1Num() != original.getLayerObject1Num()) val |= 32;
		if (modified.isLayerObject1Flip() != original.isLayerObject1Flip()) val |= 16;
		if (modified.getLayerObject1Rot() != original.getLayerObject1Rot()) val |= 8;
		if (modified.getLayerObject2Num() != original.getLayerObject2Num()) val |= 4;
		if (modified.isLayerObject2Flip() != original.isLayerObject2Flip()) val |= 2;
		if (modified.isLayerObject2Interactive() != original.isLayerObject2Interactive()) val |= 1;
		updated.put(modified, val);
	}

	public void updateCells(DofusMap map) {
		for (Entry<Cell, Integer> e : updated.entrySet()) {
			int value = e.getValue();
			boolean layerexternalsize = (value & 65536) != 0;
			boolean layerexternalinteractive = (value & 32768) != 0;
			boolean layerexternal = (value & 16384) != 0;
			boolean active = (value & 8192) != 0;
			boolean lineofsight = (value & 4096) != 0;
			boolean layermovment = (value & 2048) != 0;
			boolean layergroundlvl = (value & 1024) != 0;
			boolean layergroundslope = (value & 512) != 0;
			boolean layergoundnum = (value & 256) != 0;
			boolean layergroundflip = (value & 128) != 0;
			boolean layergroundrot = (value & 64) != 0;
			boolean layero1num = (value & 32) != 0;
			boolean layero1flip = (value & 16) != 0;
			boolean layero1rot = (value & 8) != 0;
			boolean layero2num = (value & 4) != 0;
			boolean layero2flip = (value & 2) != 0;
			boolean layero2inter = (value & 1) != 0;
			Cell last = e.getKey();
			Cell cell = map.getCell(last.getId());
			if (active) cell.setActive(last.isActive());
			if (lineofsight) cell.setLineOfSight(last.isLineOfSight());
			if (layermovment) cell.setMovement(last.getMovement());
			if (layergroundlvl) cell.setGroundLevel(last.getGroundLevel());
			if (layergroundslope) cell.setGroundSlope(last.getGroundSlope());
			if (layergoundnum) cell.setLayerGroundNum(last.getLayerGroundNum());
			if (layergroundflip) cell.setLayerGroundFlip(last.isLayerGroundFlip());
			if (layergroundrot) cell.setLayerGroundRot(last.getLayerGroundRot());
			if (layero1num) cell.setLayerObject1Num(last.getLayerObject1Num());
			if (layero1rot) cell.setLayerObject1Rot(last.getLayerObject1Rot());
			if (layero1flip) cell.setLayerObject1Flip(last.isLayerObject1Flip());
			if (layero2flip) cell.setLayerObject2Flip(last.isLayerObject2Flip());
			if (layero2inter) cell.setLayerObject2Interactive(last.isLayerObject2Interactive());
			if (layero2num) cell.setLayerObject2Num(last.getLayerObject2Num());
			if (layerexternal) cell.setLayerObjectExternal(last.getLayerObjectExternal());
			if (layerexternalinteractive) cell.setLayerObjectExternalInteractive(last.isLayerObjectExternalInteractive());
			if (layerexternalsize) cell.setLayerObjectExternalAutoSize(last.isLayerObjectExternalAutoSize());
			cell.setLayerObjectExternalData(last.getLayerObjectExternalData());
		}
	}

	/**
	 * @return the updated
	 */
	public Map<Cell, Integer> getUpdated() {
		return updated;
	}

	/**
	 * @param updated
	 *            the updated to set
	 */
	public void setUpdated(Map<Cell, Integer> updated) {
		this.updated = updated;
	}

	@Override
	public void handleServer(ServerPacketHandler handler) {
		handler.handle(this);
	}

}
