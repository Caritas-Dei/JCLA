package jcla;

import jcla.classfile.attribute.MethodParameters.Parameter;
import jcla.type.ResolvableType;
import jcla.util.Resolvable;
import jcla.util.data.BitArray;

import java.util.HashSet;
import java.util.Set;

import static jcla.Modifier.*;

/**
 * @author link
 */
public final class ParameterDefinition extends Definition {

	private final Resolvable<ClassDefinition> type;

	/**
	 * Creates a new ParameterDefinition from the given Parameter struct (from ClassFile).
	 *
	 * @param parameter the Parameter
	 */
	public ParameterDefinition(Parameter parameter) {
		super(getModifiers(parameter.getAccessFlags()), Types.qualifyType(parameter.getName().getValue()));
		type = new ResolvableType(name);
	}

	/**
	 * Creates a new ParameterDefinition from the given Parameter (Reflection API).
	 *
	 * @param parameter the Parameter
	 */
	public ParameterDefinition(java.lang.reflect.Parameter parameter) {
		this(new ResolvableType(parameter.getType()), getModifiers(new BitArray(parameter.getModifiers())), parameter.getName());
	}

	/**
	 * Creates a new ParameterDefinition with the given type and fully-qualified type name.
	 *
	 * @param type the type of this ParameterDefinition
	 * @param modifiers the access flags of this ParameterDefinition
	 * @param name the name of this ParameterDefinition
	 */
	public ParameterDefinition(Resolvable<ClassDefinition> type, Set<Modifier> modifiers, String name) {
		super(modifiers, name);
		this.type = type;
	}

	public Resolvable<ClassDefinition> getType() {
		return type;
	}

	private static Set<Modifier> getModifiers(BitArray accessflags) {
		Set<Modifier> modifiers = new HashSet<>(1);
		if (accessflags.get(FINAL.getBit()))
			modifiers.add(FINAL);
		if(accessflags.get(SYNTHETIC.getBit()))
			modifiers.add(SYNTHETIC);
		if(accessflags.get(MANDATED.getBit()))
			modifiers.add(MANDATED);

		return modifiers;
	}

}
