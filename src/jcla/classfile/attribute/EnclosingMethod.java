package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.constant.Constant;
import jcla.classfile.struct.AttributeInfo;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public final class EnclosingMethod extends AttributeInfo {

	private final int classIndex;
	private final int methodIndex;

	public EnclosingMethod(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		classIndex = in.readUnsignedShort();
		methodIndex = in.readUnsignedShort();
	}

	public EnclosingMethod(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		classIndex = in.readUnsignedShort();
		methodIndex = in.readUnsignedShort();
	}

	public EnclosingMethod(int nameIndex, int length, int classIndex, int methodIndex, ConstantPool owner) {
		super(nameIndex, length, owner);
		this.classIndex = classIndex;
		this.methodIndex = methodIndex;
	}

	public Constant.Class getClassInfo() {
		return owner.getClass(classIndex);
	}

	public Constant.NameAndType getMethodInfo() {
		return owner.getNameAndType(methodIndex);
	}

	@Override
	public byte[] getInfo() {
		byte[] info = new byte[10];
		System.arraycopy(Data.toBytesShort(name), 0, info, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, info, 2, 4);
		System.arraycopy(Data.toBytesShort(classIndex), 0, info, 6, 2);
		System.arraycopy(Data.toBytesShort(methodIndex), 0, info, 8, 2);
		return info;
	}

}
