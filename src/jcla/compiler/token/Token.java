package jcla.compiler.token;

import jcla.compiler.lang.grammar.Grammar;
import jcla.compiler.lang.production.Production;

/**
 * @author link
 */
public class Token extends Production {

	private static final Token[] NONE = new Token[0];

	private final String  symbol;

	public Token(String symbol, Grammar grammar) {
		super(NONE, grammar);
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return "Token[grammar:" + grammar + ", grammar: " + grammar + ", symbol: \"" + symbol + "\"]";
	}

}
