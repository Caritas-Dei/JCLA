package jcla.classfile.attribute;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.AttributeInfo;
import jcla.classfile.union.ElementValue;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public final class AnnotationDefault extends AttributeInfo {

	private final ElementValue defaultValue;

	public AnnotationDefault(DataInput in, ConstantPool owner) throws IOException {
		super(in, owner);
		defaultValue = ElementValue.readElementValue(in, owner);
	}

	public AnnotationDefault(int nameIndex, int length, DataInput in, ConstantPool owner) throws IOException {
		super(nameIndex, length, owner);
		defaultValue = ElementValue.readElementValue(in, owner);
	}

	public AnnotationDefault(int nameIndex, int length, ElementValue defaultValue, ConstantPool owner) {
		super(nameIndex, length, owner);
		this.defaultValue = defaultValue;
	}

	public ElementValue getDefaultValue() {
		return defaultValue;
	}

	@Override
	public byte[] getInfo() {
		byte[] defaultValue = this.defaultValue.toBytes();
		byte[] annotationDefault = new byte[6 + defaultValue.length];
		System.arraycopy(Data.toBytesShort(name), 0, annotationDefault, 0, 2);
		System.arraycopy(Data.toBytesInt(length), 0, annotationDefault, 2, 4);
		System.arraycopy(defaultValue, 0, annotationDefault, 6, defaultValue.length);
		return annotationDefault;
	}

}
