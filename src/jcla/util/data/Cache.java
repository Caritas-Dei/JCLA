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

package jcla.util.data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Andrew Porter
 */
public final class Cache<T> {

	private List<T> store;
	private int next = 0;
	private Supplier<T> constructor;
	private boolean[] used;

	/**
	 * Creates a new Cache with the given initial size, and Object constructor. Cache is meant to Cache JavaBean objects,
	 * or objects whose field(s)'(s) initialization is deferred.
	 *
	 * @param initialSize
	 * 		the initial size of the Cache
	 * @param constructor
	 * 		the constructor of the JavaBean or Object
	 */
	public Cache(int initialSize, Supplier<T> constructor) {
		this.store = new ArrayList<>(initialSize);
		this.constructor = constructor;
		used = new boolean[initialSize];
	}

	/**
	 * Caches the given object.
	 *
	 * @param object
	 * 		the object to cache
	 */
	public void add(T object) {
		store.add(object);
		used[next] = true;
		next++;
	}

	/**
	 * Creates a new object to be cached and returns it, or returns a previously cached object.
	 *
	 * @return a new object, or a previously cached object
	 */
	public T retrieve() {
		int index = next;
		int size = used.length;

		if (index < size) {
			final T object = constructor.get();
			store.add(object);
			return object;
		}

		index = findEmptyOrUnused();

		if (index == -1) {
			resize(size * 2);
			return retrieve();
		}

		T object = constructor.get();
		store.set(index, object);
		used[index] = true;
		next = index + 1;

		return object;
	}

	/**
	 * Releases one reference from the store to be garbage collected. The Cache may be resized.
	 */
	public void release() {
		next--;
		used[next] = false;
		store.remove(next);
	}

	/**
	 * Releases <em>n</em> references from the store to be garbage collected. The Cache may be resized.
	 *
	 * @param n
	 * 		the number of references to release
	 */
	public void release(int n) {
		for (int i = n; i > 0; i--) {
			release();
		}
	}

	/**
	 * Releases all references from the store to be garbage collected.
	 */
	public void clear() {
		release(store.size());
	}

	/**
	 * Resizes the internal store to the given capacity
	 *
	 * @param capacity
	 * 		the new capacity of the internal store
	 */
	private void resize(int capacity) {
		List<T> store = this.store;
		this.store = new ArrayList<>(capacity);
		this.store.addAll(capacity < store.size() ? store.subList(0, capacity - 1) : store);
		if (next >= capacity)
			next = capacity - 1;

		boolean[] used = new boolean[capacity];
		System.arraycopy(this.used, 0, used, 0, this.used.length);
		this.used = used;
	}

	/**
	 * Searches for an empty or unused slot in the cache. If no such slot can be found, returns -1.
	 *
	 * @return an empty or unused slot in the cache, or -1 if no slot is empty or unused
	 */
	private int findEmptyOrUnused() {
		int index = 0;
		for (boolean using : used) {
			if (!using)
				return index;
			index++;
		}

		return -1;
	}

}
