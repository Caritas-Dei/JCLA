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
 * Created on 4/20/2018 at 4:12 PM
 */

package jcla.lang.production.java;

import jcla.lang.production.Production;
import jcla.lang.production.java.token.Keyword;

/**
 * @author Andrew Porter
 */
public class IntegralType extends NumericType {

	public IntegralType(Keyword keyword) {
		this(validate(keyword));
	}

	protected IntegralType(Production[] productions) {
		super(productions);
	}

	private static Production[] validate(Keyword keyword) {
		String symbol = keyword.getSymbol();
		switch (symbol) {
			case "int":
			case "long":
			case "char":
			case "byte":
			case "short":
				return new Production[]{keyword};
			default:
				throw new IllegalArgumentException("The keyword must match one of the following: byte, short, int, long, char; input:" + symbol);
		}
	}

	@Override
	public IntegralType build(Production[] productions) {
		Production production = productions[0];
		if (production instanceof Keyword)
			return new IntegralType((Keyword) production);
		else
			throw new IllegalArgumentException("Cannot create IntegralType: the production is not a Keyword");
	}

}
