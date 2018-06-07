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
 * Created on 4/19/2018 at 1:41 PM
 */

package jcla.lang.production.java;

import jcla.classfile.union.ElementValue.Array;
import jcla.lang.production.Production;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Andrew Porter
 */
public class ReferenceType extends Type {

	public ReferenceType(ClassOrInterfaceType type) {
		super(new Production[]{type});
	}

	public ReferenceType(TypeVariable type) {
		super(new Production[]{type});
	}

	public ReferenceType(ArrayType type) {
		super(new Production[]{type});
	}

	protected ReferenceType(Production[] components) {
		super(components);
	}

	@Override
	public ReferenceType build(Production[] productions) {
		switch(productions[0].getClass().getSimpleName()) {
			case "ClassOrInterfaceType":
			case "TypeVariable":
			case "ArrayType":
				return new ReferenceType(components);
			default:
				throw new IllegalArgumentException("Cannot create ReferenceType: must be a ClassOrInterfaceType, TypeVariable, or ArrayType; value:" + Arrays.toString(productions));
		}
	}
}
