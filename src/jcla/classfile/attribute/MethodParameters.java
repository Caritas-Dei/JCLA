package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.constant.Constant;
import jcla.classfile.struct.AttributeInfo;
import jcla.util.data.BitArray;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;
import java.util.BitSet;

/**
 * @author link
 */
public final class MethodParameters extends AttributeInfo {

	private final Parameter[] parameters;

	public MethodParameters(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		parameters = new Parameter[in.readUnsignedShort()];
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = new Parameter(in, owner);
		}
	}

	public MethodParameters(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		parameters = new Parameter[in.readUnsignedShort()];
		for (int i = 0; i < parameters.length; i++) {
			parameters[i] = new Parameter(in, owner);
		}
	}

	public Parameter[] getParameters() {
		return parameters;
	}

	@Override
	public byte[] getInfo() {
		byte[] info = new byte[7 + parameters.length * 4];
		System.arraycopy(Data.toBytesShort(name), 0, info, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, info, 2, 4);
		info[6] = Data.ucastB(parameters.length);
		for (int i = 7, j = 0; i < info.length; i += 2, j++) {
			System.arraycopy(parameters[j].toBytes(), 0, info, i, 4);
		}

		return info;
	}

	public static final class Parameter {

		private final int          name;
		private final BitArray     accessflags;
		private final ConstantPool owner;

		public Parameter(DataInput in, ConstantPool owner) throws IOException {
			name = in.readUnsignedShort();
			accessflags = new BitArray(in.readUnsignedShort());
			this.owner = owner;
		}

		public Parameter(int nameIndex, BitArray accessflags, ConstantPool owner) {
			name = nameIndex;
			this.accessflags = accessflags;
			this.owner = owner;
		}

		public Constant.UTF8 getName() {
			return owner.getUTF8(name);
		}

		public BitArray getAccessFlags() {
			return accessflags;
		}

		public ConstantPool getOwner() {
			return owner;
		}

		public byte[] toBytes() {
			byte[] bytes = new byte[4];
			System.arraycopy(Data.toBytesShort(name), 0, bytes, 0, 2);
			System.arraycopy(accessflags.toByteArray(), 0, bytes, 2, 2);
			return bytes;
		}

	}

}
