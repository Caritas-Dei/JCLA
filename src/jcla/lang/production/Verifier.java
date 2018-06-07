/*
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
 * Created on 5/18/2018 at 1:36 PM
 */

package jcla.lang.production;

/**
 * @author Andrew Porter
 */
public enum Verifier {
	;

	/**
	 * Checks if the given production array matches exactly the given pattern. The number and kind of Productions is
	 * verified to be 1:1 with the number and kind in the pattern array. The production classes are checked for whether
	 * or not they are subclasses of the classes provided in format. The number and kind of productions must match
	 * the permutation of classes.
	 *
	 * @param productions
	 * 		the productions to compare against format
	 * @param pattern
	 * 		the expected format of the productions array
	 * @return true if the productions array exactly matches format; false otherwise
	 */
	public static boolean isExactPattern(Production[] productions, Class<? extends Production>... pattern) {
		if (productions.length == 0)
			throw new IllegalArgumentException("productions must be at least of size 1");
		if (pattern.length == 0)
			throw new IllegalArgumentException("format must be at least of size 1");
		if (pattern.length != productions.length)
			return false;

		for (int i = 0; i < productions.length; i++) {
			Production production = productions[i];
			Class<? extends Production> type = pattern[i];

			if (production != null) {
				if (type != null) {
					// if production class is subclass of type's class
					if (type.isAssignableFrom(production.getClass()))
						continue;

					return false;
				}
				throw new NullPointerException("format type is null: format[" + i + "]");
			}
			throw new NullPointerException("production is null: productions[" + i + "]");
		}

		return true;
	}

	/**
	 * Checks if the given production array matches exactly the given format. The number and kind of Productions is
	 * verified to be 1:1 with the number and kind in the format array. The production classes are checked for whether
	 * or not they match the classes provided in format. The number and kind of productions must match
	 * the permutation of classes.
	 *
	 * @param productions
	 * 		the productions to compare against format
	 * @param format
	 * 		the expected format of the productions array
	 * @return true if the productions array exactly matches format; false otherwise
	 */
	public static boolean isExactFormat(Production[] productions, Class<? extends Production>... format) {
		if (productions.length == 0)
			throw new IllegalArgumentException("productions must be at least of size 1");
		if (format.length == 0)
			throw new IllegalArgumentException("format must be at least of size 1");
		if (format.length != productions.length)
			return false;

		for (int i = 0; i < productions.length; i++) {
			Production production = productions[i];
			Class<? extends Production> type = format[i];

			if (production != null) {
				if (type != null) {
					// if production class matches the class of type
					if (production.getClass() == type)
						continue;

					return false;
				}
				throw new NullPointerException("format type is null: format[" + i + "]");
			}
			throw new NullPointerException("production is null: productions[" + i + "]");
		}

		return true;
	}

	/**
	 * Checks if the given contiguous (recurring) pattern continuously matches the entire array of given productions. The size
	 * must also match.
	 *
	 * @param productions
	 * 		the productions to compare against pattern
	 * @param pattern
	 * 		the expected pattern of the productions array
	 * @return true if the productions array exactly duplicates or matches the pattern; false otherwise
	 */
	public static boolean isValidPattern(Production[] productions, Class<? extends Production>... pattern) {
		if (productions.length == 0)
			throw new IllegalArgumentException("productions must be at least of size 1");
		if (pattern.length == 0)
			throw new IllegalArgumentException("format must be at least of size 1");

		for (int i = 0; i < productions.length; i += pattern.length) {
			Production[] current = new Production[pattern.length];
			System.arraycopy(productions, i, current, 0, pattern.length);

			if (isExactPattern(current, pattern))
				continue;

			return false;
		}

		return true;
	}

}
