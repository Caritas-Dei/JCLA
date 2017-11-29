package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.AttributeInfo;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

/**
 * A tag Attribute to declare that this class is synthetic.
 *
 * @author link
 */
public final class Synthetic extends AttributeInfo {

	public Synthetic(DataInput in, ConstantPool owner) throws IOException {
		super(in.readUnsignedShort(), 0, owner);
		// skip reading attribute length micro-optimization
		in.skipBytes(4);
	}

	public Synthetic(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		// skip reading attribute length micro-optimization
		in.skipBytes(4);
	}

	@Override
	public byte[] getInfo() {
		byte[] info = new byte[6];
		byte[] n = Data.toBytesShort(name);
		info[0] = n[0];
		info[1] = n[1];
		info[2] = 0;
		info[3] = 0;
		info[4] = 0;
		info[5] = 0;
		return info;
	}

}
