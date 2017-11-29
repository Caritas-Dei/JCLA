package jcla.type;

import jcla.ClassDefinition;
import jcla.ClassPool;
import jcla.util.Resolvable;

import java.lang.reflect.Type;

/**
 * A ResolvableType that contains a String which can be resolved to a ClassDefinition.
 *
 * @author link
 */
public class ResolvableType implements Resolvable<ClassDefinition>, Type {

	protected final String    type;
	protected final ClassPool classPool;

	public ResolvableType(String type) {
		this(type, ClassPool.getLocal());
	}

	public ResolvableType(Class<?> type) {
		this(type.getTypeName());
	}

	public ResolvableType(String type, ClassPool classPool) {
		this.type = type;
		this.classPool = classPool;
	}

	@Override
	public ClassDefinition resolve() {
		return classPool.get(type);
	}

	@Override
	public String toString() {
		return type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ResolvableType type = (ResolvableType) o;

		return this.type.equals(type.type) && classPool.equals(type.classPool);
	}

	@Override
	public int hashCode() {
		return 31 * type.hashCode() + classPool.hashCode();
	}

}
