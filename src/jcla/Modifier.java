package jcla;

/**
 * @author link
 */
public enum Modifier {

	PUBLIC(0x0001, 0),
	PRIVATE(0x0002, 1),
	PROTECTED(0x0004, 2),
	STATIC(0x0008, 3),
	FINAL(0x0010, 4),
	SUPER(0x0020, 5),
	SYNCHRONIZED(0x0020, 5),
	VOLATILE(0x0040, 6),
	BRIDGE(0x0040, 6),
	TRANSIENT(0x0080, 7),
	VARARGS(0x0080, 7),
	NATIVE(0x0100, 8),
	INTERFACE(0x0200, 9),
	ABSTRACT(0x0400, 10),
	STRICT(0x0800, 11),
	SYNTHETIC(0x1000, 12),
	ANNOTATION(0x2000, 13),
	ENUM(0x4000, 14),
	MANDATED(0x8000, 15),
	PACKAGE_LOCAL(0xFFF0, -1) {
		@Override
		public int getBit() {
			throw new UnsupportedOperationException("PACKAGE_LOCAL does not have an assigned bit.");
		}
	};

	private final int  flag;
	private final byte bit;

	Modifier(int flag, int bit) {
		this.flag = flag;
		this.bit = (byte) bit;
	}

	public int getFlag() {
		return flag;
	}

	public int getBit() {
		return bit;
	}

	public static Modifier getClassModifier(int modifier) {
		switch( modifier ) {
			case 0x0001:
				return PUBLIC;
			case 0x0010:
				return FINAL;
			case 0x0020:
				return SUPER;
			case 0x0200:
				return INTERFACE;
			case 0x0400:
				return ABSTRACT;
			case 0x1000:
				return SYNTHETIC;
			case 0x2000:
				return ANNOTATION;
			case 0x4000:
				return ENUM;
			default:
				throw new IllegalArgumentException("Invalid class modifier: " + Integer.toHexString(modifier));
		}
	}

	public static Modifier getInnerClassModifier(int modifier) {
		switch( modifier ) {
			case 0x0001:
				return PUBLIC;
			case 0x0002:
				return PRIVATE;
			case 0x0004:
				return PROTECTED;
			case 0x0008:
				return STATIC;
			case 0x0010:
				return FINAL;
			case 0x0200:
				return INTERFACE;
			case 0x0400:
				return ABSTRACT;
			case 0x1000:
				return SYNTHETIC;
			case 0x2000:
				return ANNOTATION;
			case 0x4000:
				return ENUM;
			default:
				throw new IllegalArgumentException("Invalid inner class modifier: " + Integer.toHexString(modifier));
		}
	}

	public static Modifier getFieldModifier(int modifier) {
		switch( modifier ) {
			case 0x0001:
				return PUBLIC;
			case 0x0002:
				return PRIVATE;
			case 0x0004:
				return PROTECTED;
			case 0x0008:
				return STATIC;
			case 0x0010:
				return FINAL;
			case 0x0040:
				return VOLATILE;
			case 0x0080:
				return TRANSIENT;
			case 0x1000:
				return SYNTHETIC;
			case 0x4000:
				return ENUM;
			default:
				throw new IllegalArgumentException("Invalid field modifier: " + Integer.toHexString(modifier));
		}
	}

	public static Modifier MethodModifier(int modifier) {
		switch( modifier ) {
			case 0x0001:
				return PUBLIC;
			case 0x0002:
				return PRIVATE;
			case 0x0004:
				return PROTECTED;
			case 0x0008:
				return STATIC;
			case 0x0010:
				return FINAL;
			case 0x0020:
				return SYNCHRONIZED;
			case 0x0040:
				return BRIDGE;
			case 0x0080:
				return VARARGS;
			case 0x0100:
				return NATIVE;
			case 0x0400:
				return ABSTRACT;
			case 0x0800:
				return STRICT;
			case 0x1000:
				return SYNTHETIC;
			default:
				throw new IllegalArgumentException("Invalid method modifier: " + Integer.toHexString(modifier));
		}
	}

}
