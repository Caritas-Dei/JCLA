package jcla;

import jcla.classfile.util.ClassFilePool;
import jcla.type.ResolvableType;
import jcla.util.data.Pool;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author link
 */
public class ClassPool implements Pool<String, ClassDefinition> {

	static final ClassPool CURRENT_VM = new ClassPool() {
		@Override
		public ClassDefinition get(String name) {
			ClassDefinition definition = definitions.get(name);

			// if no definition exists
			if (definition == null)
				// create a new definition and add to this class pool
				add(definition = new ClassDefinition(ClassFilePool.getLocal().get(name)));

			return definition;
		}

	};

	public static final ClassDefinition CL_OBJECT  = CURRENT_VM.get("java.lang.Object");

	public static final ClassDefinition CL_BOOLEAN = new ClassDefinition(Collections.emptySet(), Collections.emptySet(), "boolean", null, new ResolvableType[0], Collections.emptyMap(), Collections.emptyMap());
	public static final ClassDefinition CL_CHAR    = new ClassDefinition(Collections.emptySet(), Collections.emptySet(), "char", null, new ResolvableType[0], Collections.emptyMap(), Collections.emptyMap());
	public static final ClassDefinition CL_BYTE    = new ClassDefinition(Collections.emptySet(), Collections.emptySet(), "byte", null, new ResolvableType[0], Collections.emptyMap(), Collections.emptyMap());
	public static final ClassDefinition CL_SHORT   = new ClassDefinition(Collections.emptySet(), Collections.emptySet(), "short", null, new ResolvableType[0], Collections.emptyMap(), Collections.emptyMap());
	public static final ClassDefinition CL_INT     = new ClassDefinition(Collections.emptySet(), Collections.emptySet(), "int", null, new ResolvableType[0], Collections.emptyMap(), Collections.emptyMap());
	public static final ClassDefinition CL_LONG    = new ClassDefinition(Collections.emptySet(), Collections.emptySet(), "long", null, new ResolvableType[0], Collections.emptyMap(), Collections.emptyMap());
	public static final ClassDefinition CL_FLOAT   = new ClassDefinition(Collections.emptySet(), Collections.emptySet(), "float", null, new ResolvableType[0], Collections.emptyMap(), Collections.emptyMap());
	public static final ClassDefinition CL_DOUBLE  = new ClassDefinition(Collections.emptySet(), Collections.emptySet(), "double", null, new ResolvableType[0], Collections.emptyMap(), Collections.emptyMap());

	static {
		CURRENT_VM.add(CL_BOOLEAN);
		CURRENT_VM.add(CL_CHAR);
		CURRENT_VM.add(CL_BYTE);
		CURRENT_VM.add(CL_SHORT);
		CURRENT_VM.add(CL_INT);
		CURRENT_VM.add(CL_LONG);
		CURRENT_VM.add(CL_FLOAT);
		CURRENT_VM.add(CL_DOUBLE);

		ClassFilePool classFiles = ClassFilePool.getLocal();

		classFiles.set("boolean", null);
		classFiles.set("char", null);
		classFiles.set("byte", null);
		classFiles.set("short", null);
		classFiles.set("int", null);
		classFiles.set("long", null);
		classFiles.set("float", null);
		classFiles.set("double", null);
	}

	/**
	 * Gets the ClassPool for the current Java Virtual Machine instance.
	 *
	 * @return the ClassPool for the current Java Virtual Machine
	 */
	public static ClassPool getLocal() {
		return CURRENT_VM;
	}

	protected final Map<String, ClassDefinition> definitions;
	protected final ClassPool                    parent;

	public ClassPool() {
		this(CURRENT_VM);
	}

	public ClassPool(ClassPool parent) {
		definitions = new HashMap<>();
		this.parent = parent;
	}

	public ClassPool(ClassPool parent, List<ClassDefinition> classDefinitions) {
		definitions = new HashMap<>(classDefinitions.size());
		for (ClassDefinition classDefinition : classDefinitions) {
			definitions.putIfAbsent(classDefinition.getName(), classDefinition);
		}
		this.parent = parent;
	}

	@Override
	public ClassPool getParent() {
		return parent;
	}

	@Override
	public ClassDefinition get(String name) {
		return definitions.getOrDefault(name, parent.get(name));
	}

	public ClassDefinition get(Class<?> type) {
		return get(type.getTypeName());
	}

	@Override
	public void set(String name, ClassDefinition definition) {
		definitions.put(name, definition);
	}

	public void set(Class<?> clazz, ClassDefinition definition) {
		set(clazz.getTypeName(), definition);
	}

	@Override
	public void add(ClassDefinition definition) {
		definitions.putIfAbsent(definition.getName(), definition);
	}

	@Override
	public ClassDefinition remove(String name) {
		return definitions.remove(name);
	}

	public ClassDefinition remove(Class<?> clazz) {
		return remove(clazz.getTypeName());
	}

	@Override
	public void clear() {
		definitions.clear();
	}

	@Override
	public boolean contains(String name) {
		return definitions.containsKey(name);
	}

	public boolean contains(Class<?> clazz) {
		return contains(clazz.getTypeName());
	}

	public boolean contains(ClassDefinition definition) {
		return definitions.containsValue(definition);
	}

}
