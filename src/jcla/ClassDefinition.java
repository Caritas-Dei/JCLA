package jcla;

import jcla.classfile.ClassFile;
import jcla.classfile.ClassModifier;
import jcla.classfile.attribute.RuntimeVisibleAnnotations;
import jcla.classfile.constant.Constant;
import jcla.classfile.struct.Annotation;
import jcla.classfile.struct.FieldInfo;
import jcla.classfile.struct.MethodInfo;
import jcla.type.ResolvableType;
import jcla.util.Resolvable;
import jcla.util.data.BitArray;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static jcla.Modifier.*;

/**
 * @author link
 */
public class ClassDefinition extends Definition {

	protected final Resolvable<ClassDefinition>   superclass;
	protected final Resolvable<ClassDefinition>[] interfaces;
	protected final Map<String, FieldDefinition>  fields;
	protected final Map<String, MethodDefinition> methods;


	public ClassDefinition(ClassFile classFile) {
		super(getAnnotations(classFile.getAttribute(RuntimeVisibleAnnotations.class)), getModifiers(classFile.getAccessFlags()), Types.qualifyType(classFile.getName().getValue()));
		superclass = getSuperClass(classFile);
		interfaces = getInterfaces(classFile);
		fields = getFields(classFile);
		methods = getMethods(classFile);
	}

	public ClassDefinition(Set<Resolvable<ClassDefinition>> annotations, Set<Modifier> modifiers, String binaryTypeName, Resolvable<ClassDefinition> superclass, Resolvable<ClassDefinition>[] interfaces, Map<String, FieldDefinition> fields, Map<String, MethodDefinition> methods) {
		super(annotations, modifiers, Types.qualifyType(binaryTypeName));
		this.superclass = superclass;
		this.interfaces = interfaces;
		this.fields = fields;
		this.methods = methods;
	}

	public final Resolvable<ClassDefinition> getSuperClass() {
		return superclass;
	}

	public final boolean hasModifiers(ClassModifier... more) {
		if (more == null || more.length == 0)
			throw new IllegalArgumentException("Must have one or more class modifiers: " + Arrays.toString(more));
		return modifiers.containsAll(Arrays.asList(more));
	}

	public final boolean isInterface() {
		return hasModifier(INTERFACE) && !isAnnotation();
	}

	public final boolean isAnnotation() {
		return hasModifier(ANNOTATION);
	}

	public final boolean isEnum() {
		return hasModifier(ENUM);
	}

	public final boolean isAbstract() {
		return hasModifier(ABSTRACT);
	}

	public final boolean isFinal() {
		return hasModifier(FINAL);
	}

	public final boolean isPublic() {
		return hasModifier(PUBLIC);
	}

	public final boolean isPackageLocal() {
		return hasModifier(PACKAGE_LOCAL);
	}

	public final FieldDefinition getField(String field) {
		return fields.get(field);
	}

	public final Map<String, FieldDefinition> getFields() {
		return fields;
	}

	public final MethodDefinition getMethod(String method) {
		return methods.get(method);
	}

	public final Map<String, MethodDefinition> getMethods() {
		return methods;
	}

	// static methods

	private static ResolvableType getSuperClass(ClassFile classFile) {
		Constant.UTF8 superClass = classFile.getSuperClass();

		if (superClass != null)
			return new ResolvableType(Types.qualifyType(superClass.getValue()));
		else
			return new ResolvableType("java.lang.Object");

	}

	protected static ResolvableType[] getInterfaces(ClassFile classFile) {
		Constant.UTF8[]  intNames   = classFile.getInterfaces();
		ResolvableType[] interfaces = new ResolvableType[intNames.length];

		for (int i = 0; i < intNames.length; i++) {
			interfaces[i] = new ResolvableType(intNames[i].getValue());
		}

		return interfaces;
	}

	/**
	 * Note: it is very important that no call is made to AnnotationDefinition.get from any definition constructor to
	 * prevent circularity errors and thus a StackOverflowError.
	 * <p>
	 * Gets all annotations for the given RuntimeVisibleAnnotations attribute
	 *
	 * @param attribute the attribute to retrieve the annotations from
	 * @return a list of annotations or an empty list
	 */
	protected static Set<Resolvable<ClassDefinition>> getAnnotations(RuntimeVisibleAnnotations attribute) {
		if (attribute != null) {
			Annotation[]                     annotations = attribute.getAnnotations();
			Set<Resolvable<ClassDefinition>> names       = new HashSet<>(annotations.length);

			for (Annotation annotation : annotations) {
				names.add(new ResolvableType(annotation.getType().getValue()));
			}

			return names;
		}

		return Collections.emptySet();
	}

	protected static Set<Modifier> getModifiers(BitArray accessflags) {
		Set<Modifier> modifiers = new HashSet<>(2);
		if (accessflags.get(INTERFACE.getBit())) {
			modifiers.add(INTERFACE);
			modifiers.add(ABSTRACT);
			if (accessflags.get(ANNOTATION.getBit()))
				modifiers.add(ANNOTATION);
		} else {
			if (accessflags.get(FINAL.getBit()))
				modifiers.add(FINAL);
			if (accessflags.get(SUPER.getBit()))
				modifiers.add(SUPER);
			if (accessflags.get(ABSTRACT.getBit()))
				modifiers.add(ABSTRACT);
			if (accessflags.get(ENUM.getBit()))
				modifiers.add(ENUM);
		}
		if (accessflags.get(SYNTHETIC.getBit()))
			modifiers.add(SYNTHETIC);

		if (accessflags.get(PUBLIC.getBit()))
			modifiers.add(PUBLIC);
		else if (accessflags.get(PRIVATE.getBit()))
			modifiers.add(PRIVATE);
		else if (accessflags.get(PROTECTED.getBit()))
			modifiers.add(PROTECTED);
		else
			modifiers.add(PACKAGE_LOCAL);

		return modifiers;
	}

	protected static Map<String, FieldDefinition> getFields(ClassFile classFile) {
		Map<String, FieldDefinition> fields = new HashMap<>();

		for (FieldInfo field : classFile.getFields()) {
			FieldDefinition definition = new FieldDefinition(field);
			fields.put(definition.getName(), definition);
		}

		return fields;
	}

	protected static Map<String, MethodDefinition> getMethods(ClassFile classFile) {
		Map<String, MethodDefinition> methods = new HashMap<>();

		for (MethodInfo method : classFile.getMethods()) {
			MethodDefinition definition = new MethodDefinition(method);
			methods.put(definition.getName(), definition);
		}

		return methods;
	}

	@Override
	public String toString() {
		return "ClassDefinition[name:\"" + name + "\";superclass=\"" + superclass + "\";intefaces:\"" + Arrays.toString(interfaces) + "\";annotations:\"" + annotations + "\";modifiers: {" + modifiers + "};fields: " + fields.size() + ";methods: " + methods.size() + "]";
	}
}
