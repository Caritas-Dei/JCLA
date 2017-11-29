package jcla;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author link
 */
public enum Methods {
	;

	public static List<MethodDefinition> extract(Class<?> clazz) {
		List<MethodDefinition> methods = new ArrayList<>(clazz.getDeclaredMethods().length);

		for(Method method : clazz.getDeclaredMethods()) {
			methods.add(new MethodDefinition(method));
		}

		return methods;
	}

}
