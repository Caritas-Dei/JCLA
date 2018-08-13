/*
 * Copyright (c) 2018 Andrew Porter.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute,
 *  sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 *  conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jcla.compiler.analyzer;

import jcla.lang.production.Production;
import jcla.lang.production.Token;

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
	 *     A method for analysis of raw thisToken to be converted to an array of productions.
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
