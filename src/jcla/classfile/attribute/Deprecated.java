package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.AttributeInfo;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public final class Deprecated extends AttributeInfo {

	public Deprecated(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
	}

	public Deprecated(int nameIndex, int length, ConstantPool owner) {
		super(nameIndex, length, owner);
	}

	@Override
	public byte[] getInfo() {
		byte[] info = new byte[6];
		System.arraycopy(Data.toBytesShort(name), 0, info, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, info, 2, 4);
		return info;
	}

}
