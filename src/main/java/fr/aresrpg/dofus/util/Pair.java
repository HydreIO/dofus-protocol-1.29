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

/**
 * 
 * @since
 */
public class Pair<F, S> {

	private F first;
	private S second;

	/**
	 * @param first
	 * @param second
	 */
	public Pair(F first, S second) {
		this.first = first;
		this.second = second;
	}

	/**
	 * @return the first
	 */
	public F getFirst() {
		return first;
	}

	/**
	 * @param first
	 *            the first to set
	 */
	public void setFirst(F first) {
		this.first = first;
	}

	/**
	 * @return the second
	 */
	public S getSecond() {
		return second;
	}

	/**
	 * @param second
	 *            the second to set
	 */
	public void setSecond(S second) {
		this.second = second;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		if (!(obj instanceof Pair)) return false;
		Pair p = (Pair) obj;
		return p.getFirst().equals(getFirst()) && p.getSecond().equals(getSecond());
	}

	@Override
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}

}
