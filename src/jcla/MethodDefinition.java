package jcla;

import jcla.classfile.attribute.Code;
import jcla.classfile.attribute.MethodParameters;
import jcla.classfile.attribute.MethodParameters.Parameter;
import jcla.classfile.attribute.RuntimeVisibleAnnotations;
import jcla.classfile.struct.Annotation;
import jcla.classfile.struct.MethodInfo;
import jcla.classfile.util.ClassFilePool;
import jcla.type.ResolvableType;
import jcla.util.Resolvable;
import jcla.util.data.BitArray;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jcla.Modifier.*;

/**
 * A MethodDefinition defines a method in its entirety, including or excluding its bytecode.
 * <p>
 * It contains all relevant information related to a method, but the bytecode is allowed to be null.
 * The internal BytecodeSequence held by MethodDefinitions is transient, so bytecode is not serialized.
 * </p>
 * <p>
 * There also is no BytecodeSequence available for a MethodDefinition if it is an abstract method
 * </p>
 *
 * @author link
 */
public final class MethodDefinition extends Definition {

	private final           List<ParameterDefinition> parameters;
	private final           Set<Resolvable<ClassDefinition>>               exceptions;
	// make sure bytecode isn't transferred over network
	private final transient BytecodeSequence          bytecode;

	public MethodDefinition(MethodInfo methodInfo) {
		super(getAnnotations(methodInfo), getModifiers(methodInfo.getAccessFlags()), methodInfo.getName().getValue());
		parameters = getParameters(methodInfo);
		bytecode = getBytecode(methodInfo);
		exceptions = getExceptions(methodInfo);
	}

	/**
	 * Creates a new MethodDefinition with the given method as method info, and attempts to get the bytecode for the method.
	 *
	 * @param method the method to retrieve method information from
	 */
	public MethodDefinition(Method method) {
		this(method, getBytecode(method));
	}

	/**
	 * Creates a new MethodDefinition with the given method as method info, and the given bytecode sequence.
	 * <p>
	 * It is necessary to provide the bytecode if this Definition will be written to disk as a class file.
	 * The Method class does not directly provide the bytecode for a method. Leave the bytecode parameter
	 * null if the bytecode is unavailable, otherwise the bytecode will be inaccessible
	 * from this MethodDefinition.
	 * </p>
	 *
	 * @param method   the method to retrieve method information from
	 * @param bytecode the bytecode of the method, or null if no bytecode will be provided
	 */
	public MethodDefinition(Method method, BytecodeSequence bytecode) {
		super(getAnnotations(method), getModifiers(method), method.getName());
		parameters = getParameters(method);
		this.bytecode = bytecode;
		exceptions = getExceptions(method);
	}

	/**
	 * Creates a new MethodDefinition with the given annotations, modifiers, name, parameters,
	 * bytecode, and throws clause (exceptions).
	 *
	 * @param annotations the annotations on this method
	 * @param modifiers   the access flags for this method
	 * @param name        the name of this method
	 * @param parameters  the parameters of this method
	 * @param bytecode    the bytecode of this method
	 * @param exceptions  the exceptions of this method
	 */
	public MethodDefinition(Set<Resolvable<ClassDefinition>> annotations, Set<Modifier> modifiers, String name, List<ParameterDefinition> parameters, BytecodeSequence bytecode, Set<Resolvable<ClassDefinition>> exceptions) {
		super(annotations, modifiers, name);
		this.parameters = parameters;
		this.bytecode = bytecode;
		this.exceptions = exceptions;
	}


	/**
	 * Gets the parameters used by this MethodDefinition.
	 * <p>
	 * These parameters are in the order of invocation, i.e. foo(int bar, short baz), will return {int, short} by this
	 * method.
	 * </p>
	 *
	 * @return the types passed in to this method
	 */
	public List<ParameterDefinition> getParameters() {
		return parameters;
	}

	public Set<Resolvable<ClassDefinition>> getExceptions() {
		return exceptions;
	}

	// static methods

