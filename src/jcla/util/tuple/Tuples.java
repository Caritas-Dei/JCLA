/*
 * Copyright (c) 2018 Andrew Porter.
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the
 *  "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute,
 *  sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following
 *  conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package jcla.util.tuple;

import jcla.util.data.Cache;

/**
 * A utility class for managing Tuples
 *
 * @author Andrew Porter
 */
public enum Tuples {
	;

	private static final Cache<Tuple> TUPLES = new Cache<>(1, () -> new Tuple("", 0));

	/**
	 * Resets the Tuple to a null state, allowing all Objects held by the Tuple to be garbage collected, and the Tuple
	 * to be reused.
	 *
	 * @param tuple
	 * 		the Tuple to clean
	 */
	public static void clean(Tuple tuple) {
		clean(tuple, tuple.getMemberCount());
	}

	/**
	 * Resets the Tuple to a null state with the given member count, allowing all Objects held by the Tuple to be
	 * garbage collected, and the Tuple to be reused.
	 *
	 * @param tuple
	 * 		the Tuple to clean
	 * @param members
	 * 		the number of members the Tuple will hold
	 */
	public static void clean(Tuple tuple, int members) {
		tuple.setMembers(new Object[members]);
	}

	/**
	 * Resets the Tuple to a null state with the given identifier and member count, allowing all Objects held by the
	 * Tuple to be garbage collected, and the Tuple to be reused.
	 *
	 * @param tuple
	 * 		the Tuple to clean
	 * @param identifier
	 * 		the new name [identifier] for the Tuple
	 * @param members
	 * 		the number of members the Tuple will hold
	 */
	public static void clean(Tuple tuple, CharSequence identifier, int members) {
		tuple.setMembers(new Object[members]);
		tuple.setIdentifier(identifier);
	}

	/**
	 * Resets the Tuple to a null state with the given identifier, allowing all Objects held by the Tuple to be
	 * garbage collected, and the tuple to be reused.
	 *
	 * @param tuple
	 * 		the Tuple to clean
	 * @param identifier
	 * 		the new name [identifier] for the Tuple
	 */
	public static void clean(Tuple tuple, CharSequence identifier) {
		clean(tuple, identifier, tuple.getMemberCount());
	}

	/**
	 * Creates a new Tuple or gets an existing Tuple that was cached previously. New Tuples are always cached.
	 *
	 * @param identifier
	 * 		the identifier of the Tuple
	 * @param members
	 * 		the members of the Tuple
	 * @return the Tuple
	 */
	public static Tuple create(CharSequence identifier, Object... members) {
		Tuple tuple = TUPLES.retrieve();
		tuple.setIdentifier(identifier);
		tuple.setMembers(members);
		return tuple;
	}

	/**
	 * Creates a new tuple or gets an existing Tuple that was cached previously. New Tuples are always cached.
	 *
	 * @param identifier
	 * 		the identifier of the Tuple
	 * @param members
	 * 		the members of the Tuple
	 * @return the Tuple
	 */
	public static Tuple create(CharSequence identifier, int members) {
		Tuple tuple = TUPLES.retrieve();
		tuple.setIdentifier(identifier);
		tuple.setMembers(new Object[members]);
		return tuple;
	}

}
