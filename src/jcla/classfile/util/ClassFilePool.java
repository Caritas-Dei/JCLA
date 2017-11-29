package jcla.classfile.util;

import jcla.classfile.ClassFile;
import jcla.io.ClassInputStream;
import jcla.util.data.Pool;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A ClassFilePool that stores ClassFile objects
 *
 * @author link
 */
public class ClassFilePool implements Pool<String, ClassFile> {

	static final ClassFilePool CURRENT_VM = new ClassFilePool() {
		@Override
		public ClassFile get(String name) {
			ClassFile definition = definitions.get(name);

			// if no definition exists
			if (definition == null)
				try {
					// create a new definition and add to this class pool
					add(definition = new ClassFile(new ClassInputStream(name)));
				} catch( IOException e ) {
					// ignore
				}
			/*
			We ignore the IOException silently to return null
			if after attempting to load the original class file
			resource, it fails to load.
			 */

			return definition;
		}

	};

	public static final ClassFile CL_OBJECT_FILE = CURRENT_VM.get("java.lang.Object");

	/**
	 * Gets the ClassFilePool for the current Java Virtual Machine instance.
	 *
	 * @return the ClassFilePool for the current Java Virtual Machine
	 */
	public static ClassFilePool getLocal() {
		return CURRENT_VM;
	}

	protected final Map<String, ClassFile> definitions;
	protected final ClassFilePool          parent;

	public ClassFilePool() {
		this(CURRENT_VM);
	}

	public ClassFilePool(ClassFilePool parent) {
		definitions = new HashMap<>();
		this.parent = parent;
	}

	public ClassFilePool(ClassFilePool parent, List<ClassFile> classFiles) {
		definitions = new HashMap<>(classFiles.size());
		for (ClassFile classFile : classFiles) {
			definitions.putIfAbsent(classFile.getName().getValue(), classFile);
		}
		this.parent = parent;
	}

	@Override
	public ClassFilePool getParent() {
		return parent;
	}

	@Override
	public ClassFile get(String name) {
		return definitions.getOrDefault(name, parent.get(name));
	}

	public ClassFile get(Class<?> type) {
		return get(type.getTypeName());
	}

	@Override
	public void set(String name, ClassFile definition) {
		definitions.put(name, definition);
	}

	public void set(Class<?> type, ClassFile definition) {
		definitions.put(type.getTypeName(), definition);
	}

	@Override
	public void add(ClassFile definition) {
		definitions.putIfAbsent(definition.getName().getValue(), definition);
	}

	@Override
	public ClassFile remove(String name) {
		return definitions.remove(name);
	}

	@Override
	public void clear() {
		definitions.clear();
	}

	@Override
	public boolean contains(String name) {
		return definitions.containsKey(name);
	}

	public boolean contains(Class<?> type) {
		return definitions.containsKey(type.getTypeName());
	}

	public boolean contains(ClassFile definition) {
		return definitions.containsValue(definition);
	}

}
