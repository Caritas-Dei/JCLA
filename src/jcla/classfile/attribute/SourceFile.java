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
public final class SourceFile extends AttributeInfo {

	private final int sourcefile;

	public SourceFile(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		sourcefile = in.readUnsignedShort();
	}

	public SourceFile(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		sourcefile = in.readUnsignedShort();
	}

	public SourceFile(int nameIndex, int length, int sourceFileIndex, ConstantPool owner) {
		super(nameIndex, length, owner);
		sourcefile = sourceFileIndex;
	}

	public Constant.UTF8 getFileName() {
		return owner.getUTF8(sourcefile);
	}

	public int getSourceFileIndex() {
		return sourcefile;
	}

	@Override
	public byte[] getInfo() {
		byte[] info = new byte[8];
		System.arraycopy(Data.toBytesShort(name), 0, info, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, info, 2, 4);
		System.arraycopy(Data.toBytesShort(sourcefile), 0, info, 6, 2);
		return info;
	}

}
