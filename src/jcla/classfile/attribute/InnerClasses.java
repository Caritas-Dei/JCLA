package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.AttributeInfo;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public final class InnerClasses extends AttributeInfo {

	private final int[][] classes;

	public InnerClasses(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		classes = new int[in.readUnsignedShort()][4];
		for (int i = 0; i < classes.length; i++) {
			classes[i] = new int[]{in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort()};
		}
	}

	public InnerClasses(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		classes = new int[in.readUnsignedShort()][4];
		for (int i = 0; i < classes.length; i++) {
			classes[i] = new int[]{in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort()};
		}
	}

	public InnerClasses(int nameIndex, int length, int[][] innerClassStructs, ConstantPool owner) {
		super(nameIndex, length, owner);
		classes = innerClassStructs;
	}

	public int[] getInnerClass(int innerClass) {
		return classes[innerClass];
	}

	public int[][] getInnerClasses() {
		return classes;
	}

	@Override
	public byte[] getInfo() {
		byte[] info = new byte[8 + classes.length * 2];
		System.arraycopy(Data.toBytesShort(name), 0, info, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, info, 2, 4);
		System.arraycopy(Data.toBytesShort(classes.length), 0, info, 6, 2);
		for (int i = 0; i < classes.length; i++) {
			int[] row = classes[i];
			System.arraycopy(Data.toBytesShort(row[0]), 0, info, i, 2);
			System.arraycopy(Data.toBytesShort(row[1]), 0, info, i + 2, 2);
			System.arraycopy(Data.toBytesShort(row[2]), 0, info, i + 4, 2);
			System.arraycopy(Data.toBytesShort(row[3]), 0, info, i + 6, 2);
		}

		return info;
	}

}
