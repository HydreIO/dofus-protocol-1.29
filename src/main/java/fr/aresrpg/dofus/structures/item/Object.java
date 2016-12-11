package fr.aresrpg.dofus.structures.item;

/**
 * 
 * @since
 */
public class Object {

	private ObjectTemplate template;
	private int quantity = 1;
	private int position = -1;
	private int guid;

	public Object(ObjectTemplate tp, int quantity, int pos, int guid) {
		this.template = tp;
		this.quantity = quantity;
		this.position = pos;
		this.guid = guid;
	}

	public static Object fromBankPacket(String datas) {
		String[] dt = datas.substring(1).split("~"); // substring pour enlever le O
		ObjectTemplate temp = new ObjectTemplate(Integer.parseInt(dt[1], 16), "", "", 0, 0, 0, 0, 0, "", "", 0, 0); // TODO
		return new Object(temp, Integer.parseInt(dt[2], 16), Integer.parseInt(dt[3]), Integer.parseInt(dt[0], 16));
	}

	/**
	 * @return the template
	 */
	public ObjectTemplate getTemplate() {
		return template;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @return the position
	 */
	public int getPosition() {
		return position;
	}

	/**
	 * @return the guid
	 */
	public int getGuid() {
		return guid;
	}

}
