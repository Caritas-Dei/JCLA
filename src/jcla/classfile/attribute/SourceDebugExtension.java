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
public final class SourceDebugExtension extends AttributeInfo {

	private final String data;

	public SourceDebugExtension(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		// the spool
		StringBuilder string = new StringBuilder(length);
		// read UTF8 chars manually
		for (int i = 0; i < length; i++) {
			string.append((char) in.readUnsignedByte());
		}

		data = string.toString();
	}

	public SourceDebugExtension(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		// the spool
		StringBuilder string = new StringBuilder(length);
		// read UTF8 chars manually
		for (int i = 0; i < length; i++) {
			string.append((char) in.readUnsignedByte());
		}

		data = string.toString();
	}

	public String getData() {
		return data;
	}

	@Override
	public byte[] getInfo() {
		ByteArrayOutputStream info = new ByteArrayOutputStream(6 + data.length());
		DataOutputStream      out  = new DataOutputStream(info);
		try {
			out.writeShort(name);
			out.writeInt(length);
			out.writeUTF(data);
		} catch(IOException e){
			// ignore
		}

		return info.toByteArray();
	}

}
