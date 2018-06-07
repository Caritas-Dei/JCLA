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
 * Created on 5/7/2018 at 7:29 PM
 */

package jcla.lang.production.java;

import jcla.lang.production.Production;
import jcla.lang.production.java.token.Identifier;
import jcla.lang.production.java.token.Token;
import jcla.lang.production.java.token.Tokens;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author Andrew Porter
 */
public class ClassType extends ClassOrInterfaceType {

	public ClassType(Optional<Annotation> annotation, Identifier identifier, TypeArgument[] arguments) {
		super(assemble(annotation, identifier, arguments));
	}

	public ClassType(ClassOrInterfaceType type, Token DOT, Optional<Annotation> annotation, Identifier identifier, TypeArgument[] arguments) {
		super(assemble(type, DOT, annotation, identifier, arguments));
	}

	protected ClassType(Production[] components) {
		super(components);
	}

	private static Production[] assemble(Optional<Annotation> annotation, Identifier identifier, TypeArgument[] arguments) {
		Production[] components;

		if (annotation.isPresent()) {
			components = new Production[2 + arguments.length];
			components[0] = annotation.get();
			components[1] = identifier;

			System.arraycopy(arguments, 0, components, 2, arguments.length);
		}
		else {
			components = new Production[1 + arguments.length];
			components[0] = identifier;

			System.arraycopy(arguments, 0, components, 1, arguments.length);
		}

		return components;
	}

	private static Production[] assemble(ClassOrInterfaceType type, Token DOT, Optional<Annotation> annotation, Identifier identifier, TypeArgument[] arguments) {
		Production[] components;

		if (annotation.isPresent()) {
			components = new Production[4 + arguments.length];
			components[0] = type;
			components[1] = DOT;
			components[2] = annotation.get();
			components[3] = identifier;

			System.arraycopy(arguments, 0, components, 4, arguments.length);
		}
		else {
			components = new Production[3 + arguments.length];
			components[0] = type;
			components[1] = DOT;
			components[2] = identifier;

			System.arraycopy(arguments, 0, components, 3, arguments.length);
		}

		return components;
	}

	@Override
	public ClassType build(Production[] productions) {
		switch (productions.length) {
			case 2:
				if (productions[0] instanceof Identifier && productions[1] instanceof TypeArgument)
					return new ClassType(productions);
				else
					throw new IllegalArgumentException("Cannot create ClassType: must be an Identifier followed by a TypeArgument; value:" + Arrays.toString(productions));
			case 3:
				if (productions[0] instanceof Annotation && productions[1] instanceof Identifier && productions[2] instanceof TypeArgument)
					return new ClassType(productions);
				else
					throw new IllegalArgumentException("Cannot create ClassType: must be an Annotation followed by an Identifier and TypeArgument; value:" + Arrays.toString(productions));
			case 4:
				if (productions[0] instanceof ClassOrInterfaceType && productions[1] instanceof Token && productions[2] instanceof Identifier && productions[3] instanceof TypeArgument)
					if (productions[1].getSymbol().equals("."))
						return new ClassType(productions);
				else
					throw new IllegalArgumentException("Cannot create ClassType: the Identifier must be a DOT (.); value:" + productions[1]);
					else
						throw new IllegalArgumentException("Cannot create ClassType: must be an Identifier followed by a TypeArgument; value:" + Arrays.toString(productions));
			case 5:
			default:
				throw new IllegalArgumentException("Cannot create ClassType: wrong number of productions; value:" + Arrays.toString(productions));
		}
	}

}
