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
public final class MethodInfo {

	private final BitArray        accessflags;
	private final int             name;
	private final int             descriptor;
	private final AttributeInfo[] attributes;
	private       ConstantPool    owner;

	public MethodInfo(DataInput in, ConstantPool owner) throws IOException {
		accessflags = new BitArray(in.readUnsignedShort());
		name = in.readUnsignedShort();
		descriptor = in.readUnsignedShort();
		attributes = Attributes.load(in, owner);
		this.owner = owner;
	}

	public BitArray getAccessFlags() {
		return accessflags;
	}

	public Constant.UTF8 getName() {
		return owner.getUTF8(name);
	}

	public int getNameIndex() {
		return name;
	}

	public Constant.UTF8 getDescriptor() {
		return owner.getUTF8(descriptor);
	}

	public int getDescriptorIndex() {
		return descriptor;
	}

	public AttributeInfo[] getAttributes() {
		return attributes;
	}

	@SuppressWarnings("unchecked")
	public <A extends AttributeInfo> A getAttribute(Class<A> type) {
		A attribute = null;

		for(AttributeInfo a : attributes) {
			if(a.getClass() == type) {
				attribute = (A) a;
				break;
			}
		}

		return attribute;
	}

	public ConstantPool getOwner() {
		return owner;
	}

	@Override
	public String toString() {
		return "MethodInfo:[name: " + getName() + ";access_flags: " + accessflags + ";descriptor: " + getDescriptor() + "]";
	}

}
