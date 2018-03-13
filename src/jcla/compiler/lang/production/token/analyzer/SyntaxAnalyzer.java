package jcla.compiler.lang.production.token.analyzer;

import jcla.compiler.lang.production.token.Token;

import java.util.ArrayList;
import java.util.List;

/**
 * @author link
 */
public final class SyntaxAnalyzer {

	/**
	 * Analyzes and converts a List of Tokens into a List of Productions. Each Symbol is just a group of Tokens that
	 * matched a high-level Productions pattern, and the Tokens themselves are identified by their Productions. The process is
	 * documented as follows: TODO
	 *
	 * @param tokens the Tokens to process
	 * @return a List of Productions created from the List of Tokens
	 */
	public List<Symbol> analyze(List<Token> tokens) {
		List<Symbol> result = new ArrayList<>(tokens.size());

		// recursive linear-based grouping


	}

}
