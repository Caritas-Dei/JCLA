package jcla.classfile.constant;

import jcla.classfile.ConstantPool;
import jcla.util.io.Data;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public abstract class Constant {

	protected final Type type;

	protected Constant(Type type) {
		this.type = type;
	}

	public final Type getConstantType() {
		return type;
	}

	public abstract byte[] toBytes();

	public static abstract class Primitive extends Constant {

		Primitive(Type type) {
			super(type);
		}

	}

	public static abstract class Pointer extends Constant {

		protected final ConstantPool owner;

		Pointer(Type type, ConstantPool owner) {
			super(type);
			this.owner = owner;
		}

		ConstantPool getOwner() {
			return owner;
		}

	}

	public static final class Integer extends Primitive {

		private final int value;

		public Integer(int value) {
			super(Type.INTEGER);
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesInt(value);
		}

		@Override
		public java.lang.String toString() {
			return "Constant.Integer[value: " + value + "]";
		}

	}

	public static final class Float extends Primitive {

		private final float value;

		public Float(float value) {
			super(Type.FLOAT);
			this.value = value;
		}

		public float getValue() {
			return value;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesFloat(value);
		}

		@Override
		public java.lang.String toString() {
			return "Constant.Float[value: " + value + "]";
		}

	}

	public static final class Long extends Primitive {

		private final long value;

		public Long(java.lang.Long value) {
			super(Type.LONG);
			this.value = value;
		}

		public long getValue() {
			return value;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesLong(value);
		}
		@Override
		public java.lang.String toString() {
			return "Constant.Long[value: " + value + "]";
		}


	}

	public static final class Double extends Primitive {

		private final double value;

		public Double(double value) {
			super(Type.DOUBLE);
			this.value = value;
		}

		public double getValue() {
			return value;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesDouble(value);
		}

		@Override
		public java.lang.String toString() {
			return "Constant.Double[value: " + value + "]";
		}

	}

	public static final class String extends Pointer {

		private final int definition;

		public String(ConstantPool owner, int utf8Index) {
			super(Type.STRING, owner);
			definition = utf8Index;
		}

		public UTF8 getDefinition() {
			return owner.getUTF8(definition);
		}

		public int getDefinitionIndex() {
			return definition;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesShort(definition);
		}

		@Override
		public java.lang.String toString() {
			return "Constant.String[definition: " + getDefinition() + "]";
		}

	}

	public static final class MethodType extends Pointer {

		private final int descriptor;

		public MethodType(ConstantPool owner, int descriptorIndex) {
			super(Type.METHOD_TYPE, owner);
			descriptor = descriptorIndex;
		}

		public UTF8 getDescriptor() {
			return owner.getUTF8(descriptor);
		}

		public int getDescriptorIndex() {
			return descriptor;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesShort(descriptor);
		}

		@Override
		public java.lang.String toString() {
			return "Constant.MethodType[descriptor: " + getDescriptor() + "]";
		}

	}

	public static final class Class extends Pointer {

		private final int name;

		public Class(ConstantPool owner, int nameIndex) {
			super(Type.CLASS, owner);
			name = nameIndex;
		}

		public UTF8 getName() {
			return owner.getUTF8(name);
		}

		public int getNameIndex() {
			return name;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesShort(name);
		}

		@Override
		public java.lang.String toString() {
			return "Constant.Class[name: \"" + getName() + "\"]";
		}

	}

	public static final class UTF8 extends Constant {

		private final java.lang.String value;
		private final int              cpindex;

		public UTF8(java.lang.String value, int cpindex) {
			super(Type.UTF8);
			this.value = value;
			this.cpindex = cpindex;
		}

		public java.lang.String getValue() {
			return value;
		}

		public int getIndex() {
			return cpindex;
		}

		@Override
		public byte[] toBytes() {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream(value.length());
			DataOutputStream      out   = new DataOutputStream(bytes);
			try {
				out.writeUTF(value);
			} catch (IOException e) {
				// ignore
			}
			return bytes.toByteArray();
		}

		@Override
		public java.lang.String toString() {
			return "Constant.UTF8[value: \"" + value + "\";index: " + cpindex + "]";
		}

	}

	public static final class NameAndType extends Pointer {

		private final int name, descriptor;

		public NameAndType(ConstantPool owner, int nameIndex, int descriptorIndex) {
			super(Type.NAME_AND_TYPE, owner);
			name = nameIndex;
			descriptor = descriptorIndex;
		}

		public UTF8 getName() {
			return owner.getUTF8(name);
		}

		public int getNameIndex() {
			return name;
		}

		public UTF8 getDescriptor() {
			return owner.getUTF8(descriptor);
		}

		public int getDescriptorIndex() {
			return descriptor;
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[4];
			System.arraycopy(Data.toBytesShort(name), 0, bytes, 0, 2);
			System.arraycopy(Data.toBytesShort(descriptor), 0, bytes, 2, 2);
			return bytes;
		}

		@Override
		public java.lang.String toString() {
			return "Constant.NameAndType[name: \"" + name + "\";type: \"" + type + "\"]";
		}

	}

	public static final class FieldRef extends Pointer {

		private final int clazz, descriptor;

		public FieldRef(ConstantPool owner, int classIndex, int descriptorIndex) {
			super(Type.FIELD_REF, owner);
			clazz = classIndex;
			descriptor = descriptorIndex;
		}

		public Class getClassConst() {
			return owner.getClass(clazz);
		}

		public int getClassConstIndex() {
			return clazz;
		}

		public NameAndType getDescriptor() {
			return owner.getNameAndType(descriptor);
		}

		public int getDescriptorIndex() {
			return descriptor;
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[4];
			System.arraycopy(Data.toBytesShort(clazz), 0, bytes, 0, 2);
			System.arraycopy(Data.toBytesShort(descriptor), 0, bytes, 2, 2);
			return bytes;
		}

		@Override
		public java.lang.String toString() {
			return "Constant.FieldRef[class: " + getClassConst() + ";descriptor: " + getDescriptor() + "]";
		}

	}

	public static final class MethodRef extends Pointer {

		private final int clazz, descriptor;

		public MethodRef(ConstantPool owner, int classIndex, int descriptorIndex) {
			super(Type.METHOD_REF, owner);
			clazz = classIndex;
			descriptor = descriptorIndex;
		}

		public Class getClassConst() {
			return owner.getClass(clazz);
		}

		public int getClassConstIndex() {
			return clazz;
		}

		public NameAndType getDescriptor() {
			return owner.getNameAndType(descriptor);
		}

		public int getDescriptorIndex() {
			return descriptor;
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[4];
			System.arraycopy(Data.toBytesShort(clazz), 0, bytes, 0, 2);
			System.arraycopy(Data.toBytesShort(descriptor), 0, bytes, 2, 2);
			return bytes;
		}

		@Override
		public java.lang.String toString() {
			return "Constant.MethodRef[class: " + getClassConst() + ";descriptor: " + getDescriptor() + "]";
		}

	}

	public static final class InterfaceMethodRef extends Pointer {

		private final int clazz, descriptor;

		public InterfaceMethodRef(ConstantPool owner, int classIndex, int descriptorIndex) {
			super(Type.INTERFACE_METHOD_REF, owner);
			clazz = classIndex;
			descriptor = descriptorIndex;
		}

		public Class getClassConst() {
			return owner.getClass(clazz);
		}

		public int getClassConstIndex() {
			return clazz;
		}

		public NameAndType getDescriptor() {
			return owner.getNameAndType(descriptor);
		}

		public int getDescriptorIndex() {
			return descriptor;
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[4];
			System.arraycopy(Data.toBytesShort(clazz), 0, bytes, 0, 2);
			System.arraycopy(Data.toBytesShort(descriptor), 0, bytes, 2, 2);
			return bytes;
		}

		@Override
		public java.lang.String toString() {
			return "Constant.InterfaceMethodRef[class: " + getClassConst() + ";descriptor: " + getDescriptor() + "]";
		}

	}

	public static final class InvokeDynamic extends Pointer {

		private final int bootstrapMethodAttr, descriptor;

		public InvokeDynamic(ConstantPool owner, int bootstrapMethodAttrIndex, int descriptorIndex) {
			super(Type.INVOKE_DYNAMIC, owner);
			bootstrapMethodAttr = bootstrapMethodAttrIndex;
			descriptor = descriptorIndex;
		}

		public int getBootstrapMethodAttr() {
			return bootstrapMethodAttr;
		}

		public NameAndType getDescriptor() {
			return owner.getNameAndType(descriptor);
		}

		public int getDescriptorIndex() {
			return descriptor;
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[4];
			System.arraycopy(Data.toBytesShort(bootstrapMethodAttr), 0, bytes, 0, 2);
			System.arraycopy(Data.toBytesShort(descriptor), 0, bytes, 2, 2);
			return bytes;
		}

		@Override
		public java.lang.String toString() {
			return "Constant.InvokeDynamic[bootstrapMethodAttr: {\n" + bootstrapMethodAttr + "\n};descriptor: " + getDescriptor() + "]";
		}

	}

	public static final class MethodHandle extends Pointer {

		private final int kind, index;

		public MethodHandle(ConstantPool owner, int referenceKind, int referenceIndex) {
			super(Type.METHOD_HANDLE, owner);
			kind = referenceKind;
			index = referenceIndex;
		}

		public int getReferenceKind() {
			return kind;
		}

		public MethodRef getMethodRef() {
			return owner.getMethodRef(index);
		}

		public int getReferenceIndex() {
			return index;
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[4];
			System.arraycopy(Data.toBytesShort(kind), 0, bytes, 0, 2);
			System.arraycopy(Data.toBytesShort(index), 0, bytes, 2, 2);
			return bytes;
		}

		@Override
		public java.lang.String toString() {
			return "Constant.MethodHandle[kind: " + getReferenceKind() + ";index: " + index + "]";
		}

	}

}
