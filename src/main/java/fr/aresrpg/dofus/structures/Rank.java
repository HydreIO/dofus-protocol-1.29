/*******************************************************************************
 * BotFather (C) - Dofus 1.29 protocol library
 * This class is part of an AresRPG Project.
 *
 * @author Sceat {@literal <sceat@aresrpg.fr>}
 * @author Duarte David {@literal <deltaduartedavid@gmail.com>}
 *  
 * Created 2016
 *******************************************************************************/
package fr.aresrpg.dofus.structures;

/**
 * 
 * @since
 */
public class Rank {

	private int value;
	private int honour;
	private int disgrace;
	private boolean enabled;

	/**
	 * @param value
	 * @param honour
	 * @param disgrace
	 * @param enabled
	 */
	public Rank(int value, int honour, int disgrace, boolean enabled) {
		super();
		this.value = value;
		this.honour = honour;
		this.disgrace = disgrace;
		this.enabled = enabled;
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

	/**
	 * @return the honour
	 */
	public int getHonour() {
		return honour;
	}

	/**
	 * @param honour
	 *            the honour to set
	 */
	public void setHonour(int honour) {
		this.honour = honour;
	}

	/**
	 * @return the disgrace
	 */
	public int getDisgrace() {
		return disgrace;
	}

	/**
	 * @param disgrace
	 *            the disgrace to set
	 */
	public void setDisgrace(int disgrace) {
		this.disgrace = disgrace;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public String toString() {
		return "Rank [value=" + value + ", honour=" + honour + ", disgrace=" + disgrace + ", enabled=" + enabled + "]";
	}

}
