/**
 * Copyright (c) 2018 Andrew Porter.
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 * <p>
 * This file is part of JCLassAssistant
 * <p>
 * Created on 4/19/2018 at 1:21 PM
 */

package jcla.lang.production.java;

import jcla.lang.production.Production;
import jcla.lang.production.java.token.Keyword;
import jcla.lang.production.java.token.Literal;
import jcla.lang.production.java.token.Token;

/**
 *
 * @author Andrew Porter
 */
public final class NullLiteral extends Literal {
	public NullLiteral(Keyword keyword) {
		super(keyword.getSymbol());
	}

	@Override
	public String toString() {
		return "NullLiteral[symbol: \"" + symbol + "\"]";
	}

	@Override
	public NullLiteral build(Production[] productions) {
		Token token = productions[0];
		if (token instanceof Keyword && token.getSymbol().equals("null"))
			return new NullLiteral((Keyword) token);
		else
			throw new IllegalArgumentException("Cannot create NullLiteral: Keyword is null or does not contain the String \"null\"");
	}

}
