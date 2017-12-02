package jcla.compiler.token;

import jcla.compiler.token.hint.Hint;
import jcla.compiler.token.tag.Tag;

import java.util.Set;

/**
 * @author link
 */
public final class Token {

	private final String     symbol;
	private final Tag        tag;
	private final Set<Hint> hints;

	public Token(String symbol, Tag tag, Hint... hints) {
		this.symbol = symbol;
		this.tag = tag;
		this.hints = Set.of(hints);
	}

	public String getSymbol() {
		return symbol;
	}

	public Tag getTag() {
		return tag;
	}

	public Set<Hint> getHints() {
		return hints;
	}

	@Override
	public String toString() {
		return "\n[ tag:" + tag + ", hints: " + hints + ", symbol: \"" + symbol + "\" ]";
	}

}
