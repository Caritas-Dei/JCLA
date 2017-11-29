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
public final class LocalVariableTypeTable extends AttributeInfo {

	private final Entry[] entries;

	public LocalVariableTypeTable(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		entries = new Entry[in.readUnsignedShort()];
		for (int i = 0; i < entries.length; i++) {
			entries[i] = new Entry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
		}
	}

	public LocalVariableTypeTable(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		entries = new Entry[in.readUnsignedShort()];
		for (int i = 0; i < entries.length; i++) {
			entries[i] = new Entry(in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort(), in.readUnsignedShort());
		}
	}

	public LocalVariableTypeTable(int nameIndex, int length, Entry[] entries, ConstantPool owner) {
		super(nameIndex, length, owner);
		this.entries = entries;
	}

	public Entry[] getEntries() {
		return entries;
	}

	@Override
	public byte[] getInfo() {
		byte[] info = new byte[8 + entries.length * 10];
		System.arraycopy(Data.toBytesShort(name), 0, info, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, info, 2, 4);
		System.arraycopy(Data.toBytesShort(entries.length), 0, info, 6, 2);

		for (int i = 8, j = 0; i < info.length; i += 10, j++) {
			System.arraycopy(entries[j].toBytes(), 0, info, i, 10);
		}

		return info;
	}

	public final class Entry {

		private final int startPC;
		private final int length;
		private final int name;
		private final int signature;
		private final int index;

		public Entry(int startPC, int length, int nameIndex, int signatureIndex, int index) {
			this.startPC = startPC;
			this.length = length;
			this.name = nameIndex;
			this.signature = signatureIndex;
			this.index = index;
		}

		public int getStartPC() {
			return startPC;
		}

		public int getLength() {
			return length;
		}

		public Constant.UTF8 getName() {
			return owner.getUTF8(name);
		}

		public Constant.UTF8 getSignature() {
			return owner.getUTF8(signature);
		}

		public int getFrameIndex() {
			return index;
		}

		public byte[] toBytes() {
			byte[] bytes = new byte[10];
			System.arraycopy(Data.toBytesShort(startPC), 0, bytes, 0, 2);
			System.arraycopy(Data.toBytesShort(length), 0, bytes, 2, 2);
			System.arraycopy(Data.toBytesShort(name), 0, bytes, 4, 2);
			System.arraycopy(Data.toBytesShort(signature), 0, bytes, 6, 2);
			System.arraycopy(Data.toBytesShort(index), 0, bytes, 8, 2);
			return bytes;
		}

	}

}
