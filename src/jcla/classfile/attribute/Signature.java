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
public final class Signature extends AttributeInfo {

	private final int signature;

	public Signature(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		signature = in.readUnsignedShort();
	}

	public Signature(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		signature = in.readUnsignedShort();
	}

	public Signature(int nameIndex, int length, int signatureIndex, ConstantPool owner) {
		super(nameIndex, length, owner);
		signature = signatureIndex;
	}

	public Constant.UTF8 getSignature() {
		return owner.getUTF8(signature);
	}

	public int getSignatureIndex() {
		return signature;
	}

	@Override
	public byte[] getInfo() {
		byte[] info = new byte[8];
		System.arraycopy(Data.toBytesShort(name), 0, info, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, info, 2, 4);
		System.arraycopy(Data.toBytesShort(signature), 0, info, 6, 2);
		return info;
	}

}
