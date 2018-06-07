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
 * Created on 5/19/2018 at 4:34 PM
 */

package jcla.lang.production.java;

import jcla.lang.production.Production;
import jcla.lang.production.Verifier;
import jcla.lang.production.java.token.Identifier;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Andrew Porter
 */
public class TypeParameter extends Production {

	public TypeParameter(Optional<TypeParameterModifier> modifier, Identifier identifier, TypeBound[] typeBounds) {
		super(create(modifier, identifier, typeBounds));
	}

	protected TypeParameter(Production[] components) {
		super(components);
	}

	@Override
	public final boolean isConcrete() {
		return false;
	}

	@Override
	public final String getSymbol() {
		return null;
	}

	private static Production[] create(Optional<TypeParameterModifier> modifier, Identifier identifier, TypeBound[] typeBounds) {
		Production[] result = new Production[modifier.isPresent() ? 2 + typeBounds.length : 1 + typeBounds.length];
		int index = 0;

		if (modifier.isPresent()) {
			result[0] = modifier.get();
			result[1] = identifier;
			index = 2;
		} else {
			result[0] = identifier;
			index = 1;
		}

		System.arraycopy(typeBounds, 0, result, index, typeBounds.length);

		return result;
	}

	@Override
	public Production build(Production[] productions) {
		// TODO check for TypeBound array
		switch(productions[0].getClass().getSimpleName()){
			case "TypeParameterModifier":
				if (productions.length > 2)
					return new TypeParameter(productions);
			case "Identifier":
				if (productions.length > 1)
					return new TypeParameter(productions);
			default:
				throw new IllegalArgumentException("Cannot create TypeParameter: expected TypeParameterModifier or Identifier; value:" + Arrays.toString(productions));
		}
	}


}
