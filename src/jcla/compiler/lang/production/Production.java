package jcla.compiler.lang.production;

import jcla.compiler.lang.grammar.Grammar;
import jcla.compiler.token.Token;

import static jcla.compiler.lang.production.Production.Type.TERMINAL;

/**
 * @author link
 */
public class Production {

	protected final Token[] tokens;
	protected final Grammar grammar;
	protected final Type type;

	public Production(Token[] tokens, Grammar grammar) {
		this(tokens, grammar, TERMINAL);
	}

	public Production(Token[] tokens, Grammar grammar, Type type) {
		this.tokens = tokens;
		this.grammar = grammar;
		this.type = type;
	}

	public final Token[] getTokens() {
		return tokens;
	}

	public final Grammar getGrammar() {
		return grammar;
	}

	public final Type getType() {
		return type;
	}

	public enum Type {
		TERMINAL, NON_TERMINAL, ZERO_OR_MORE, ZERO_OR_ONE
	}

}
