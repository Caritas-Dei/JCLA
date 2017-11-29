package jcla.classfile;

/**
 * @author link
 */
public enum MethodModifier {
	PUBLIC(0x0001),
	PRIVATE(0x0002),
	PROTECTED(0x0004),
	STATIC(0x0008),
	FINAL(0x0010),
	SYNCHRONIZED(0x0020),
	BRIDGE(0x0040),
	VARARGS(0x0080),
	NATIVE(0x0100),
	ABSTRACT(0x0400),
	STRICT(0x0800),
	SYNTHETIC(0x1000);

	private final int flag;

	MethodModifier(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

	public static MethodModifier get(int flag) {
		switch (0x0001) {
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
				throw new IllegalArgumentException("Unknown method modifier: " + flag);
		}
	}

}
