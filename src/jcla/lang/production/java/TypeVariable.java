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
 * Created on 5/8/2018 at 2:01 PM
 */

package jcla.lang.production.java;

import jcla.lang.production.Production;
import jcla.lang.production.java.token.Identifier;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Andrew Porter
 */
public class TypeVariable extends ReferenceType {

	public TypeVariable(Optional<Annotation> annotation, Identifier identifier) {
		super(annotation.isPresent() ? new Production[]{ annotation.get(), identifier } : new Production[]{ identifier });
	}

	protected TypeVariable(Production[] components) {
		super(components);
	}

	@Override
	public TypeVariable build(Production[] productions) {
		switch (productions.length) {
			case 1:
				if (productions[0] instanceof Identifier)
					return new TypeVariable(productions);

				throw new IllegalArgumentException("Cannot create TypeVariable: must be an Identifier; value:" + Arrays.toString(productions));
			case 2:
				if (productions[0] instanceof Annotation && productions[1] instanceof Identifier)
					return new TypeVariable(productions);

				throw new IllegalArgumentException("Cannot create TypeVariable: must be an {Annotation} followed by an Identifier; value:" + Arrays.toString(productions));
			default:
				throw new IllegalArgumentException("Cannot create TypeVariable: too many Productions; value:" + Arrays.toString(productions));
		}
	}
}
