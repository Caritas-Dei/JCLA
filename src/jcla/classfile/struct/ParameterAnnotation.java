package jcla.classfile.struct;

import jcla.classfile.ConstantPool;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public final class ParameterAnnotation {

	private final Annotation[] annotations;

	public ParameterAnnotation(DataInput in, ConstantPool owner) throws IOException {
		annotations = new Annotation[in.readUnsignedShort()];
		for (int i = 0; i < annotations.length; i++) {
			annotations[i] = new Annotation(in, owner);
		}
	}

	public Annotation[] getAnnotations() {
		return annotations;
	}

	public byte[] toBytes() {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream(4 + annotations.length * 2);
		DataOutputStream out = new DataOutputStream(bytes);
		for (Annotation annotation : annotations) {
			try {
				out.write(annotation.toBytes());
			} catch (IOException e) {
				// ignore
			}
		}

		return bytes.toByteArray();
	}

}
