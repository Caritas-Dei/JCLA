package jcla.classfile.union;

import jcla.classfile.ConstantPool;
import jcla.classfile.struct.Annotation;
import jcla.error.ElementValuePairFormatException;
import jcla.util.io.Data;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public abstract class ElementValue {

	protected final char tag;
	protected final ConstantPool owner;

	private ElementValue(DataInput in, ConstantPool owner) throws IOException {
		tag = getCharASCII(in.readUnsignedByte());
		this.owner = owner;
	}

	private ElementValue(char tag, ConstantPool owner) {
		this.tag = tag;
		this.owner = owner;
	}

	public static ElementValue readElementValue(DataInput in, ConstantPool owner) throws IOException {
		char tag = getCharASCII(in.readUnsignedByte());
		switch(tag) {
			case 'B':
			case 'C':
			case 'D':
			case 'F':
			case 'I':
			case 'J':
			case 'S':
			case 'Z':
			case 's':
				return new Constant(tag, in.readUnsignedShort(), owner);
			case 'e':
				return new Enum(in.readUnsignedShort(), in.readUnsignedShort(), owner);
			case 'c':
				return new Class(in.readUnsignedShort(), owner);
			case '@':
				return new NestedAnnotation(new Annotation(in, owner), owner);
			case '[':
				ElementValue[] values = new ElementValue[in.readUnsignedShort()];
				for (int i = 0; i < values.length; i++) {
					values[i] = readElementValue(in, owner);
				}
				return new Array(values, owner);
			default:
				throw new ElementValuePairFormatException("Unrecognized ASCII char tag: " + tag + " (" + Integer.toHexString(Character.getNumericValue(tag)) + ")");
		}
	}

	public final char getTag() {
		return tag;
	}

	public abstract byte[] toBytes();

	public final ConstantPool getOwner() {
		return owner;
	}


	protected static char getCharASCII(int ubyteASCII) {
		switch(ubyteASCII) {
			case 0x42:
				return 'B';
			case 0x43:
				return 'C';
			case 0x44:
				return 'D';
			case 0x46:
				return 'F';
			case 0x49:
				return 'I';
			case 0x4A:
				return 'J';
			case 0x53:
				return 'S';
			case 0x5A:
				return 'Z';
			case 0x73:
				return 's';
			case 0x65:
				return 'e';
			case 0x63:
				return 'c';
			case 0x40:
				return '@';
			case 0x5B:
				return '[';
			default:
				throw new ElementValuePairFormatException("Unrecognized ASCII char tag: " + Integer.toHexString(ubyteASCII));
		}
	}

	public static final class Constant extends ElementValue {

		private final int constant;

		public Constant(DataInput in, ConstantPool owner) throws IOException {
			super(in, owner);
			constant = in.readUnsignedShort();
		}

		public Constant(char tag, int constValueIndex, ConstantPool owner) {
			super(tag, owner);
			constant = constValueIndex;
		}

		public jcla.classfile.constant.Constant getValue() {
			return owner.get(constant);
		}

		public int getConstantIndex() {
			return constant;
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[5];
			bytes[0] = (byte) tag;
			System.arraycopy(Data.toBytesInt(constant), 0, bytes, 1, 4);
			return bytes;
		}

	}

	public static final class Enum extends ElementValue {

		private final int typeName;
		private final int constName;

		public Enum(DataInput in, ConstantPool owner) throws IOException {
			super(in, owner);
			typeName = in.readUnsignedShort();
			constName = in.readUnsignedShort();
		}

		public Enum(int typeNameIndex, int constNameIndex, ConstantPool owner) {
			super('e', owner);
			typeName = typeNameIndex;
			constName = constNameIndex;
		}

		public jcla.classfile.constant.Constant.UTF8 getTypeName() {
			return owner.getUTF8(typeName);
		}

		public int getTypeNameIndex() {
			return typeName;
		}

		public jcla.classfile.constant.Constant.UTF8 getConstantName() {
			return owner.getUTF8(constName);
		}

		public int getConstantNameIndex() {
			return constName;
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[9];
			bytes[0] = (byte) tag;
			System.arraycopy(Data.toBytesInt(typeName), 0, bytes, 1, 4);
			System.arraycopy(Data.toBytesInt(constName), 0, bytes, 5, 4);
			return bytes;
		}

	}

	public static final class Class extends ElementValue {

		private final int classInfo;

		public Class(DataInput in, ConstantPool owner) throws IOException {
			super(in, owner);
			classInfo = in.readUnsignedShort();
		}

		public Class(int classInfoIndex, ConstantPool owner) {
			super('c', owner);
			classInfo = classInfoIndex;
		}

		public jcla.classfile.constant.Constant.UTF8 getClassInfo() {
			return owner.getUTF8(classInfo);
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[5];
			bytes[0] = (byte) tag;
			System.arraycopy(Data.toBytesInt(classInfo), 0, bytes, 1, 4);
			return bytes;
		}
	}

	public static final class NestedAnnotation extends ElementValue {

		private final Annotation annotation;

		public NestedAnnotation(DataInput in, ConstantPool owner) throws IOException {
			super(in, owner);
			annotation = new Annotation(in, owner);
		}

		public NestedAnnotation(Annotation annotation, ConstantPool owner) {
			super('@', owner);
			this.annotation = annotation;
		}

		public Annotation getAnnotation() {
			return annotation;
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[5];
			bytes[0] = (byte) tag;
			System.arraycopy(annotation.toBytes(), 0, bytes, 1, 4);
			return bytes;
		}

	}

	public static final class Array extends ElementValue {

		private final ElementValue[] values;

		public Array(DataInput in, ConstantPool owner) throws IOException {
			super(in, owner);
			values = new ElementValue[in.readUnsignedShort()];
			for (int i = 0; i < values.length; i++) {
				values[i] = readElementValue(in, owner);
			}
		}

		public Array(ElementValue[] values, ConstantPool owner) {
			super('[', owner);
			this.values = values;
		}

		public ElementValue getValue(int index) {
			return values[index];
		}

		public ElementValue[] getValues() {
			return values;
		}

		@Override
		public byte[] toBytes() {
			try {
				ByteArrayOutputStream byteArray = new ByteArrayOutputStream(1 + values.length * 4);
				DataOutputStream bytes = new DataOutputStream(byteArray);
				bytes.writeByte(tag);

				for (ElementValue elementValue : values)
					bytes.write(elementValue.toBytes());

				return byteArray.toByteArray();
			} catch (IOException e) {
				return new byte[0];
			}
		}

	}

}
