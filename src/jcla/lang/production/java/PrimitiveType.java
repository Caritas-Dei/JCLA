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
 * Created on 4/19/2018 at 1:36 PM
 */

package jcla.lang.production.java;

import jcla.lang.production.Production;
import jcla.lang.production.java.token.Keyword;
import jcla.lang.production.java.token.Tokens;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static jcla.lang.production.java.token.Tokens.BOOLEAN;

/**
 * @author Andrew Porter
 */
public class PrimitiveType extends Type {

	public PrimitiveType(Optional<Annotation> annotation, NumericType type) {
		super(annotation.isPresent() ? new Production[]{ annotation.get(), type } : new Production[]{ type });
	}

	/**
	 * Creates a PrimitiveType production with optionally an annotation followed by the boolean
	 * keyword.
	 *
	 * @param annotation
	 * 		an optional annotation to be provided
	 */
	public PrimitiveType(Optional<Annotation> annotation) {
		super(annotation.isPresent() ? new Production[]{ annotation.get(), BOOLEAN } : new Production[]{ BOOLEAN });
	}

	protected PrimitiveType(Production[] productions) {
		super(productions);
	}

	@Override
	public String toString() {
		return "PrimitiveType[" + Arrays.toString(components) + "]";
	}

	@Override
	public PrimitiveType build(Production[] productions) {
		Production first = productions[0];
		if (productions.length == 2) {
			Production second = productions[1];
			if (first instanceof Annotation) {
				if (second instanceof NumericType) {
					return new PrimitiveType(Optional.of((Annotation) first), (NumericType) second);
				}
				else if (second == BOOLEAN) {
					return new PrimitiveType(Optional.of((Annotation) first));
				}

				throw new IllegalArgumentException("Cannot create PrimitiveType: the second Production must be NumericType or Keyword.BOOLEAN; value:" + second);
			}
			else {
				throw new IllegalArgumentException("Cannot create PrimitiveType: too many Productions; value:" + Arrays.toString(productions));
			}
		}
		else if (productions.length == 1) {
			if (first == BOOLEAN) {
				return new PrimitiveType(Optional.of(null));
			}
			else if (first instanceof Annotation) {
				return new PrimitiveType(Optional.of((Annotation) first));
			}
		}

		throw new IllegalArgumentException("Cannot create PrimitiveType:\n\t\"{Annotation} NumericType\" or \"{Annotation} boolean\" expected;\n\tvalue:\n\n" + Arrays.toString(productions));
	}

}
