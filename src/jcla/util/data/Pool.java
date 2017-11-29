package jcla.util.data;

/**
 * @author link
 */
public interface Pool<K, V> {

	Pool<K, V> getParent();

	V get(K key);

	default V retrieve(K key) {
		return get(key);
	}

	void set(K key, V value);

	default void store(K key, V value) {
		set(key, value);
	}

	void add(V value);

	default void append(V value) {
		add(value);
	}

	V remove(K key);

	void clear();

	boolean contains(K key);

}
