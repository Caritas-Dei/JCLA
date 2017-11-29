package jcla.classfile;

/**
 * @author link
 */
public enum FieldModifier {
	PUBLIC(0x0001),
	PRIVATE(0x0002),
	PROTECTED(0x0004),
	STATIC(0x0008),
	FINAL(0x0010),
	VOLATILE(0x0040),
	TRANSIENT(0x0080),
	SYNTHETIC(0x1000),
	ENUM(0x4000);

	private final int flag;

	FieldModifier(int flag) {
		this.flag = flag;
	}

	public int getFlag() {
		return flag;
	}

	public static FieldModifier get(int flag) {
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
			case 0x0040:
				return VOLATILE;
			case 0x0080:
				return TRANSIENT;
			case 0x1000:
				return SYNTHETIC;
			case 0x4000:
				return ENUM;
			default:
				throw new IllegalArgumentException("Unknown field modifier: " + flag);
		}
	}

}
