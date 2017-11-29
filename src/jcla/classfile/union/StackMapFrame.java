package jcla.classfile.union;

import jcla.classfile.ConstantPool;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public final class StackMapFrame {

	public static final int TAG_SAME_MIN                           = 0;
	public static final int TAG_SAME_MAX                           = 63;
	public static final int TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN = 64;
	public static final int TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MAX = Byte.MAX_VALUE;
	public static final int TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_EXT = 247;
	public static final int TAG_CHOP_FRAME_MIN                     = 248;
	public static final int TAG_CHOP_FRAME_MAX                     = 250;
	public static final int TAG_SAME_FRAME_EXT                     = 251;
	public static final int TAG_APPEND_FRAME_MIN                   = 252;
	public static final int TAG_APPEND_FRAME_MAX                   = 254;
	public static final int TAG_FULL_FRAME                         = 255;

	private final int                    frameType;
	private final int                    offsetDelta;
	private final VerificationTypeInfo[] locals;
	private final VerificationTypeInfo[] stack;
	private final ConstantPool           owner;

	public StackMapFrame(DataInput in, ConstantPool owner) throws IOException {
		frameType = in.readUnsignedByte();
		// put loading in another method to make constructor more clean
		Object[] data = loadFrame(frameType, in, owner);
		offsetDelta = (int) data[0];
		locals = (VerificationTypeInfo[]) data[1];
		stack = (VerificationTypeInfo[]) data[2];
		this.owner = owner;
	}

	public StackMapFrame(int frameType, int offsetDelta, VerificationTypeInfo[] locals, VerificationTypeInfo[] stack, ConstantPool owner) {
		this.frameType = frameType;
		this.offsetDelta = offsetDelta;
		this.locals = locals;
		this.stack = stack;
		this.owner = owner;
	}

	/**
	 * Loads the remaining stack_map_frame elements and returns an array containing them
	 *
	 * @param frameType the frameType of the stackMapFrame
	 * @param in        the classfile byte stream
	 * @param owner     the owner of this frame
	 * @return {(int)offsetDelta, (VerificationTypeInfo[]) locals, (VerificationTypeInfo[]) stack}
	 * @throws IOException if an error occurs while reading data from in
	 */
	private static Object[] loadFrame(final int frameType, DataInput in, ConstantPool owner) throws IOException {
		final int                    offsetDelta;
		final VerificationTypeInfo[] locals;
		final VerificationTypeInfo[] stack;

		switch (frameType) {
			case TAG_SAME_MIN:
			case TAG_SAME_MIN + 1:
			case TAG_SAME_MIN + 2:
			case TAG_SAME_MIN + 3:
			case TAG_SAME_MIN + 4:
			case TAG_SAME_MIN + 5:
			case TAG_SAME_MIN + 6:
			case TAG_SAME_MIN + 7:
			case TAG_SAME_MIN + 8:
			case TAG_SAME_MIN + 9:
			case TAG_SAME_MIN + 10:
			case TAG_SAME_MIN + 11:
			case TAG_SAME_MIN + 12:
			case TAG_SAME_MIN + 13:
			case TAG_SAME_MIN + 14:
			case TAG_SAME_MIN + 15:
			case TAG_SAME_MIN + 16:
			case TAG_SAME_MIN + 17:
			case TAG_SAME_MIN + 18:
			case TAG_SAME_MIN + 19:
			case TAG_SAME_MIN + 20:
			case TAG_SAME_MIN + 21:
			case TAG_SAME_MIN + 22:
			case TAG_SAME_MIN + 23:
			case TAG_SAME_MIN + 24:
			case TAG_SAME_MIN + 25:
			case TAG_SAME_MIN + 26:
			case TAG_SAME_MIN + 27:
			case TAG_SAME_MIN + 28:
			case TAG_SAME_MIN + 29:
			case TAG_SAME_MIN + 30:
			case TAG_SAME_MIN + 31:
			case TAG_SAME_MIN + 32:
			case TAG_SAME_MIN + 33:
			case TAG_SAME_MIN + 34:
			case TAG_SAME_MIN + 35:
			case TAG_SAME_MIN + 36:
			case TAG_SAME_MIN + 37:
			case TAG_SAME_MIN + 38:
			case TAG_SAME_MIN + 39:
			case TAG_SAME_MIN + 40:
			case TAG_SAME_MIN + 41:
			case TAG_SAME_MIN + 42:
			case TAG_SAME_MIN + 43:
			case TAG_SAME_MIN + 44:
			case TAG_SAME_MIN + 45:
			case TAG_SAME_MIN + 46:
			case TAG_SAME_MIN + 47:
			case TAG_SAME_MIN + 48:
			case TAG_SAME_MIN + 49:
			case TAG_SAME_MIN + 50:
			case TAG_SAME_MIN + 51:
			case TAG_SAME_MIN + 52:
			case TAG_SAME_MIN + 53:
			case TAG_SAME_MIN + 54:
			case TAG_SAME_MIN + 55:
			case TAG_SAME_MIN + 56:
			case TAG_SAME_MIN + 57:
			case TAG_SAME_MIN + 58:
			case TAG_SAME_MIN + 59:
			case TAG_SAME_MIN + 60:
			case TAG_SAME_MIN + 61:
			case TAG_SAME_MIN + 62:
			case TAG_SAME_MAX:
				offsetDelta = frameType;
				locals = null;
				stack = null;
				break;
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 1:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 2:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 3:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 4:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 5:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 6:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 7:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 8:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 9:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 10:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 11:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 12:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 13:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 14:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 15:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 16:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 17:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 18:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 19:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 20:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 21:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 22:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 23:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 24:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 25:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 26:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 27:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 28:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 29:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 30:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 31:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 32:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 33:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 34:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 35:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 36:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 37:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 38:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 39:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 40:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 41:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 42:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 43:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 44:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 45:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 46:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 47:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 48:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 49:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 50:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 51:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 52:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 53:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 54:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 55:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 56:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 57:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 58:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 59:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 60:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 61:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MIN + 62:
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_MAX:
				offsetDelta = frameType - 64;
				locals = null;
				stack = new VerificationTypeInfo[]{new VerificationTypeInfo(in, owner)};
				break;
			case TAG_SAME_LOCALS_1_STACK_ITEM_FRAME_EXT:
				offsetDelta = in.readUnsignedShort();
				locals = null;
				stack = new VerificationTypeInfo[]{new VerificationTypeInfo(in, owner)};
				break;
			case TAG_CHOP_FRAME_MIN:
			case TAG_CHOP_FRAME_MIN + 1:
			case TAG_CHOP_FRAME_MAX:
				offsetDelta = in.readUnsignedShort();
				locals = null;
				stack = null;
				break;
			case TAG_SAME_FRAME_EXT:
				offsetDelta = in.readUnsignedShort();
				locals = null;
				stack = null;
				break;
			case TAG_APPEND_FRAME_MIN:
			case TAG_APPEND_FRAME_MIN + 1:
			case TAG_APPEND_FRAME_MAX:
				offsetDelta = in.readUnsignedShort();
				locals = new VerificationTypeInfo[]{new VerificationTypeInfo(in, owner)};
				stack = null;
				break;
			case TAG_FULL_FRAME:
				offsetDelta = in.readUnsignedShort();
				locals = new VerificationTypeInfo[in.readUnsignedShort()];
				for (int i = 0; i < locals.length; i++) {
					locals[i] = new VerificationTypeInfo(in, owner);
				}
				stack = new VerificationTypeInfo[in.readUnsignedShort()];
				for (int i = 0; i < stack.length; i++) {
					stack[i] = new VerificationTypeInfo(in, owner);
				}
				break;
			default:
				throw new UnsupportedClassVersionError("This class file is unsupported: unrecognized frame_type \"" + frameType + "\"");
		}
		return new Object[]{offsetDelta, locals, stack};
	}

	public int getFrameType() {
		return frameType;
	}

	public int getOffsetDelta() {
		return offsetDelta;
	}

	public VerificationTypeInfo[] getLocals() {
		return locals;
	}

	public VerificationTypeInfo[] getStack() {
		return stack;
	}

	public byte[] toBytes() {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream(8 + locals.length * 3 + stack.length * 3);
		DataOutputStream out = new DataOutputStream(bytes);
		try {
			out.writeShort(frameType);
			out.writeShort(offsetDelta);
			out.writeShort(locals.length);
			for(VerificationTypeInfo typeInfo : locals)
				out.write(typeInfo.toBytes());

			out.writeShort(stack.length);
			for(VerificationTypeInfo typeInfo : stack)
				out.write(typeInfo.toBytes());


		} catch (IOException e) {
			// ignore
		}

		return bytes.toByteArray();
	}

}
