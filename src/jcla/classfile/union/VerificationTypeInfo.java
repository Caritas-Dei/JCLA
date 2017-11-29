package jcla.classfile.union;

import jcla.classfile.ConstantPool;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public final class VerificationTypeInfo {

	public static final byte TAG_ITEM_TOP                = 0;
	public static final byte TAG_ITEM_INTEGER            = 1;
	public static final byte TAG_ITEM_FLOAT              = 2;
	public static final byte TAG_ITEM_NULL               = 5;
	public static final byte TAG_ITEM_UNINITIALIZED_THIS = 6;
	public static final byte TAG_ITEM_OBJECT             = 7;
	public static final byte TAG_ITEM_UNINITIALIZED      = 8;
	public static final byte TAG_ITEM_LONG               = 4;
	public static final byte TAG_ITEM_DOUBLE             = 3;

	private final byte tag;
	private final int  ext;

	public VerificationTypeInfo(DataInput in, ConstantPool owner) throws IOException {
		tag = in.readByte();
		switch (tag) {
			case TAG_ITEM_OBJECT:
			case TAG_ITEM_UNINITIALIZED:
				ext = in.readUnsignedShort();
				break;
			default:
				ext = 0;
		}
	}

	public VerificationTypeInfo(byte tag, ConstantPool owner) {
		this(tag, -1, owner);
	}

	public VerificationTypeInfo(byte tag, int ext, ConstantPool owner) {
		this.tag = tag;
		this.ext = ext;
	}

	public byte getTag() {
		return tag;
	}

	public int getExt() {
		return ext;
	}

	public byte[] toBytes() {
		byte[] bytes;

		if(tag > 6 && tag < 9) {
			bytes = new byte[3];
			System.arraycopy(Data.toBytesShort(ext), 0, bytes, 1, 2);
		} else {
			bytes = new byte[1];
		}

		bytes[0] = tag;

		return bytes;
	}

}
