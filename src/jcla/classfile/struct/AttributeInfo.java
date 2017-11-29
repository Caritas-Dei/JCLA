package jcla.classfile.struct;

import jcla.classfile.ConstantPool;
import jcla.classfile.constant.Constant.UTF8;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public abstract class AttributeInfo {

	protected final int          name;
	protected final int          length;
	protected final ConstantPool owner;

	protected AttributeInfo(DataInput in, ConstantPool owner) throws IOException {
		this(in.readUnsignedShort(), in.readInt(), owner);
	}

	protected AttributeInfo(int nameIndex, int length, ConstantPool owner) {
		name = nameIndex;
		this.length = length;
		this.owner = owner;
	}

	public final UTF8 getName() {
		return owner.getUTF8(name);
	}

	public final int getLength() {
		return length;
	}

	/**
	 * Gets this attribute_info structure as a byte array.
	 * <p>
	 * This method can be particularly expensive since AttributeInfo objects don't store data as one byte array, but
	 * rather converts data their actual primimtive types (within type constraints). In the future, AttributeInfo
	 * structures may be optimized for the sake of conversions between ClassFiles and ClassDefinitions.
	 * </p>
	 *
	 * @return this attribute_info structure as a byte array
	 */
	public abstract byte[] getInfo();

	public final ConstantPool getOwner() {
		return owner;
	}

}
