package jcla.util.data;

/**
 * A big-endian bit array using boolean.
 *
 * @author link
 */
public final class BitArray {

	private final boolean[] bits;

	public BitArray(byte array) {
		bits = new boolean[8];
		for (int i = 0; i < 8; i++) {
			bits[i] = (array & 1) == 1;
			array >>>= 1;
		}
	}

	public BitArray(short array) {
		bits = new boolean[16];
		for (int i = 0; i < 16; i++) {
			bits[i] = (array & 1) == 1;
			array >>>= 1;
		}
	}

	public BitArray(int array) {
		bits = new boolean[32];
		for (int i = 0; i < 32; i++) {
			bits[i] = (array & 1) == 1;
			array >>>= 1;
		}
	}

	public BitArray(long array) {
		bits = new boolean[64];
		for (int i = 0; i < 64; i++) {
			bits[i] = (array & 1) == 1;
			array >>>= 1;
		}
	}

	public BitArray(float array) {
		int data = Float.floatToRawIntBits(array);
		bits = new boolean[32];
		for (int i = 0; i < 32; i++) {
			bits[i] = (data & 1) == 1;
			data >>>= 1;
		}
	}

	public BitArray(double array) {
		long data = Double.doubleToRawLongBits(array);
		bits = new boolean[64];
		for (int i = 0; i < 64; i++) {
			bits[i] = (data & 1) == 1;
			data >>>= 1;
		}
	}

	/**
	 * Gets the bit at the specified index in this bit array.
	 *
	 * @param index the index of the bit starting at the right
	 * @return the value of the bit
	 */
	public boolean get(int index) {
		return bits[index];
	}

	/**
	 * Sets the bit at the specified index in this bit array.
	 *
	 * @param index the index of the bit starting at the right
	 * @param value the value of the bit
	 */
	public void set(int index, boolean value) {
		bits[index] = value;
	}


	/**
	 * Creates a byte array whose total bit width equals this bit array. Each bit has a 1:1 correspondence between the
	 * given array and this BitArray.
	 *
	 * @return an array of bytes whose total bit width equals this bit array.
	 */
	public byte[] toByteArray() {
		int    bytes = bits.length / 2;
		byte[] words = new byte[bytes];
		byte   word  = 0;
		for (int i = 0; i < bytes; i++) {
			for (int j = bits.length - 1; j >= 0; i -= 8) {
				word |= bits[j] ? -127 : 0;
				word |= bits[j - 1] ? 0b01000000 : 0;
				word |= bits[j - 2] ? 0b00100000 : 0;
				word |= bits[j - 3] ? 0b00010000 : 0;
				word |= bits[j - 4] ? 0b00001000 : 0;
				word |= bits[j - 5] ? 0b00000100 : 0;
				word |= bits[j - 6] ? 0b00000010 : 0;
				word |= bits[j - 7] ? 0b00000001 : 0;
			}
			words[i] = word;
			word = 0;
		}

		return words;
	}

}
