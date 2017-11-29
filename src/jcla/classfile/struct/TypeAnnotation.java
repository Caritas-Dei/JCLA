package jcla.classfile.struct;

import jcla.classfile.ConstantPool;
import jcla.classfile.constant.Constant;
import jcla.classfile.union.TargetInfo;

import java.io.ByteArrayOutputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * @author link
 */
public final class TypeAnnotation {

	private final int                targetType;
	private final TargetInfo         targetInfo;
	private final TypePath           targetPath;
	private final int                type;
	private final ElementValuePair[] elementValuePairs;
	private final ConstantPool       owner;

	public TypeAnnotation(DataInput in, ConstantPool owner) throws IOException {
		targetType = in.readUnsignedByte();
		targetInfo = readTargetInfo(targetType, in, owner);
		targetPath = new TypePath(in, owner);
		type = in.readUnsignedShort();
		elementValuePairs = new ElementValuePair[in.readUnsignedShort()];
		for (int i = 0; i < elementValuePairs.length; i++) {
			elementValuePairs[i] = new ElementValuePair(in, owner);
		}
		this.owner = owner;
	}

	public TypeAnnotation(int targetType, TargetInfo targetInfo, TypePath targetPath, int typeIndex, ElementValuePair[] elementValuePairs, ConstantPool owner) {
		this.targetType = targetType;
		this.targetInfo = targetInfo;
		this.targetPath = targetPath;
		this.type = typeIndex;
		this.elementValuePairs = elementValuePairs;
		this.owner = owner;
	}

	private static TargetInfo readTargetInfo(int targetType, DataInput in, ConstantPool owner) throws IOException {
		switch (targetType) {
			case 0x00:
			case 0x01:
				return new TargetInfo.TypeParameter(in, owner);
			case 0x10:
				return new TargetInfo.SuperType(in, owner);
			case 0x11:
			case 0x12:
				return new TargetInfo.TypeParameterBound(in, owner);
			case 0x13:
			case 0x14:
			case 0x15:
				return new TargetInfo.Empty(owner);
			case 0x16:
				return new TargetInfo.FormalParameter(in, owner);
			case 0x17:
				return new TargetInfo.Throws(in, owner);
			case 0x40:
			case 0x41:
				return new TargetInfo.LocalVar(in, owner);
			case 0x42:
				return new TargetInfo.Catch(in, owner);
			case 0x43:
			case 0x44:
			case 0x45:
			case 0x46:
				return new TargetInfo.Offset(in, owner);
			case 0x47:
			case 0x48:
			case 0x49:
			case 0x4A:
			case 0x4B:
				return new TargetInfo.TypeArgument(in, owner);
			default:
				throw new IllegalArgumentException("Illegal target_type: " + Integer.toHexString(targetType));
		}
	}

	public int getTargetType() {
		return targetType;
	}

	public TargetInfo getTargetInfo() {
		return targetInfo;
	}

	public TypePath getTargetPath() {
		return targetPath;
	}

	public Constant.UTF8 getType() {
		return owner.getUTF8(type);
	}

	public ElementValuePair[] getElementValuePairs() {
		return elementValuePairs;
	}

	public ConstantPool getOwner() {
		return owner;
	}

	public byte[] toBytes() {
		ByteArrayOutputStream bytes = new ByteArrayOutputStream(6 + targetPath.getPaths().length * 4 + elementValuePairs.length * 4);
		DataOutputStream out = new DataOutputStream(bytes);
		try {
			out.writeShort(targetType);
			out.write(targetInfo.toBytes());
			out.write(targetPath.toBytes());
			out.writeShort(type);
			out.writeShort(elementValuePairs.length);
			for (ElementValuePair pair : elementValuePairs) {
				out.write(pair.toBytes());
			}
		}catch (IOException e) {
			// ignore
		}
		return bytes.toByteArray();
	}

}
