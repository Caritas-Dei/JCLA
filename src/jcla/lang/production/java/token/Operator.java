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

package jcla.lang.production.java.token;

import jcla.lang.production.Token;

import java.util.LinkedList;

/**
 *
 * @author Andrew Porter
 */
public class Operator extends Token {

	public static final Definition DEFINITION = new JavaProduction.Definition("Operator", new LinkedList<>());

	public Operator(String rawString) {
		super(rawString);
	}

	@Override
	public Definition getDefinition() {
		return DEFINITION;
	}

	@Override
	public String toString() {
		return "Operator[symbol: \"" + rawString + "\"]";
	}

	@Override
	public int hashCode() {
		return (super.hashCode() & 0xFFFF_FFFB);
	}

}
