package jcla.util.data.tree;

import java.util.HashMap;
import java.util.Map;

/**
 * A Tree data structure whose Nodes' children are accessed with a key and stored in a HashMap.
 *
 * @author link
 */
public final class HashTree<K, T> {

	private Node<K, T> root = new Node<>(null, null);

	public Node<K, T> getRoot() {
		return root;
	}

	/**
	 * Allows traversal and retrieval of Nodes to and from this HashTree via delegated proxy.
	 *
	 * @return a new Navigator instance for traversing this HashTree
	 */
	public Navigator nagivator() {
		return new Navigator<K, T>(this);
	}

	public static final class Node<K, V> {

		private final HashTree<K, V>     owner;
		// the child Nodes
		private final Map<K, Node<K, V>> children;
		// the parent Node
		private final Node<K, V>         parent;
		// the value at this Node
		private final V                  value;
		// the depth of this Node
		private final int                depth;

		Node(Node<K, V> parent) {
			this(null, parent);
		}

		Node(V value, Node<K, V> parent) {
			this.owner = parent.owner;
			this.value = value;
			children = new HashMap<>();
			this.parent = parent;
			depth = parent == null ? 0 : parent.depth + 1;
		}

		public HashTree<K, V> getOwner() {
			return owner;
		}

		public V getValue() {
			return value;
		}

		public Node<K, V> getParent() {
			return parent;
		}

		public Map<K, Node<K, V>> getChildren() {
			return children;
		}

		public Node<K, V> getChild(K key) {
			return children.get(key);
		}

		public void setChild(K key, Node<K, V> child) {
			children.put(key, child);
		}

		public Node<K, V> replaceChild(K key, Node<K, V> child) {
			return children.replace(key, child);
		}

		public Node<K, V> removeChild(K key) {
			return children.remove(key);
		}

		public static <K, V> Node<K, V> create(V value, Node<K, V> parent) {
			return new Node<>(value, parent);
		}

	}

	@SuppressWarnings("unchecked")
	public final class Navigator<K, T> {

		private final HashTree<K, T> tree;
		private       Node<K, T>     position;

		protected Navigator(HashTree<K, T> tree) {
			this.tree = tree;
			position = tree.root;
		}

		/**
		 * Navigates to the Node located by the given array of keys.
		 * <p>
		 * Beginning at the current Node (position), this Navigator traverses through the Nodes until the child of the last key is
		 * found, or a leaf Node is found. Each Key given represents exactly one child Node, and every successive Key in
		 * the array is a child of the previous Key.
		 * </p>
		 *
		 * @param keys the location of the Node
		 */
		public void navigate(K... keys) {
			navigate(position, keys);
		}

		/**
		 * Navigates to the Node located by the given array of keys starting at the given Node.
		 * <p>
		 * Beginning at the given Node, this Navigator traverses through the Nodes until the child of the last key is
		 * found, or a leaf Node is found. Each Key given represents exactly one child Node, and every successive Key in
		 * the array is a child of the previous Key.
		 * </p>
		 * <p>
		 * The given Node must be owned by this Navigator's HashTree, or this method does nothing.
		 * </p>
		 *
		 * @param start the Node to start at
		 * @param keys the keys corresponding to the child Nodes to traverse
		 */
		public void navigate(Node<K, T> start, K... keys) {
			if (start.owner == tree) {
				Node<K, T> current = start;

				for (K key : keys) {
					current = current.getChild(key);
				}

				position = current;
			}
		}

		/**
		 * Resets this Navigator's position to the HashTree's root Node.
		 */
		public void reset() {
			position = (Node<K, T>) tree.root;
		}

		/**
		 * Gets the Node at the current position.
		 *
		 * @return the Node at the current position
		 */
		public Node<K, T> getNode() {
			return position;
		}

	}

}
