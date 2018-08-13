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

/**
 *
 * @author Andrew Porter
 */
@SuppressWarnings("unchecked")
public final class Tuple {

	private CharSequence identifier;
	private Object[] members;

	public Tuple(CharSequence identifier, Object... members) {
		this.identifier = identifier;
		this.members = members;
	}

	public Tuple(CharSequence identifier, int size) {
		this.identifier = identifier;
		members = new Object[size];
	}

	public CharSequence getIdentifier() {
		return identifier;
	}

	public <T> T get(int object, Class<T> type) {
		return type.cast(members[object]);
	}

	public void set(int object, Object value) {
		members[object] = value;
	}

	public int getMemberCount() {
		return members.length;
	}

	Object[] getMembers() {
		return members;
	}

	void setMembers(Object[] members) {
		this.members = members;
	}

	void setIdentifier(CharSequence identifier) {
		this.identifier = identifier;
	}

}
