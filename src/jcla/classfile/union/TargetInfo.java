package jcla.classfile.union;

import jcla.classfile.ConstantPool;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public abstract class TargetInfo {

	protected final ConstantPool owner;

	private TargetInfo(ConstantPool owner) {
		this.owner = owner;
	}

	public final ConstantPool getOwner() {
		return owner;
	}

	public abstract byte[] toBytes();

	public static final class TypeParameter extends TargetInfo {

		private final int index;

		public TypeParameter(DataInput in, ConstantPool owner) throws IOException {
			super(owner);
			index = in.readUnsignedByte();
		}

		public TypeParameter(int index, ConstantPool owner) {
			super(owner);
			this.index = index;
		}

		public int getIndex() {
			return index;
		}

		@Override
		public byte[] toBytes() {
			return new byte[]{Data.ucastB(index)};
		}

	}

	public static final class SuperType extends TargetInfo {

		private final int superType;

		public SuperType(DataInput in, ConstantPool owner) throws IOException {
			super(owner);
			superType = in.readUnsignedShort();
		}

		public SuperType(int superTypeIndex, ConstantPool owner) {
			super(owner);
			superType = superTypeIndex;
		}

		public int getIndex() {
			return superType;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesShort(superType);
		}

	}

	public static final class TypeParameterBound extends TargetInfo {

		private final int index;
		private final int boundIndex;

		public TypeParameterBound(DataInput in, ConstantPool owner) throws IOException {
			super(owner);
			index = in.readUnsignedByte();
			boundIndex = in.readUnsignedByte();
		}

		public TypeParameterBound(int typeParameterIndex, int boundIndex, ConstantPool owner) {
			super(owner);
			index = typeParameterIndex;
			this.boundIndex = boundIndex;
		}

		public int getIndex() {
			return index;
		}

		public int getBoundIndex() {
			return boundIndex;
		}

		@Override
		public byte[] toBytes() {
			return new byte[]{Data.ucastB(index), Data.ucastB(boundIndex)};
		}

	}

	public static final class Empty extends TargetInfo {

		public Empty(ConstantPool owner) {
			super(owner);
		}

		@Override
		public byte[] toBytes() {
			return new byte[0];
		}

	}

	public static final class FormalParameter extends TargetInfo {

		private final int index;

		public FormalParameter(DataInput in, ConstantPool owner) throws IOException {
			super(owner);
			index = in.readUnsignedByte();
		}

		public FormalParameter(int formalParameterIndex, ConstantPool owner) {
			super(owner);
			index = formalParameterIndex;
		}

		public int getIndex() {
			return index;
		}

		@Override
		public byte[] toBytes() {
			return new byte[]{Data.ucastB(index)};
		}

	}

	public static final class Throws extends TargetInfo {

		private final int throwsType;

		public Throws(DataInput in, ConstantPool owner) throws IOException {
			super(owner);
			throwsType = in.readUnsignedShort();
		}

		public Throws(int throwsTypeIndex, ConstantPool owner) {
			super(owner);
			throwsType = throwsTypeIndex;
		}

		public int getTypeIndex() {
			return throwsType;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesShort(throwsType);
		}

	}

	public static final class LocalVar extends TargetInfo {

		private final Entry[] table;

		public LocalVar(DataInput in, ConstantPool owner) throws IOException {
			super(owner);
			table = new Entry[in.readUnsignedShort()];
			for (int i = 0; i < table.length; i++) {
				table[i] = new Entry(in, owner);
			}
		}

		public static final class Entry {

			private final int          startPC;
			private final int          length;
			private final int          index;
			private final ConstantPool owner;

			public Entry(DataInput in, ConstantPool owner) throws IOException {
				startPC = in.readUnsignedShort();
				length = in.readUnsignedShort();
				index = in.readUnsignedShort();
				this.owner = owner;
			}

			public Entry(int startPC, int length, int index, ConstantPool owner) {
				this.startPC = startPC;
				this.length = length;
				this.index = index;
				this.owner = owner;
			}

			public int getStartPC() {
				return startPC;
			}

			public int getLength() {
				return length;
			}

			public int getIndex() {
				return index;
			}

			public ConstantPool getOwner() {
				return owner;
			}

			public byte[] toBytes() {
				byte[] bytes = new byte[6];
				System.arraycopy(Data.toBytesShort(startPC), 0, bytes, 0, 2);
				System.arraycopy(Data.toBytesShort(length), 0, bytes, 2, 2);
				System.arraycopy(Data.toBytesShort(index), 0, bytes, 4, 2);
				return bytes;
			}

		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[2 + 6 * table.length];
			System.arraycopy(Data.toBytesShort(table.length), 0, bytes, 0, 2);
			for (int i = 2, j = 0; i < bytes.length; i += 6, j++) {
				System.arraycopy(table[j].toBytes(), 0, bytes, i, 6);
			}
			return bytes;
		}

	}

	public static final class Catch extends TargetInfo {

		private final int exceptionTableIndex;

		public Catch(DataInput in, ConstantPool owner) throws IOException {
			super(owner);
			exceptionTableIndex = in.readUnsignedShort();
		}

		public Catch(int exceptionTableIndex, ConstantPool owner) {
			super(owner);
			this.exceptionTableIndex = exceptionTableIndex;
		}

		public int getExceptionTableIndex() {
			return exceptionTableIndex;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesShort(exceptionTableIndex);
		}

	}

	public static final class Offset extends TargetInfo {

		private final int offset;

		public Offset(DataInput in, ConstantPool owner) throws IOException {
			super(owner);
			offset = in.readUnsignedShort();
		}

		public Offset(int offset, ConstantPool owner) {
			super(owner);
			this.offset = offset;
		}

		public int getOffset() {
			return offset;
		}

		@Override
		public byte[] toBytes() {
			return Data.toBytesShort(offset);
		}

	}

	public static final class TypeArgument extends TargetInfo {

		private final int offset;
		private final int typeArgumentIndex;

		public TypeArgument(DataInput in, ConstantPool owner) throws IOException {
			super(owner);
			offset = in.readUnsignedShort();
			typeArgumentIndex = in.readUnsignedByte();
		}

		public TypeArgument(int offset, int typeArgumentIndex, ConstantPool owner) {
			super(owner);
			this.offset = offset;
			this.typeArgumentIndex = typeArgumentIndex;
		}

		public int getOffset() {
			return offset;
		}

		public int getTypeArgumentIndex() {
			return typeArgumentIndex;
		}

		@Override
		public byte[] toBytes() {
			byte[] bytes = new byte[3];
			System.arraycopy(Data.toBytesShort(offset), 0, bytes, 0, 2);
			bytes[2] = Data.ucastB(typeArgumentIndex);
			return bytes;
		}

	}

}
