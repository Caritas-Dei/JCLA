package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.AttributeInfo;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public final class Code extends AttributeInfo {

	private final int             maxstack;
	private final int             maxlocals;
	private final short[]         code;
	private final int[]           exceptiontable;
	private final AttributeInfo[] attributes;

	public Code(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		maxstack = in.readUnsignedShort();
		maxlocals = in.readUnsignedShort();
		// the bytecode of a method
		code = new short[in.readInt()];
		for (int i = 0; i < code.length; i++) {
			code[i] = (short) in.readUnsignedByte();
		}

		exceptiontable = new int[in.readUnsignedShort() * 4];
		for (int i = 0; i < exceptiontable.length; i++) {
			exceptiontable[i] = in.readUnsignedShort();
		}

		attributes = Attributes.load(in, owner);
	}

	public Code(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		maxstack = in.readUnsignedShort();
		maxlocals = in.readUnsignedShort();
		// the bytecode of a method
		code = new short[in.readInt()];
		for (int i = 0; i < code.length; i++) {
			code[i] = (short) in.readUnsignedByte();
		}

		exceptiontable = new int[in.readUnsignedShort() * 4];
		for (int i = 0; i < exceptiontable.length; i++) {
			exceptiontable[i] = in.readUnsignedShort();
		}

		attributes = Attributes.load(in, owner);
	}

	public Code(int nameIndex, int length, int maxstack, int maxlocals, short[] code, int[] exceptiontable, AttributeInfo[] attributes, ConstantPool owner) {
		super(nameIndex, length, owner);
		this.maxstack = maxstack;
		this.maxlocals = maxlocals;
		this.code = code;
		this.exceptiontable = exceptiontable;
		this.attributes = attributes;
	}

	public int getMaxStack() {
		return maxstack;
	}

	public int getMaxLocals() {
		return maxlocals;
	}

	public short[] getBytecode() {
		return code;
	}

	public int[] getExceptionTable() {
		return exceptiontable;
	}

	public AttributeInfo[] getAttributes() {
		return attributes;
	}

	@SuppressWarnings("unchecked")
	public <A extends AttributeInfo> A getAttribute(Class<A> type) {
		A attribute = null;

		for (AttributeInfo a : attributes) {
			if (a.getClass() == type) {
				attribute = (A) a;
				break;
			}
		}

		return attribute;
	}

	@Override
	public byte[] getInfo() {
		ByteArrayOutputStream info = new ByteArrayOutputStream(16 + exceptiontable.length * 2 + code.length + attributes.length * 6);
		DataOutputStream out = new DataOutputStream(info);
		try {
			// u2 attribute_name_index
			out.writeShort(name);
			// u4 attribute_length
			out.writeInt(length);
			// u2 max_stack
			out.writeShort(maxstack);
			// u2 max_locals
			out.writeShort(maxlocals);
			// u4 code_length
			out.writeInt(code.length);
			// u1 code[code_length]
			for (short codebyte : code)
				out.write(codebyte);
			// u2 exception_table_length
			out.writeShort(exceptiontable.length);
			// exception table (u8 entry)
			for (int exception : exceptiontable)
				out.writeShort(exception);
			// u2 attributes_count
			out.writeShort(attributes.length);
			// attribute_info attributes[attributes_count]
			for(AttributeInfo attribute : attributes)
				out.write(attribute.getInfo());
		} catch(IOException e) {
			// ignore
		}

		return info.toByteArray();
	}

}
