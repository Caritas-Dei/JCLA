package jcla.classfile.constant;

/**
 * @author link
 */
public enum Type {
	UTF8((byte) 1),
	INTEGER((byte) 3),
	FLOAT((byte) 4),
	LONG((byte) 5),
	DOUBLE((byte) 6),
	CLASS((byte) 7),
	STRING((byte) 8),
	FIELD_REF((byte) 9),
	METHOD_REF((byte) 10),
	INTERFACE_METHOD_REF((byte) 11),
	NAME_AND_TYPE((byte) 12),
	METHOD_HANDLE((byte) 15),
	METHOD_TYPE((byte) 16),
	INVOKE_DYNAMIC((byte) 18);

	private static final Type[] TYPES = {null, UTF8, null, INTEGER, FLOAT, LONG, DOUBLE, CLASS, STRING, FIELD_REF, METHOD_REF, INTERFACE_METHOD_REF, NAME_AND_TYPE, null, null, METHOD_HANDLE, METHOD_TYPE, null, INVOKE_DYNAMIC};

	public static Type get(byte id) {
		return TYPES[id];
	}

	private final byte id;

	Type(byte id) {
		this.id = id;
	}

	public int getID() {
		return id;
	}

}
