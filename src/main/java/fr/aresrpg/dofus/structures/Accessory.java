package fr.aresrpg.dofus.structures;

import fr.aresrpg.dofus.util.Convert;

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
