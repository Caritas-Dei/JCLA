package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.AttributeInfo;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public final class LineNumberTable extends AttributeInfo {

	private final Entry[] lineNumberTable;

	public LineNumberTable(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		lineNumberTable = new Entry[in.readUnsignedShort()];
		for (int i = 0; i < length; i++) {
			lineNumberTable[i] = new Entry(in.readUnsignedShort(), in.readUnsignedShort());
		}
	}

	public LineNumberTable(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		lineNumberTable = new Entry[in.readUnsignedShort()];
		for (int i = 0; i < lineNumberTable.length; i++) {
			lineNumberTable[i] = new Entry(in.readUnsignedShort(), in.readUnsignedShort());
		}
	}

	public LineNumberTable(int nameIndex, int length, Entry[] entries, ConstantPool owner) {
		super(nameIndex, length, owner);
		lineNumberTable = entries;
	}

	public Entry getEntry(int index) {
		return lineNumberTable[index];
	}

	public Entry[] getEntries() {
		return lineNumberTable;
	}

	@Override
	public byte[] getInfo() {
		byte[] info = new byte[8 + lineNumberTable.length * 4];
		System.arraycopy(Data.toBytesShort(name), 0, info, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, info, 2, 4);
		System.arraycopy(Data.toBytesShort(lineNumberTable.length), 0, info, 6, 2);

		for(int i = 0, j = 0; i < info.length; i += 4, j++) {
			System.arraycopy(lineNumberTable[j].toBytes(), 0, info, i, 4);
		}

		return info;
	}

	public static final class Entry {

		private final int startPC;
		private final int lineNumber;

		public Entry(int startPC, int lineNumber) {
			this.startPC = startPC;
			this.lineNumber = lineNumber;
		}

		public int getStartPC() {
			return startPC;
		}

		public int getLineNumber() {
			return lineNumber;
		}

		public byte[] toBytes() {
			byte[] bytes = new byte[4];
			System.arraycopy(Data.toBytesShort(startPC), 0, bytes, 0, 2);
			System.arraycopy(Data.toBytesShort(lineNumber), 0, bytes, 2, 2);
			return bytes;
		}

	}

}
