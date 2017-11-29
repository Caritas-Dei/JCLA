package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.Annotation;
import jcla.classfile.struct.AttributeInfo;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public final class RuntimeVisibleAnnotations extends AttributeInfo {

	private final Annotation[] annotations;

	public RuntimeVisibleAnnotations(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		annotations = new Annotation[in.readUnsignedShort()];
		for (int i = 0; i < annotations.length; i++) {
			annotations[i] = new Annotation(in, owner);
		}
	}

	public RuntimeVisibleAnnotations(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		annotations = new Annotation[in.readUnsignedShort()];
		for (int i = 0; i < annotations.length; i++) {
			annotations[i] = new Annotation(in, owner);
		}
	}

	public RuntimeVisibleAnnotations(int nameIndex, int length, Annotation[] annotations, ConstantPool owner) {
		super(nameIndex, length, owner);
		this.annotations = annotations;
	}

	public Annotation[] getAnnotations() {
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

			for(Annotation annotation : annotations) {
				out.write(annotation.toBytes());
			}
		} catch (IOException e) {
			// ignore
		}

		return bytes.toByteArray();
	}

}
