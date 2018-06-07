/**
 * Copyright (c) 2018 Andrew Porter.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 * conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 * This file is part of JCLassAssistant
 *
 * Created on 5/8/2018 at 2:08 PM
 */

package jcla.lang.production.java;

import jcla.lang.production.Production;
import jcla.lang.production.Verifier;

/**
 *
 * @author Andrew Porter
 */
public class ArrayType extends ReferenceType {

	public ArrayType(PrimitiveType type, Dims dims) {
		super(new Production[]{type, dims});
	}

	public ArrayType(ClassOrInterfaceType type, Dims dims) {
		super(new Production[]{type, dims});
	}

	public ArrayType(TypeVariable type, Dims dims) {
		super(new Production[]{type, dims});
	}

	protected ArrayType(Production[] components) {
		super(components);
	}

	@Override
	public ArrayType build(Production[] productions) {
		switch(productions[0].getClass().getSimpleName()) {
			case "PrimitiveType":
			case "ClassOrInterfaceType":
			case "TypeVariable":
				if (Verifier.isExactPattern(productions, Type.class, Dims.class))
				return new ArrayType(productions);
			default:
				throw new IllegalArgumentException("Cannot create ArrayType: invalid production (index: 0): " + productions[0]);
		}

	}

}
