package jcla.classfile;

import jcla.classfile.attribute.Attributes;
import jcla.classfile.constant.Constant;
import jcla.classfile.struct.AttributeInfo;
import jcla.classfile.struct.FieldInfo;
import jcla.classfile.struct.MethodInfo;
import jcla.error.ClassMagicInvalidException;
import jcla.io.ClassInputStream;
import jcla.util.data.BitArray;

import java.io.DataInput;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents a ClassFile struct as defined by <a href="http://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html#jvms-4.1">The {@code class} File Format</a>:
 * <pre>
 *     ClassFile {
 *          u4             magic;
 *          u2             minor_version;
 *          u2             major_version;
 *          u2             constant_pool_count;
 *          cp_info        constant_pool[constant_pool_count-1];
 *          u2             access_flags;
 *          u2             this_class;
 *          u2             super_class;
 *          u2             interfaces_count;
 *          u2             interfaces[interfaces_count];
 *          u2             fields_count;
 *          field_info     fields[fields_count];
 *          u2             methods_count;
 *          method_info    methods[methods_count];
 *          u2             attributes_count;
 *          attribute_info attributes[attributes_count];
 *      }
 * </pre>
 *
 * @author link
 */
public final class ClassFile {

	public static final int MAGIC = 0xCAFEBABE;

	private final int magic;
	private final int major, minor;
	private final ConstantPool            constantPool;
	private final BitArray                accessflags;
	private final Constant.UTF8           name;
	private final Constant.UTF8           superclass;
	private final Constant.UTF8[]         interfaces;
	private final Map<String, FieldInfo>  fields;
	private final Map<String, MethodInfo> methods;
	private final AttributeInfo[]         attributes;

	public ClassFile(ClassInputStream data) throws IOException {
		// class file magic
		magic = data.readInt();
		// commence debug printing
		if (magic != MAGIC)
			throw new ClassMagicInvalidException(magic);
		// the bytecode minor version
		minor = data.readUnsignedShort();
		// the bytecode major version
		major = data.readUnsignedShort();
		// load cp_info struct
		constantPool = new ConstantPool(data, this);
		// class access flags
		accessflags = new BitArray(data.readUnsignedShort());
		// class name
		name = constantPool.getClass(data.readUnsignedShort()).getName();
		// superclass name
		int sup = data.readUnsignedShort();
		superclass = sup == 0 ? null : constantPool.getClass(sup).getName();
		// interfaces that this class implements
		interfaces = readInterfaces(data.readUnsignedShort(), data, constantPool);
		// fields defined by this class
		fields = readFields(data.readUnsignedShort(), data, constantPool);
		// methods defined by this class
		methods = readMethods(data.readUnsignedShort(), data, constantPool);
		// attributes defined by this class
		attributes = Attributes.load(data, constantPool);
	}

	public ClassFile(int major, int minor, ConstantPool constantPool, int accessflags, Constant.UTF8 name, Constant.UTF8 superclass, Constant.UTF8[] interfaces, Map<String, FieldInfo> fields, Map<String, MethodInfo> methods, AttributeInfo[] attributes) {
		magic = MAGIC;
		this.major = major;
		this.minor = minor;
		this.constantPool = constantPool;
		this.accessflags = new BitArray(accessflags);
		this.name = name;
		this.superclass = superclass;
		this.interfaces = interfaces;
		this.fields = fields;
		this.methods = methods;
		this.attributes = attributes;
	}


	private static Constant.UTF8[] readInterfaces(int count, DataInput in, ConstantPool owner) throws IOException {
		final Constant.UTF8[] interfaces = new Constant.UTF8[count];

		for (Constant.UTF8 i : interfaces) {
			i = owner.getClass(in.readUnsignedShort()).getName();
		}

		return interfaces;
	}

	private static Map<String, FieldInfo> readFields(int count, DataInput in, ConstantPool owner) throws IOException {
		Map<String, FieldInfo> fields = new HashMap<>(count);
		for (int i = 0; i < count; i++) {
			FieldInfo field = new FieldInfo(in, owner);
			fields.put(field.getName().getValue(), field);
		}
		return fields;
	}

	private static Map<String, MethodInfo> readMethods(int count, DataInput in, ConstantPool owner) throws IOException {
		Map<String, MethodInfo> methods = new HashMap<>(count);
		for (int i = 0; i < count; i++) {
			MethodInfo method = new MethodInfo(in, owner);
			methods.put(method.getName().getValue(), method);
		}
		return methods;
	}

	public int getMagic() {
		return magic;
	}

	public int getMajorVersion() {
		return major;
	}

	public int getMinorVersion() {
		return minor;
	}

	public ConstantPool getConstantPool() {
		return constantPool;
	}

	public BitArray getAccessFlags() {
		return accessflags;
	}

	public Constant.UTF8 getName() {
		return name;
	}

	public Constant.UTF8 getSuperClass() {
		return superclass;
	}

	public Constant.UTF8[] getInterfaces() {
		return interfaces;
	}

	public FieldInfo[] getFields() {
		return fields.values().toArray(new FieldInfo[0]);
	}

	public FieldInfo getField(String name) {
		return fields.get(name);
	}

	public MethodInfo[] getMethods() {
		return methods.values().toArray(new MethodInfo[0]);
	}

	public MethodInfo getMethod(String name) {
		return methods.get(name);
	}

	public AttributeInfo[] getAttributes() {
		return attributes;
	}

	@SuppressWarnings("unchecked")
	public <A extends AttributeInfo> A getAttribute(Class<A> type) {
		A attribute = null;

		for (AttributeInfo a : attributes) {
			if (a.getClass() == type) {
				attribute = (A) a;
				break;
			}
		}

		return attribute;
	}

}
