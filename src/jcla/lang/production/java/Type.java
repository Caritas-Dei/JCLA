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
 * Created on 4/19/2018 at 1:23 PM
 */

package jcla.lang.production.java;

import jcla.lang.production.Production;

import java.util.Arrays;

/**
 * @author Andrew Porter
 */
public class Type extends Production {

	public Type(PrimitiveType type) {
		this(new Production[]{type});
	}

	public Type(ReferenceType type) {
		this(new Production[]{type});
	}

	protected Type(Production[] components) {
		super(components);
	}

	@Override
	public boolean isConcrete() {
		return false;
	}

	@Override
	public String getSymbol() {
		return null;
	}

	@Override
	public String toString() {
		return "Type[" + Arrays.toString(components) + "]";
	}

	@Override
	public Type build(Production[] productions) {
		Production production = productions[0];
		switch (production.getClass().getCanonicalName()) {
			case "jcla.lang.production.java.PrimitiveType":
				return new Type((PrimitiveType) production);
			case "jcla.lang.production.java.ReferenceType":
				return new Type((ReferenceType) production);
			default:
				throw new IllegalArgumentException("Cannot create Type: production must be a PrimitiveType or ReferenceType; input:" + production);
		}
	}

}
