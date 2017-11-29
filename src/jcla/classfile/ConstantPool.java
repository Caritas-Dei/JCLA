package jcla.classfile;

import jcla.classfile.constant.Constant;
import jcla.classfile.constant.Constant.InvokeDynamic;
import jcla.classfile.constant.Constant.MethodType;
import jcla.classfile.constant.Constant.NameAndType;
import jcla.classfile.constant.Type;

import java.io.DataInput;
import java.io.IOException;

import static jcla.classfile.constant.Type.DOUBLE;

/**
 * @author link
 */
public final class ConstantPool {

	private final Constant[] constants;
	private final ClassFile  owner;

	public ConstantPool(DataInput classData, ClassFile owner) throws IOException {
		final int size = classData.readUnsignedShort();
		constants = new Constant[size];
		constants[0] = null;
		for (int i = 1; i < size; i++) {
			byte id = (byte) classData.readUnsignedByte();
			constants[i] = id > DOUBLE.getID() ? readConst(this, id, classData) : readConst(id, classData, i);
		}
		this.owner = owner;
	}

	public ConstantPool(Constant[] constants, ClassFile owner) {
		this.constants = constants;
		this.owner = owner;
	}

	//********************************************************************************
	//                    below: ldc (load constant) helper methods
	//********************************************************************************

	private static Constant readConst(byte id, DataInput classData, int index) throws IOException {
		switch (Type.get(id)) {
			case UTF8:
				return readUTF8(classData, index);
			case INTEGER:
				return readInteger(classData);
			case FLOAT:
				return readFloat(classData);
			case LONG:
				return readLong(classData);
			case DOUBLE:
				return readDouble(classData);
			default:
				throw new IllegalArgumentException("ConstantPool Path id mismatch: " + id + " does not match any known Constant Path");
		}
	}

	private static Constant readConst(ConstantPool owner, byte id, DataInput classData) throws IOException {
		switch (Type.get(id)) {
			case STRING:
				return readString(owner, classData);
			case CLASS:
				return readClass(owner, classData);
			case METHOD_TYPE:
				return readMethodType(owner, classData);
			case NAME_AND_TYPE:
				return readNameAndType(owner, classData);
			case METHOD_REF:
				return readMethodRef(owner, classData);
			case FIELD_REF:
				return readFieldRef(owner, classData);
			case METHOD_HANDLE:
				return readMethodHandle(owner, classData);
			case INTERFACE_METHOD_REF:
				return readInterfaceMethodRef(owner, classData);
			case INVOKE_DYNAMIC:
				return readInvokeDynamic(owner, classData);
			default:
				throw new IllegalArgumentException("ConstantPool Path id mismatch: " + id + " does not match any known Constant Path");
		}
	}

	private static Constant readUTF8(DataInput in, int index) throws IOException {
		return new Constant.UTF8(in.readUTF(), index);
	}

	private static Constant readInteger(DataInput in) throws IOException {
		return new Constant.Integer(in.readInt());
	}

	private static Constant readFloat(DataInput in) throws IOException {
		return new Constant.Float(in.readFloat());
	}

	private static Constant readLong(DataInput in) throws IOException {
		return new Constant.Long(in.readLong());
	}

	private static Constant readDouble(DataInput in) throws IOException {
		return new Constant.Double(in.readDouble());
	}

	private static Constant readString(ConstantPool owner, DataInput in) throws IOException {
		return new Constant.String(owner, in.readUnsignedShort());
	}

	private static Constant readClass(ConstantPool owner, DataInput in) throws IOException {
		return new Constant.Class(owner, in.readUnsignedShort());
	}

	private static Constant readMethodType(ConstantPool owner, DataInput in) throws IOException {
		return new Constant.MethodType(owner, in.readUnsignedShort());
	}

	private static Constant readNameAndType(ConstantPool owner, DataInput in) throws IOException {
		return new Constant.NameAndType(owner, in.readUnsignedShort(), in.readUnsignedShort());
	}

	private static Constant readMethodRef(ConstantPool owner, DataInput in) throws IOException {
		return new Constant.MethodRef(owner, in.readUnsignedShort(), in.readUnsignedShort());
	}

	private static Constant readFieldRef(ConstantPool owner, DataInput in) throws IOException {
		return new Constant.FieldRef(owner, in.readUnsignedShort(), in.readUnsignedShort());
	}

	private static Constant readMethodHandle(ConstantPool owner, DataInput in) throws IOException {
		return new Constant.MethodHandle(owner, in.readUnsignedByte(), in.readUnsignedShort());
	}

	private static Constant readInterfaceMethodRef(ConstantPool owner, DataInput in) throws IOException {
		return new Constant.InterfaceMethodRef(owner, in.readUnsignedShort(), in.readUnsignedShort());
	}

	private static Constant readInvokeDynamic(ConstantPool owner, DataInput in) throws IOException {
		return new Constant.InvokeDynamic(owner, in.readUnsignedShort(), in.readUnsignedShort());
	}

	//********************************************************************************
	//                    above: ldc (load constant) helper methods
	//********************************************************************************

	public int size() {
		return constants.length;
	}

	public Constant get(int index) {
		return constants[index];
	}

	public Constant.Integer getInteger(int index) {
		return (Constant.Integer) get(index);
	}

	public Constant.Float getFloat(int index) {
		return (Constant.Float) get(index);
	}

	public Constant.Long getLong(int index) {
		return (Constant.Long) get(index);
	}

	public Constant.Double getDouble(int index) {
		return (Constant.Double) get(index);
	}

	public Constant.String getString(int index) {
		return (Constant.String) get(index);
	}

	public MethodType getMethod(int index) {
		return (MethodType) get(index);
	}

	public Constant.Class getClass(int index) {
		return (Constant.Class) get(index);
	}

	public Constant.UTF8 getUTF8(int index) {
		return (Constant.UTF8) get(index);
	}

	public NameAndType getNameAndType(int index) {
		return (NameAndType) get(index);
	}

	public Constant.FieldRef getFieldRef(int index) {
		return (Constant.FieldRef) get(index);
	}

	public Constant.MethodRef getMethodRef(int index) {
		return (Constant.MethodRef) get(index);
	}

	public Constant.InterfaceMethodRef getInterfaceMethodRef(int index) {
		return (Constant.InterfaceMethodRef) get(index);
	}

	public InvokeDynamic getInvokeDynamic(int index) {
		return (InvokeDynamic) get(index);
	}

	public Constant.MethodHandle getMethodHandle(int index) {
		return (Constant.MethodHandle) get(index);
	}

	public ClassFile getOwner() {
		return owner;
	}
}
