package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.constant.Constant;
import jcla.classfile.struct.AttributeInfo;
import jcla.util.io.Data;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public final class BootstrapMethods extends AttributeInfo {

	private final BootstrapMethod[] bootstrapMethods;

	public BootstrapMethods(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		bootstrapMethods = new BootstrapMethod[in.readUnsignedShort()];
		for (int i = 0; i < bootstrapMethods.length; i++) {
			bootstrapMethods[i] = new BootstrapMethod(in, owner);
		}
	}

	public BootstrapMethods(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		bootstrapMethods = new BootstrapMethod[in.readUnsignedShort()];
		for (int i = 0; i < bootstrapMethods.length; i++) {
			bootstrapMethods[i] = new BootstrapMethod(in, owner);
		}
	}

	public BootstrapMethod[] getBootstrapMethods() {
		return bootstrapMethods;
	}

	@Override
	public byte[] getInfo() {
		try {
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream(8 + bootstrapMethods.length);
			DataOutputStream      bytes     = new DataOutputStream(byteArray);
			bytes.writeShort(name);
			bytes.writeInt(length);
			bytes.writeShort(bootstrapMethods.length);
			for (BootstrapMethod method : bootstrapMethods)
				bytes.write(method.toBytes());

			return byteArray.toByteArray();
		} catch (IOException e) {
			return new byte[0];
		}
	}

	public static final class BootstrapMethod {

		private final int          methodRef;
		private final int[]        bootstrapArguments;
		private final ConstantPool owner;

		public BootstrapMethod(DataInput in, ConstantPool owner) throws IOException {
			methodRef = in.readUnsignedShort();
			bootstrapArguments = new int[in.readUnsignedShort()];
			for (int i = 0; i < bootstrapArguments.length; i++) {
				bootstrapArguments[i] = in.readUnsignedShort();
			}
			this.owner = owner;
		}

		public BootstrapMethod(int methodRef, int[] bootstrapArguments, ConstantPool owner) {
			this.methodRef = methodRef;
			this.bootstrapArguments = bootstrapArguments;
			this.owner = owner;
		}

		public Constant.MethodRef getMethodRef() {
			return owner.getMethodRef(methodRef);
		}

		public int[] getBootstrapArgumentIndices() {
			return bootstrapArguments;
		}

		public Constant[] getBootstrapArguments() {
			Constant[] constants = new Constant[bootstrapArguments.length];
			for (int i = 0; i < constants.length; i++) {
				constants[i] = owner.get(bootstrapArguments[i]);
			}
			return constants;
		}

		public byte[] toBytes() {
			byte[] bytes = new byte[4 + bootstrapArguments.length * 4];
			System.arraycopy(Data.toBytesInt(methodRef), 0, bytes, 0, 4);
			for (int i = 0; i < bytes.length; i++) {
				System.arraycopy(Data.toBytesInt(bootstrapArguments[i]), 0, bytes, i * 4, 4);
			}
			return bytes;
		}

	}

}
