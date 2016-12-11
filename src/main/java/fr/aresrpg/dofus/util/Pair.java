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
		super();
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
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}

}
