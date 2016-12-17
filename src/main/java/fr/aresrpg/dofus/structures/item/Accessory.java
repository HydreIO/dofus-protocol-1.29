/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 * 
 *         Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures.item;

import fr.aresrpg.dofus.util.Convert;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.IntStream;

/**
 * 
 * @since
 */
public class Accessory {

	private int id;
	private int type;
	private int frame;

	/**
	 * @param id
	 * @param type
	 * @param frame
	 */
	public Accessory(int id, int type, int frame) {
		this.id = id;
		this.type = type;
		this.frame = frame;
	}

	public static Accessory parse(String data) {
		String[] datas = data.split("~");
		int id = Convert.toHexInt(datas[0]);
		int type = 0;
		int frame = 0;
		if (datas.length == 3) {
			type = Convert.toHexInt(datas[1]);
			frame = Convert.toHexInt(datas[2]);
		}
		return new Accessory(id, type, frame);
	}

	public static Accessory[] parseFew(String data) {
		String[] datas = data.split(",", -1);
		Accessory[] ac = new Accessory[datas.length];
		IntStream.range(0, datas.length).forEach(i -> ac[i] = parse(datas[i]));
		return ac;
	}

	public static String serializeFew(Accessory... ac) {
		StringJoiner ajoiner = new StringJoiner(",");
		Arrays.stream(ac).forEach(a -> ajoiner.add(a.serialize()));
		return ajoiner.toString();
	}

	public String serialize() {
		return Convert.fromHexInt(id) + (type == 0 ? "" : "~" + Integer.toHexString(type) + "~" + Integer.toHexString(frame));
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the frame
	 */
	public int getFrame() {
		return frame;
	}

	/**
	 * @param frame
	 *            the frame to set
	 */
	public void setFrame(int frame) {
		this.frame = frame;
	}

	@Override
	public String toString() {
		return "Accessory [id=" + id + ", type=" + type + ", frame=" + frame + "]";
	}

}
