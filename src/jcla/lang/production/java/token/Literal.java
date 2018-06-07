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
 * Created on 3/13/2018 at 7:28 PM
 */

package jcla.lang.production.java.token;

import jcla.lang.production.Production;

/**
 * @author Andrew Porter
 */
public class Literal extends Token {

	public Literal(String symbol) {
		super(symbol);
	}

	@Override
	public String toString() {
		return "Literal[symbol: \"" + symbol + "\"]";
	}

	@Override
	public int getID() {
		return 2;
	}

	@Override
	public Literal build(Production[] productions) {
		String symbol = productions[0].getSymbol();
		if (symbol != null)
			return new Literal(symbol);
		else
			throw new IllegalArgumentException("Cannot create Literal: symbol is null.");
	}
}
