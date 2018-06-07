package jcla.compiler.analyzer;

import jcla.lang.production.Production;
import jcla.lang.production.java.token.Token;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @author link
 */
public final class SyntaxAnalyzer {

	/**
	 * <p>Analyzes and converts a List of Tokens into a List of Productions. Each Symbol is just a group of Tokens that
	 * matched a high-level Productions pattern, and the Tokens themselves are identified by their Productions. The process is
	 * documented as follows:</p>
	 *
	 * <p>
	 *     A method for analysis of raw tokens to be converted to an array of productions.
	 * </p>
	 * <p>
	 *     A <em>waiting stack</em> and a <em>working stack</em> are introduced.
	 * </p>
	 * <ol>
	 * <li>Push a token onto the working stack</li>
	 * </ol>
	 *
	 * @param tokens the Tokens to process
	 * @return a List of Productions created from the List of Tokens
	 */
	public List<Production> analyze(List<Token> tokens) {
		List<Production> result = new ArrayList<>(tokens.size());
		Deque<Production> waiting = new ArrayDeque<>();
		Deque<Production> working = new ArrayDeque<>();

		return null;
	}

}
