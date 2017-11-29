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
public final class ConstantValue<C extends Constant> extends AttributeInfo {

	private final int constantvalue;

	public ConstantValue(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		constantvalue = in.readUnsignedShort();
	}

	public ConstantValue(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		constantvalue = in.readUnsignedShort();
	}

	public ConstantValue(int nameIndex, int length, int constantvalueIndex, ConstantPool owner) {
		super(nameIndex, length, owner);
		constantvalue = constantvalueIndex;
	}

	@SuppressWarnings("unchecked")
	public C getConstantValue() {
		return (C) owner.get(constantvalue);
	}

	@Override
	public byte[] getInfo() {
		byte[] bytes = new byte[8];
		System.arraycopy(Data.toBytesShort(name), 0, bytes, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, bytes, 2, 4);
		System.arraycopy(Data.toBytesShort(constantvalue), 0, bytes, 6, 2);
		return bytes;
	}

}
