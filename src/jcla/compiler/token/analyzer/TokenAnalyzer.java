package jcla.compiler.token.analyzer;

import jcla.compiler.lang.grammar.Grammar;
import jcla.compiler.lang.production.Production;
import jcla.compiler.token.Token;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author link
 */
public final class TokenAnalyzer {

	/**
	 * Analyzes and converts a List of Tokens into a List of Productions. Each Production is just a group of Tokens that
	 * matched a high-level Grammar pattern, and the Tokens themselves are identified by their Grammar. The process is
	 * documented as follows: TODO
	 *
	 * @param tokens the Tokens to process
	 * @return a List of Productions created from the List of Tokens
	 */
	public List<Production> analyze(List<Token> tokens) {
		List<Grammar> grammars = new ArrayList<>();
		// the waiting stack
		Deque<Grammar> waiting = new ArrayDeque<>(tokens.size());
		// the working stack
		Deque<Grammar> working = new ArrayDeque<>(tokens.size());

		// the analysis loop
		for (Token current : tokens) {

		}

		return null;

	}

}
