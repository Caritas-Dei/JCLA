package jcla.classfile.struct;

import jcla.classfile.ConstantPool;
import jcla.classfile.attribute.Attributes;
import jcla.classfile.constant.Constant;
import jcla.util.data.BitArray;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public final class FieldInfo {

	private final BitArray        accessflags;
	private final int             name;
	private final int             descriptor;
	private final AttributeInfo[] attributes;

	private final ConstantPool owner;

	public FieldInfo(DataInput in, ConstantPool owner) throws IOException {
		accessflags = new BitArray(in.readUnsignedShort());
		name = in.readUnsignedShort();
		descriptor = in.readUnsignedShort();
		attributes = Attributes.load(in, owner);
		this.owner = owner;
	}

	public FieldInfo(int accessflags, int nameIndex, int descriptorIndex, AttributeInfo[] attributes, ConstantPool owner) {
		this.accessflags = new BitArray(accessflags);
		name = nameIndex;
		descriptor = descriptorIndex;
		this.attributes = attributes;
		this.owner = owner;
	}


	public BitArray getAccessFlags() {
		return accessflags;
	}

	public Constant.UTF8 getName() {
		return owner.getUTF8(name);
	}

	public Constant.UTF8 getDescriptor() {
		return owner.getUTF8(descriptor);
	}

	public AttributeInfo[] getAttributes() {
		return attributes;
	}

	public ConstantPool getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "FieldInfo:[name: " + getName() + ";descriptor: " + getDescriptor() + ";access_flags: " + accessflags + "attributes_count: " + attributes.length + "]";
	}

}
