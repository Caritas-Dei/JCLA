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

package jcla.lang.production.java;

import jcla.lang.production.AbstractProduction;
import jcla.lang.production.Production;
import jcla.lang.production.Token;

import java.util.List;

import static jcla.lang.production.java.token.Tokens.BOOLEAN;

/**
 * @author Andrew Porter
 */
public final class PrimitiveType extends AbstractProduction {

	public static final Definition DEFINITION = new Definition("PrimitiveType", List.of(
			new Definition.ZeroOrMore(List.of(Annotation.DEFINITION)),
			new OneOf(List.of(NumericType.DEFINITION, BOOLEAN.getDefinition()))
	));

	public PrimitiveType(Annotation annotation, NumericType type) {
		super(List.of(annotation, type));
	}

	public PrimitiveType(NumericType type) {
		super(List.of(type));
	}

	public PrimitiveType(Annotation annotation, Token booleanType) {
		super(List.of(annotation, booleanType));
	}

	public PrimitiveType(Token booleanType) {
		super(List.of(booleanType));
		if (booleanType != BOOLEAN)
			throw new IllegalArgumentException("The given token must be the boolean token: " + booleanType);
	}

	@Override
	public Production.Definition getDefinition() {
		return null;
	}

}
