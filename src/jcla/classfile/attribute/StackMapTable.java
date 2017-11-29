package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.AttributeInfo;
import jcla.classfile.union.StackMapFrame;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public final class StackMapTable extends AttributeInfo {

	private final StackMapFrame[] entries;

	public StackMapTable(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		entries = new StackMapFrame[in.readUnsignedShort()];
		for (int i = 0; i < entries.length; i++) {
			entries[i] = new StackMapFrame(in, owner);
		}
	}

	public StackMapTable(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		entries = new StackMapFrame[in.readUnsignedShort()];
		for (int i = 0; i < entries.length; i++) {
			entries[i] = new StackMapFrame(in, owner);
		}
	}

	public StackMapTable(int nameIndex, int length, StackMapFrame[] entries, ConstantPool owner) {
		super(nameIndex, length, owner);
		this.entries = entries;
	}

	public StackMapFrame[] getEntries() {
		return entries;
	}

	@Override
	public byte[] getInfo() {
		ByteArrayOutputStream info = new ByteArrayOutputStream(8 + entries.length * 4);
		DataOutputStream out = new DataOutputStream(info);
		try {
			out.writeShort(name);
			out.writeInt(length);
			out.writeShort(entries.length);
			for(StackMapFrame frame : entries)
				out.write(frame.toBytes());
		} catch (IOException e) {
			// ignore
		}

		return info.toByteArray();
	}

}
