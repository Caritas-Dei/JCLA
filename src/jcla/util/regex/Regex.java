/*
 * The MIT License
 *
 * Copyright 2017 link.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 *
 * Created file on 1/27/17 at 7:20 AM.
 *
 * This file is part of Atomic
 */
package jcla.util.regex;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regular expression. This implementation takes one input.
 *
 * @author link
 */
public class Regex {

	private static final List<String> EMPTY_MATCH = Collections.singletonList("");

	public static final Regex MATCH_ANY = new Regex() {
		@Override
		public List<String> match() {
			return Collections.singletonList(this.input);
		}

		@Override
		public boolean matches() {
			return true;
		}
	};
	public static final Regex EMPTY_ALWAYS = new Regex("") {

		@Override
		public List<String> match() {
			return EMPTY_MATCH;
		}

		@Override
		public boolean matches() {
			return false;
		}

	};

	protected final Pattern pattern;
	protected       String  input;

	private Regex() {
		pattern = null;
	}

	public Regex(String regex) {
		this(null, regex);
	}

	public Regex(String regex, int flags) {
		this("", Pattern.compile(regex, flags));
	}

	public Regex(Pattern regex) {
		this.pattern = regex;
	}

	public Regex(String input, String regex) {
		this(input, Pattern.compile(regex));
	}

	public Regex(String input, Pattern pattern) {
		this.pattern = pattern;
		this.input = input;
	}

	/**
	 * Matches the given input against this Regex and returns any matches found
	 * in the given input, or an empty string if no matches were found.
	 *
	 * @param input the input to test against this Regex
	 * @return the results, or an empty String if no match was found
	 */
	public static List<String> match(String input, String regex) {
		return new Regex(input, regex).match();
	}

	/**
	 * Combines two or more Regex patterns into one Regex pattern.
	 *
	 * @param first the first Regex
	 * @param other another Regex
	 * @param more more patterns
	 * @return an amalgamation of the given Regex patterns
	 */
	public static Regex combine(Regex first, Regex other, Regex... more) {
		if (first != null && other == null) return first;
		else if (first == null && other != null) return other;
		else if (first == null && other == null)
			throw new IllegalArgumentException("first and other Regex cannot both be null!");

		if (first != null && other != null) {
			String regex = "(" + first.getPattern().pattern() + ")";
			regex += "|(" + other + ")";
			if (more != null && more.length > 0) for (Regex r : more)
				regex += "|(" + r.getPattern().pattern() + ")";

			return new Regex(regex);
		}

		return null;
	}

	/**
	 * Gets the Pattern object backed by this Regex.
	 *
	 * @return the Pattern object backed by this Regex
	 */
	public final Pattern getPattern() {
		return pattern;
	}

	/**
	 * Sets the internal String to {@code input} and returns this Regex for chain calls.
	 *
	 * @param input the new internal String
	 * @return this Regex
	 */
	public final Regex in(String input) {
		this.input = input;
		return this;
	}

	/**
	 * Sets the internal char array to {@code input} and returns this Regex for chain calls.
	 *
	 * @param input the new internal char array
	 * @return this Regex
	 */
	public final Regex in(char[] input) {
		return in(String.valueOf(input));
	}

	/**
	 * Finds all matches for the {@linkplain #in(String) internal String} and returns the list of matches.
	 *
	 * @return the matches for the internal String against this Regex
	 */
	public List<String> match() {
		final Matcher matcher = pattern.matcher(input);
		// at least one match found
		final boolean found = matcher.find();
		// add each result to the list
		if (found) {
			// the matches
			List<String> matches = new ArrayList<>(1);

			do {
				matches.add(matcher.group());
			} while (matcher.find());

			return matches;
		} else  // no results, return EMPTY_MATCH
			return EMPTY_MATCH;
	}

	/**
	 * Finds all matches for the {@linkplain #in(String) internal String} and returns the list of matches.
	 * <p>
	 * This method is identical to {@link #match()}, except that it allows a Function to be applied to each
	 * match before adding it to the list of matches to be returned.
	 * </p>
	 *
	 * @return the matches for the internal String against this Regex
	 */
	public List<String> match(java.util.function.Function<String, String> forEach) {
		final Matcher matcher = pattern.matcher(input);
		// at least one match found
		final boolean found = matcher.find();
		// add each result to the list
		if (found) {
			// the matches
			List<String> matches = new ArrayList<>(1);

			do {
				matches.add(forEach.apply(matcher.group()));
			} while (matcher.find());

			return matches;
		} else  // no results, return EMPTY_MATCH
			return EMPTY_MATCH;
	}

	/**
	 * Tests if the given {@linkplain #in(String) internal String} matches this Regex.
	 *
	 * @return true if the internal String matches this Regex; false otherwise
	 */
	public boolean matches() {
		return pattern.matcher(input).matches();
	}

}
