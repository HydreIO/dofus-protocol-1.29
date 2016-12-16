package fr.aresrpg.dofus.structures;

/**
 * 
 * @since
 */
public enum ExchangeMove {

	ADD('+'),
	UPDATE('~'),
	REMOVE('-');

	private char c;

	private ExchangeMove(char c) {
		this.c = c;
	}

	/**
	 * @return the code
	 */
	public char getSymbol() {
		return c;
	}

	public static ExchangeMove fromSymbol(char c) {
		if (c == '+') return ADD;
		else if (c == '-') return REMOVE;
		else if (c == '~') return UPDATE;
		return null;
	}

}
