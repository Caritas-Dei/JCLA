package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.AttributeInfo;
import jcla.classfile.struct.ParameterAnnotation;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public final class RuntimeInvisibleParameterAnnotations extends AttributeInfo {

	private final ParameterAnnotation[] annotations;

	public RuntimeInvisibleParameterAnnotations(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		annotations = new ParameterAnnotation[in.readUnsignedByte()];
		for (int i = 0; i < annotations.length; i++) {
			annotations[i] = new ParameterAnnotation(in, owner);
		}
	}

	public RuntimeInvisibleParameterAnnotations(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		annotations = new ParameterAnnotation[in.readUnsignedByte()];
		for (int i = 0; i < annotations.length; i++) {
			annotations[i] = new ParameterAnnotation(in, owner);
		}
	}

	public ParameterAnnotation[] getAnnotations() {
		return annotations;
	}

	@Override
	public byte[] getInfo() {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream(8 + annotations.length * 4);
		DataOutputStream      out   = new DataOutputStream(bytes);

		try {
			out.writeShort(name);
			out.writeInt(length);
			out.writeShort(annotations.length);

			for(ParameterAnnotation annotation : annotations) {
				out.write(annotation.toBytes());
			}
		} catch (IOException e) {
			// ignore
		}

		return bytes.toByteArray();
	}

}
