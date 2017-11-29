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
public final class Exceptions extends AttributeInfo {

	private final int[] exceptionIndexTable;

	public Exceptions(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		exceptionIndexTable = new int[in.readUnsignedShort()];
		for (int i = 0; i < exceptionIndexTable.length; i++) {
			exceptionIndexTable[i] = in.readUnsignedShort();
		}
	}

	public Exceptions(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		exceptionIndexTable = new int[in.readUnsignedShort()];
		for (int i = 0; i < exceptionIndexTable.length; i++) {
			exceptionIndexTable[i] = in.readUnsignedShort();
		}
	}

	public Exceptions(int nameIndex, int length, int[] exceptionIndexTable, ConstantPool owner) {
		super(nameIndex, length, owner);
		this.exceptionIndexTable = exceptionIndexTable;
	}

	public Constant.Class getException(int tableIndex) {
		return owner.getClass(exceptionIndexTable[tableIndex]);
	}

	public Constant.Class[] getExceptions() {
		Constant.Class[] exceptions = new Constant.Class[exceptionIndexTable.length];
		for (int i = 0; i < exceptions.length; i++) {
			exceptions[i] = getException(i);
		}
		return exceptions;
	}

	public int[] getIndices() {
		return exceptionIndexTable;
	}

	@Override
	public byte[] getInfo() {
		byte[] info = new byte[8 + exceptionIndexTable.length * 2];
		System.arraycopy(Data.toBytesShort(name), 0, info, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, info, 2, 4);
		System.arraycopy(Data.toBytesShort(exceptionIndexTable.length), 0, info, 6, 2);

		for (int i = 8, j = 0; j < exceptionIndexTable.length; i += 2, j++) {
			System.arraycopy(Data.toBytesShort(exceptionIndexTable[j]), 0, info, i, 2);
		}

		return info;
	}

}
