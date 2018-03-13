package jcla.compiler.lang.production.token;

import jcla.compiler.lang.production.Production;

/**
 * @author link
 */
public class Token extends Production {

	protected final String symbol;

	public Token(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public final boolean isConcrete() {
		return true;
	}

	@Override
	public final String getSymbol() {
		return symbol;
	}

	@Override
	public String toString() {
		return "Token[symbol: \"" + symbol + "\"]";
	}

}
