package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.AttributeInfo;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public class CustomAttribute extends AttributeInfo {

	protected final byte[] info;

	public CustomAttribute(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		info = new byte[length];
		for (int i = 0; i < length; i++) {
			info[i] = Data.ucastB(in.readUnsignedByte());
		}
	}

	public CustomAttribute(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		info = new byte[length];
		for (int i = 0; i < length; i++) {
			info[i] = Data.ucastB(in.readUnsignedByte());
		}
	}

	public CustomAttribute(int nameIndex, int length, byte[] info, ConstantPool owner) {
		super(nameIndex, length, owner);
		this.info = info;
	}

	@Override
	public byte[] getInfo() {
		return info;
	}

}
