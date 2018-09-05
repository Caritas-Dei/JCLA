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

package jcla.lang.production;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 *
 * @author Andrew Porter
 */
public interface Production {

	Definition getDefinition();

	List<? extends Production> getParts();

	default List<Token> getTokens() {
		List<? extends Production> parts = getParts();
		List<Token> tokens = new ArrayList<>(parts.size());

		for (Production production : parts) {
			tokens.addAll(production.getTokens());
        }

        return tokens;
    }

	default boolean isToken() {
		return this instanceof Token;
	}

	default String getRawString() {
		List<Token> tokens = getTokens();
		StringJoiner rawString = new StringJoiner(" ");

		for (Token token : tokens) {
			rawString.add(token.getRawString());
		}

		return rawString.toString();
	}

	interface Definition {

		CharSequence getName();

		List<? extends Definition> getPattern();

	}

}
