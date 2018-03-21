package jcla.compiler.lang.production.token;

import jcla.compiler.lang.production.Production;

import java.util.Collections;
import java.util.List;

/**
 * @author link
 */
public abstract class Token extends Production {

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

	/**
	 * Gets the ID of this Token. This is used by the {@linkplain jcla.compiler.lang.production.search.ProductionLookupTable} to
	 * know which kind of Token this is. This value can change before or after runtime and will not affect the semantics.
	 * <p>
	 * 0 - Identifier
	 * 1 - Keyword
	 * 2 - Literal
	 * 3 - Separator
	 * 4 - Operator
	 *
	 * @return the ID of this Token
	 */
	public abstract int getID();

	/**
	 * Returns this Token in an array of size one.
	 *
	 * @return this Token in an array of size one
	 */
	@Override
	public final List<Token> asTokens() {
		return Collections.singletonList(this);
	}

}
