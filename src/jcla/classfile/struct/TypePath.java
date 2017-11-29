package jcla.classfile.struct;

import jcla.classfile.ConstantPool;
import jcla.util.io.Data;

import java.io.DataInput;
import java.io.IOException;

/**
 * @author link
 */
public final class TypePath {

	private final Path[]       paths;
	private final ConstantPool owner;

	public TypePath(DataInput in, ConstantPool owner) throws IOException {
		paths = new Path[in.readUnsignedByte()];
		for (int i = 0; i < paths.length; i++) {
			paths[i] = new Path(in, owner);
		}
		this.owner = owner;
	}

	public TypePath(Path[] paths, ConstantPool owner) {
		this.paths = paths;
		this.owner = owner;
	}

	public Path[] getPaths() {
		return paths;
	}

	public ConstantPool getOwner() {
		return owner;
	}

	public byte[] toBytes() {
		byte[] bytes = new byte[paths.length * 4];
		for (int i = 0, j = 0; i < bytes.length; i += 4, j++) {
			System.arraycopy(paths[j].toBytes(), 0, bytes, i, 4);
		}
		return bytes;
	}

	public static final class Path {

		private final int          typePathKind;
		private final int          typeArgumentIndex;
		private final ConstantPool owner;

		public Path(DataInput in, ConstantPool owner) throws IOException {
			typePathKind = in.readUnsignedByte();
			typeArgumentIndex = in.readUnsignedByte();
			this.owner = owner;
		}

		public Path(int typePathKind, int typeArgumentIndex, ConstantPool owner) {
			this.typePathKind = typePathKind;
			this.typeArgumentIndex = typeArgumentIndex;
			this.owner = owner;
		}

		public int getTypePathKind() {
			return typePathKind;
		}

		public int getTypeArgumentIndex() {
			return typeArgumentIndex;
		}

		public ConstantPool getOwner() {
			return owner;
		}

		public byte[] toBytes() {
			byte[] bytes = new byte[4];
			System.arraycopy(Data.toBytesShort(typePathKind), 0, bytes, 0, 2);
			System.arraycopy(Data.toBytesShort(typeArgumentIndex), 0, bytes, 2, 2);
			return bytes;
		}

	}

}