	private static Set<Modifier> getModifiers(BitArray accessflags) {
		Set<Modifier> modifiers = new HashSet<>(1);

		if (accessflags.get(ABSTRACT.getBit())) {
			modifiers.add(ABSTRACT);
			if (accessflags.get(PUBLIC.getBit()))
				modifiers.add(PUBLIC);
			else if (accessflags.get(PROTECTED.getBit()))
				modifiers.add(PROTECTED);
			else
				modifiers.add(PACKAGE_LOCAL);
		} else if (accessflags.get(STATIC.getBit())) {
			modifiers.add(STATIC);
			if (accessflags.get(PUBLIC.getBit()))
				modifiers.add(PUBLIC);
			else if (accessflags.get(PRIVATE.getBit()))
				modifiers.add(PRIVATE);
			else if (accessflags.get(PROTECTED.getBit()))
				modifiers.add(PROTECTED);
			else
				modifiers.add(PACKAGE_LOCAL);

			if (accessflags.get(FINAL.getBit()))
				modifiers.add(FINAL);
			if (accessflags.get(NATIVE.getBit()))
				modifiers.add(NATIVE);
			if (accessflags.get(STRICT.getBit()))
				modifiers.add(STRICT);
		}


		if (accessflags.get(SYNTHETIC.getBit()))
			modifiers.add(SYNTHETIC);


		return modifiers;
	}

	private static Set<Modifier> getModifiers(Method method) {
		return getModifiers(new BitArray(method.getModifiers()));
	}

	private static Set<Resolvable<ClassDefinition>> getAnnotations(MethodInfo methodInfo) {
		RuntimeVisibleAnnotations attribute = methodInfo.getAttribute(RuntimeVisibleAnnotations.class);

		if (attribute != null) {
			Annotation[]                     methodAnnotations = attribute.getAnnotations();
			Set<Resolvable<ClassDefinition>> annotations       = new HashSet<>(methodAnnotations.length);

			for (Annotation annotation : methodAnnotations) {
				annotations.add(new ResolvableType(Types.qualify(annotation.getType().getValue())));
			}

			return annotations;
		}

		return Collections.emptySet();
	}

	private static Set<Resolvable<ClassDefinition>> getAnnotations(Method method) {
		java.lang.annotation.Annotation[] annotations = method.getAnnotations();
		Set<Resolvable<ClassDefinition>>  definitions = new HashSet<>(annotations.length);

		for (java.lang.annotation.Annotation annotation : annotations) {
			definitions.add(new ResolvableType(annotation.annotationType().getTypeName()));
		}

		return definitions;
	}

	private static List<ParameterDefinition> getParameters(MethodInfo methodInfo) {
		MethodParameters methodParamsAttribute = methodInfo.getAttribute(MethodParameters.class);

		if (methodParamsAttribute != null) {
			Parameter[]               methodParams = methodParamsAttribute.getParameters();
			List<ParameterDefinition> parameters   = new ArrayList<>(methodParams.length);

			for (Parameter parameter : methodParams)
				parameters.add(new ParameterDefinition(parameter));

			return parameters;
		}

		return Collections.emptyList();
	}

	private static List<ParameterDefinition> getParameters(Method method) {
		final ClassPool               pool         = ClassPool.getLocal();
		java.lang.reflect.Parameter[] methodParams = method.getParameters();
		List<ParameterDefinition>     parameters   = new ArrayList<>(methodParams.length);

		for (java.lang.reflect.Parameter parameter : methodParams) {
			parameters.add(new ParameterDefinition(parameter));
		}

		return parameters;
	}

	private static BytecodeSequence getBytecode(MethodInfo methodInfo) {
		Code code = methodInfo.getAttribute(Code.class);

		if (code != null) {
			return new BytecodeSequence(code);
		}

		return null;
	}

	private static BytecodeSequence getBytecode(Method method) {
		BytecodeSequence bytecode;
		MethodInfo       methodInfo = ClassFilePool.getLocal().get(method.getDeclaringClass()).getMethod(method.getName());
		bytecode = getBytecode(methodInfo);
		return bytecode;
	}

	private static Set<Resolvable<ClassDefinition>> getExceptions(MethodInfo methodInfo) {
		ClassPool        classes               = ClassPool.getLocal();
		MethodParameters methodParamsAttribute = methodInfo.getAttribute(MethodParameters.class);

		if (methodParamsAttribute != null) {
			Parameter[] methodExceptions = methodInfo.getAttribute(MethodParameters.class).getParameters();
			Set<Resolvable<ClassDefinition>> exceptions       = new HashSet<>(methodExceptions.length);

			for (Parameter exception : methodExceptions) {
				exceptions.add(new ResolvableType(Types.qualify(exception.getName().getValue())));
			}

			return exceptions;
		}

		return Collections.emptySet();
	}

	private static Set<Resolvable<ClassDefinition>> getExceptions(Method method) {
		Set<Resolvable<ClassDefinition>> exceptions = new HashSet<>(method.getExceptionTypes().length);

		for (Class<?> exception : method.getExceptionTypes())
			exceptions.add(new ResolvableType(exception.getTypeName()));

		return exceptions;
	}

}
