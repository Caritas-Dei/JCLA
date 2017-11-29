package jcla.classfile.struct;

import jcla.classfile.ConstantPool;
import jcla.classfile.constant.Constant;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public final class Annotation {

	private final int type;
	private final ElementValuePair[] elementValuePairs;
	private final ConstantPool owner;

	public Annotation(DataInput in, ConstantPool owner) throws IOException {
		type = in.readUnsignedShort();
		elementValuePairs = new ElementValuePair[in.readUnsignedShort()];
		this.owner = owner;
		for (int i = 0; i < elementValuePairs.length; i++) {
			elementValuePairs[i] = new ElementValuePair(in, owner);
		}
	}

	public Annotation(int type, ElementValuePair[] pairs, ConstantPool owner) {
		this.type = type;
		elementValuePairs = pairs;
		this.owner = owner;
	}

	public Constant.UTF8 getType() {
		return owner.getUTF8(type);
	}

	public ElementValuePair[] getElementValuePairs() {
		return elementValuePairs;
	}

	public ElementValuePair getElementValuePair(int index) {
		return elementValuePairs[index];
	}

	public byte[] toBytes() {

		try {
			// expandable byte array
			//
			// initialize to the minimum size of an ElementValuePair
			// times the length of ~pairs plus 4 bytes for the type.
			ByteArrayOutputStream byteList = new ByteArrayOutputStream(4 + elementValuePairs.length * 4);
			DataOutputStream bytes = new DataOutputStream(byteList);
			bytes.writeInt(type);
			// write ElementValuePair[]
			for(ElementValuePair elementValuePair : elementValuePairs)
				bytes.write(elementValuePair.toBytes());

			return byteList.toByteArray();
		} catch (IOException e) {
			// ignore; no disk reading at all
			return new byte[0];
		}
	}

}
