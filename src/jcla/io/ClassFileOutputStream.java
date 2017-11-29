package jcla.io;

import jcla.classfile.ClassFile;
import jcla.classfile.ConstantPool;
import jcla.classfile.constant.Constant;
import jcla.classfile.constant.Constant.UTF8;
import jcla.classfile.constant.Type;
import jcla.classfile.struct.AttributeInfo;
import jcla.classfile.struct.MethodInfo;
import jcla.util.data.BitArray;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public final class ClassFileOutputStream extends FileOutputStream {

	public ClassFileOutputStream(File classFile) throws FileNotFoundException {
		super(classFile);
		if(!classFile.getName().endsWith(".class"))
			throw new IllegalArgumentException("The given file must be a class file: " + classFile.getName());
	}

	public void write(ClassFile classFile) throws IOException {
		DataOutputStream out = new DataOutputStream(this);
		// u4 magic
		out.writeInt(ClassFile.MAGIC);
		// u2 minor_version
		out.writeShort(classFile.getMinorVersion());
		// u2 major_version
		out.writeShort(classFile.getMajorVersion());
		// u2 constant_pool_count, cp_info constant_pool[constant_pool_count - 1]
		writeConstantPool(out, classFile.getConstantPool());
		// u2 access_flags
		writeAccessFlags(out, classFile.getAccessFlags());
		// u2 this_class
		out.writeShort(classFile.getName().getIndex());
		// u2 super_class
		out.writeShort(classFile.getSuperClass().getIndex());
		// u2 interfaces_count
		out.writeShort(classFile.getInterfaces().length);
		// u2 interfaces[interfaces_count]
		writeInterfaces(out, classFile.getInterfaces());
		// u2 methods_count
		out.writeShort(classFile.getMethods().length);
		// u2 methods[methods_count]
		writeMethods(out, classFile.getMethods());
	}

	private static void writeConstantPool(DataOutputStream out, ConstantPool constants) throws IOException {
		int constantPoolCount = constants.size();
		// u2 constant_pool_count
		out.writeShort(constantPoolCount + 1);
		// cp_info constant_pool[constant_pool_count - 1]
		for (int i = 0; i < constantPoolCount; i++) {
			writeConstant(out, constants.get(i));
		}
	}

	private static void writeConstant(DataOutputStream out, Constant constant) throws IOException {
		Type type = constant.getConstantType();
		out.writeShort(type.getID());

		switch(type) {
			case UTF8:
				out.writeChars(((Constant.UTF8) constant).getValue());
				break;
			case INTEGER:
				out.writeInt(((Constant.Integer) constant).getValue());
				break;
			case FLOAT:
				out.writeFloat(((Constant.Float) constant).getValue());
				break;
			case LONG:
				out.writeLong(((Constant.Long) constant).getValue());
				break;
			case DOUBLE:
				out.writeDouble(((Constant.Double) constant).getValue());
				break;
			case CLASS:
				out.writeShort(((Constant.Class) constant).getNameIndex());
				break;
			case STRING:
				out.writeShort(((Constant.String) constant).getDefinitionIndex());
				break;
			case FIELD_REF:
			case METHOD_REF:
			case INTERFACE_METHOD_REF:
			case NAME_AND_TYPE:
				out.writeShort(((Constant.NameAndType) constant).getNameIndex());
				out.writeShort(((Constant.NameAndType) constant).getDescriptorIndex());
				break;
			case METHOD_HANDLE:
				out.writeByte(((Constant.MethodHandle) constant).getReferenceKind());
				out.writeShort(((Constant.MethodHandle) constant).getReferenceIndex());
				break;
			case METHOD_TYPE:
				out.writeShort(((Constant.MethodType) constant).getDescriptorIndex());
				break;
			case INVOKE_DYNAMIC:
				out.writeShort(((Constant.InvokeDynamic) constant).getBootstrapMethodAttr());
				out.writeShort(((Constant.InvokeDynamic) constant).getDescriptorIndex());
			default:
				throw new IllegalArgumentException("Unsupported type: " + type);
		}
	}

	private static void writeAccessFlags(DataOutputStream out, BitArray access_flags) throws IOException {
		int accessflags = 0;

		for (int i = 0; i < 16; i++) {
			accessflags |= access_flags.get(i) ? 1 : 0;
		}

		out.writeShort(accessflags);
	}

	private static void writeInterfaces(DataOutputStream out, UTF8[] interfaces) throws IOException {
		for(UTF8 interface_ : interfaces) {
			out.writeShort(interface_.getIndex());
		}
	}

	private static void writeMethods(DataOutputStream out, MethodInfo[] methods) throws IOException {
		for (MethodInfo method : methods) {
			// u2 access_flags
			writeAccessFlags(out, method.getAccessFlags());
			// u2 name_index
			out.writeShort(method.getName().getIndex());
			// u2 descriptor_index
			out.writeShort(method.getDescriptor().getIndex());
			// u2 attributes_count
			AttributeInfo[] attributes = method.getAttributes();
			out.writeShort(attributes.length);
			// attribute_info attributes[attributes_count]
			for (AttributeInfo attribute : attributes) {
				writeAttribute(out, attribute);
			}
		}
	}

	private static void writeAttribute(DataOutputStream out, AttributeInfo attribute) throws IOException {
		// u2 attribute_name_index
		out.writeShort(attribute.getName().getIndex());
		// u4 attribute_length
		out.writeInt(attribute.getLength());
		// u1 info[attribute_length]
		out.write(attribute.getInfo());
	}

}
