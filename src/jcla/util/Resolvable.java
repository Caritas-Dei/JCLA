package jcla.util;

/**
 * Represents an object that is a reference to another object, i.e. a <i>resolvable reference</i> that lazily retrieves
 * the object that it resolves.
 *
 * @author link
 */
@FunctionalInterface
public interface Resolvable<T> {

	/**
	 * Resolves the object that is referenced by this object.
	 *
	 * @return the object that is referenced by this object
	 */
	T resolve();

}
