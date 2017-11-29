package jcla.classfile.struct;

import jcla.classfile.ConstantPool;
import jcla.classfile.constant.Constant;
import jcla.classfile.union.ElementValue;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

import static jcla.classfile.union.ElementValue.readElementValue;

/**
 * @author link
 */
public final class ElementValuePair {

	private final int          elementName;
	private final ElementValue elementValue;
	private final ConstantPool owner;

	public ElementValuePair(DataInput in, ConstantPool owner) throws IOException {
		elementName = in.readUnsignedShort();
		elementValue = readElementValue(in, owner);
		this.owner = owner;
	}

	public ElementValuePair(int elementName, ElementValue elementValue, ConstantPool owner) {
		this.elementName = elementName;
		this.elementValue = elementValue;
		this.owner = owner;
	}

	public Constant.UTF8 getElementName() {
		return owner.getUTF8(elementName);
	}

	public int getElementNameIndex() {
		return elementName;
	}

	public ElementValue getElementValue() {
		return elementValue;
	}

	public byte[] toBytes() {
		byte[] elementValue = this.elementValue.toBytes();
		byte[] bytes = new byte[elementValue.length + 4];
		System.arraycopy(Data.toBytesInt(elementName), 0, bytes, 0, 4);
		System.arraycopy(elementValue, 0, bytes, 4, elementValue.length);
		return bytes;
	}

	public ConstantPool getOwner() {
		return owner;
	}

}
