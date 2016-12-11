package fr.aresrpg.dofus.structures.game;

import fr.aresrpg.dofus.structures.Alignment;

/**
 * 
 * @since
 */
public class Alignement {

	private Alignment index;
	private int value;
	private boolean fallenAngelDemon = false;

	/**
	 * @param index
	 * @param value
	 */
	public Alignement(Alignment index, int value) {
		this.index = index;
		this.value = value;
	}

	public Alignement(int index, int value) {
		this(Alignment.valueOf(index), value);
	}

	/**
	 * @return the fallenAngelDemon
	 */
	public boolean isFallenAngelDemon() {
		return fallenAngelDemon;
	}

	/**
	 * @param fallenAngelDemon
	 *            the fallenAngelDemon to set
	 */
	public Alignement setFallenAngelDemon(boolean fallenAngelDemon) {
		this.fallenAngelDemon = fallenAngelDemon;
		return this;
	}

	/**
	 * @return the index
	 */
	public Alignment getIndex() {
		return index;
	}

	/**
	 * @param index
	 *            the index to set
	 */
	public void setIndex(Alignment index) {
		this.index = index;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Alignement [index=" + index + ", value=" + value + "]";
	}

}
