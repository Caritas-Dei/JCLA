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
 * Created on 5/17/2018 at 2:49 PM
 */

package jcla.lang.production.java;

import jcla.lang.production.Production;
import jcla.lang.production.java.token.Token;
import jcla.lang.production.java.token.Tokens;

/**
 *
 * @author Andrew Porter
 */
public class Dims extends Production {

	public Dims(Annotation annotation) {
		super(new Production[]{annotation});
	}

	public Dims(Annotation[] annotations) {
		super(annotations);
	}

	protected Dims(Production[] components) {
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
	public Production build(Production[] productions) {
		return null;
	}
}
