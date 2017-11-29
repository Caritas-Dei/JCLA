package jcla;

import jcla.util.Resolvable;

import java.util.Collections;
import java.util.Set;

/**
 * @author link
 */
public abstract class Definition {

	protected final Set<Resolvable<ClassDefinition>> annotations;
	protected final Set<Modifier> modifiers;
	protected final String name;

	@SuppressWarnings("unchecked")
	protected Definition(Set<Modifier> modifiers, String name) {
		this.annotations = Collections.EMPTY_SET;
		this.modifiers = modifiers;
		this.name = name;
	}

	protected Definition(Set<Resolvable<ClassDefinition>> annotations, Set<Modifier> modifiers, String name) {
		this.annotations = annotations;
		this.modifiers = modifiers;
		this.name = name;
	}

	public final String getName() {
		return name;
	}

	public final Set<Resolvable<ClassDefinition>> getAnnotations() {
		return annotations;
	}

	public final Set<Modifier> getModifiers() {
		return modifiers;
	}

	public final boolean hasModifier(Modifier modifier) {
		return modifiers.contains(modifier);
	}

}
