package jcla;

import jcla.classfile.attribute.RuntimeInvisibleAnnotations;
import jcla.classfile.attribute.RuntimeInvisibleTypeAnnotations;
import jcla.classfile.attribute.RuntimeVisibleAnnotations;
import jcla.classfile.attribute.RuntimeVisibleTypeAnnotations;
import jcla.classfile.struct.Annotation;
import jcla.classfile.struct.AttributeInfo;
import jcla.classfile.struct.FieldInfo;
import jcla.classfile.struct.TypeAnnotation;
import jcla.type.ResolvableType;
import jcla.util.Resolvable;
import jcla.util.data.BitArray;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static jcla.Modifier.*;

/**
 * @author link
 */
public final class FieldDefinition extends Definition {

	private final Resolvable<ClassDefinition>      type;

	public FieldDefinition(FieldInfo field) {
		this(getAnnotations(field.getAttributes()), getModifiers(field.getAccessFlags()), new ResolvableType(field.getDescriptor().getValue().substring(1).replace('/', '.')), field.getName().getValue());
	}

	public FieldDefinition(Field field) {
		this(getAnnotations(field.getDeclaredAnnotations()), getModifiers(new BitArray(field.getModifiers())), new ResolvableType(field.getType().getName()), field.getName());
	}

	public FieldDefinition(Set<Modifier> modifiers, Resolvable<ClassDefinition> type, String name) {
		this(Collections.emptySet(), modifiers, type, name);
	}

	public FieldDefinition(Set<Resolvable<ClassDefinition>> annotations, Set<Modifier> modifiers, Resolvable<ClassDefinition> type, String name) {
		super(annotations, modifiers, name);
		this.type = type;
	}

	public Resolvable<ClassDefinition> getType() {
		return type;
	}

	private static Set<Modifier> getModifiers(BitArray accessflags) {
		Set<Modifier> modifiers = new HashSet<>();

		if (accessflags.get(PUBLIC.getBit()))
			modifiers.add(PUBLIC);
		else if (accessflags.get(PRIVATE.getBit()))
			modifiers.add(PRIVATE);
		else if (accessflags.get(PROTECTED.getBit()))
			modifiers.add(PROTECTED);
		else
			modifiers.add(PACKAGE_LOCAL);

		if (accessflags.get(STATIC.getBit()))
			modifiers.add(STATIC);

		if (accessflags.get(FINAL.getBit()))
			modifiers.add(FINAL);
		else if (accessflags.get(VOLATILE.getBit()))
			modifiers.add(VOLATILE);

		if (accessflags.get(TRANSIENT.getBit()))
			modifiers.add(TRANSIENT);
		if (accessflags.get(SYNTHETIC.getBit()))
			modifiers.add(SYNTHETIC);
		if (accessflags.get(ENUM.getBit()))
			modifiers.add(ENUM);

		return modifiers;
	}

	private static Set<Resolvable<ClassDefinition>> getAnnotations(AttributeInfo[] attributes) {
		// TODO normalize all descriptors to fully qualified names
		Set<Resolvable<ClassDefinition>> annotations = new HashSet<>();
		for (AttributeInfo attribute : attributes) {
			// quick check if this is an Annotations attribute
			if (attribute.getName().getValue().endsWith("Annotations"))
				if (attribute instanceof RuntimeVisibleAnnotations) {
					for (Annotation annotation : ((RuntimeVisibleAnnotations) attribute).getAnnotations()) {
						annotations.add(new ResolvableType(Types.qualifyType(annotation.getType().getValue())));
					}
				} else if (attribute instanceof RuntimeInvisibleAnnotations) {
					for (Annotation annotation : ((RuntimeInvisibleAnnotations) attribute).getAnnotations()) {
						annotations.add(new ResolvableType(Types.qualifyType(annotation.getType().getValue())));
					}
				} else if (attribute instanceof RuntimeVisibleTypeAnnotations) {
					for (TypeAnnotation annotation : ((RuntimeVisibleTypeAnnotations) attribute).getAnnotations()) {
						annotations.add(new ResolvableType(Types.qualifyType(annotation.getType().getValue())));
					}
				} else if (attribute instanceof RuntimeInvisibleTypeAnnotations) {
					for (TypeAnnotation annotation : ((RuntimeInvisibleTypeAnnotations) attribute).getAnnotations()) {
						annotations.add(new ResolvableType(Types.qualifyType(annotation.getType().getValue())));
					}
				}
		}
		return annotations;
	}

	private static Set<Resolvable<ClassDefinition>> getAnnotations(java.lang.annotation.Annotation[] annotations) {
		Set<Resolvable<ClassDefinition>> result = new HashSet<>();
		for (java.lang.annotation.Annotation annotation : annotations) {
			result.add(new ResolvableType(annotation.annotationType().getName()));
		}
		return result;
	}

}
