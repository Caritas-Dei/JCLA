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
 * Created on 5/19/2018 at 5:43 PM
 */

package jcla.lang.production;

/**
 *
 * @author Andrew Porter
 */
public enum Synthesizer {
	;

	/**
	 * Reproduces a particular Production from the given constituents. It is assumed that each constituent is at the
	 * Token tier, however, an algorithm automatically detects which tier the array of Productions belongs to and then
	 * continues to anabolize the constituents until there is only one production left.
	 *
	 * @param constituents
	 * @return
	 */
	public Production anabolize(Production[] constituents) {

	}

	public Production anabolize(Production[] constituents, int level) {

	}

	public Production[] catabolize(Production[] constituents) {

	}

	public Production[] catabolize(Production[] constituents, int level) {

	}

}
