package fr.aresrpg.dofus.structures.game;

import fr.aresrpg.dofus.structures.Alignment;

/**
 * 
 * @since
 */
public class FightTeam {

	private int id;
	private int cellNum;
	private double color1;
	private int type;
	private Alignment alignment;

	/**
	 * @param id
	 * @param cellNum
	 * @param color1
	 * @param type
	 * @param alignment
	 */
	public FightTeam(int id, int cellNum, double color1, int type, Alignment alignment) {
		super();
		this.id = id;
		this.cellNum = cellNum;
		this.color1 = color1;
		this.type = type;
		this.alignment = alignment;
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
	 * @return the cellNum
	 */
	public int getCellNum() {
		return cellNum;
	}

	/**
	 * @param cellNum
	 *            the cellNum to set
	 */
	public void setCellNum(int cellNum) {
		this.cellNum = cellNum;
	}

	/**
	 * @return the color1
	 */
	public double getColor1() {
		return color1;
	}

	/**
	 * @param color1
	 *            the color1 to set
	 */
	public void setColor1(double color1) {
		this.color1 = color1;
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
	 * @return the alignment
	 */
	public Alignment getAlignment() {
		return alignment;
	}

	/**
	 * @param alignment
	 *            the alignment to set
	 */
	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	@Override
	public String toString() {
		return "FightTeam [id=" + id + ", cellNum=" + cellNum + ", color1=" + color1 + ", type=" + type + ", alignment=" + alignment + "]";
	}

}
