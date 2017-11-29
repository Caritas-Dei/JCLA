package jcla;

import jcla.util.regex.Regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author link
 */
public enum Types {
	;

	private static final Regex DESCRIPTOR = new Regex(Pattern.compile("([\\[]+)?([BCDFIJSZ]|L([A-Za-z0-9_$]+(/)?)+;)"));

	/**
	 * Qualifies the given fully-qualified descriptor to a fully-qualified type name.
	 * <p>
	 * This method converts fully-qualified names in the form of<br>
	 * <pre>Ljava/lang/Object;</pre><br>
	 * to<br>
	 * <pre>java.lang.Object</pre><br>
	 * as well as<br>
	 * <pre>[[[[J</pre><br>
	 * to<br>
	 * <pre>long[][][][]</pre>
	 * </p>
	 *
	 * @param type the fully-qualified binary descriptor
	 * @return a fully-qualified type name according to the Java Language Specification
	 * @see #qualifyType(String)
	 * @see #qualifyDescriptor(String)
	 * @see #qualifySignature(String)
	 */
	public static String qualify(String type) {
		switch (type.charAt(0)) {
			case '[':
				final int arrayLength = type.lastIndexOf('[') + 1;
				StringBuilder qualified = new StringBuilder(qualify(type.substring(arrayLength, type.length() - 1)));
				for (int i = 0; i < arrayLength; i++)
					qualified.append("[]");
				return qualified.toString();
			case 'L':
				// in front of L and before ;
				type = type.substring(1, type.length() - 1);
				return type.replace('/', '.');
			case 'B':
				return "byte";
			case 'C':
				return "char";
			case 'D':
				return "double";
			case 'F':
				return "float";
			case 'I':
				return "int";
			case 'J':
				return "long";
			case 'S':
				return "short";
			case 'Z':
				return "boolean";
			default:
				throw new IllegalArgumentException("The given string was not a valid descriptor: \"" + type + "\"");
		}
	}

	/**
	 * Qualifies the given fully-qualified binary name to a fully-qualified type name.
	 * <p>
	 * This method converts fully-qualified names in the form of<br>
	 * <pre>java/lang/Object</pre><strong><em>&emsp;(With or without prefix)</em></strong><br>
	 * to<br>
	 * <pre>java.lang.Object</pre><br>
	 * as well as<br>
	 * <pre>[[[[J</pre><br>
	 * to<br>
	 * <pre>long[][][][]</pre>
	 * </p>
	 *
	 * @param simpleType the fully-qualified binary name
	 * @return a fully-qualified type name according to the Java Language Specification
	 * @see #qualify(String)
	 * @see #qualifyDescriptor(String)
	 * @see #qualifySignature(String)
	 */
	public static String qualifyType(String simpleType) {
		switch (simpleType.charAt(0)) {
			case '[':
				final int arrayLength = simpleType.lastIndexOf('[') + 1;
				StringBuilder qualified = new StringBuilder(qualify(simpleType.substring(arrayLength, simpleType.length() - 1)));
				for (int i = 0; i < arrayLength; i++)
					qualified.append("[]");
				return qualified.toString();
			case 'L':
				return qualify(simpleType);
			default:
				return simpleType.replace('/', '.');
		}
	}

	/**
	 * Qualifies the given descriptor to an array of fully qualified binary class names.
	 * <p>
	 * This method converts fully qualified descriptors in the form of<br>
	 * <pre>IIILjava/lang/Object;Ljava/lang/Class;... OR Ljava/lang/Object;</pre><br>
	 * to<br>
	 * <pre>String[]{int, int, int, java.lang.Object, java.lang.Class, ...} OR String[]{java.lang.Object}</pre>
	 * </p>
	 *
	 * @param descriptor a fully-qualified binary name according to the Java Language Specification
	 * @return a list of fully-qualified type names according to the Java Language Specification
	 */
	public static List<String> qualifyDescriptor(String descriptor) {
		return DESCRIPTOR.in(descriptor).match(Types::qualify);
	}

	/**
	 * Qualifies the given method signature to an array of fully-qualified binary class names.
	 * The order of names is preserved, and the return type is guaranteed to be the last entry
	 * in the returned array of Strings.
	 * <p>
	 * This method converts fully qualified signatures in the form of<br>
	 * <pre>(Ljava/lang/Class;Ljava/lang/Class;I;)V</pre><br>
	 * to<br>
	 * <pre>String[]{java.lang.Class, java.lang.Class, I, V}</pre>
	 * </p>
	 *
	 * @param signature the method signature to convert
	 * @return a list of fully-qualified type names according to the Java language Specification
	 */
	public static List<String> qualifySignature(String signature) {
		// the end of the method parameters
		final int splitIndex = signature.lastIndexOf(")");
		// the method parameters
		final List<String> result = new ArrayList<>(qualifyDescriptor(signature.substring(1, splitIndex - 1)));
		result.add(DESCRIPTOR.in(signature.substring(splitIndex + 1, signature.length() - 1)).match().get(0));
		return result;
	}

}
