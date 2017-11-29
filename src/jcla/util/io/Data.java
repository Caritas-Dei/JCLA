package jcla.util.io;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/**
 * @author link
 */
public enum Data {
	;

	private static final long TRUE = 0xFFFFFFFFFFFFFFFFL;
	private static final long FALSE = 0L;

	public static short asShort(byte[] data, int offset) {
		short first  = data[offset];
		short second = data[offset + 1];
		return (short) (first | second);
	}

	public static int asInt(byte[] data, int offset) {
		int first  = data[offset] << 24;
		int second = data[offset + 1] << 16;
		int third  = data[offset + 2] << 8;
		int fourth = data[offset + 3];
		return first | second | third | fourth;
	}

	public static long asLong(byte[] data, int offset) {
		long first   = ((long) data[offset]) << 56;
		long second  = ((long) data[offset + 1]) << 48;
		long third   = ((long) data[offset + 2]) << 40;
		long fourth  = ((long) data[offset + 3]) << 32;
		long fifth   = data[offset + 4] << 24;
		long sixth   = data[offset + 5] << 16;
		long seventh = data[offset + 6] << 8;
		long eighth  = data[offset + 7];
		return first | second | third | fourth | fifth | sixth | seventh | eighth;
	}

	public static String asString(byte[] data, int offset, int length, Charset charset) {
		ByteBuffer bytes = ByteBuffer.allocateDirect(offset + length);
		bytes.put(data, offset, length);
		return charset.decode(bytes).toString();
	}

	public static byte[] toBytesShort(int value) {
		final byte[] bytes   = new byte[2];
		int          counter = value;
		// init bytes in reverse order
		for (int i = bytes.length - 1; i > -1; i--) {
			bytes[i] = (byte) (counter & 0xFF);
			counter >>= 8;
		}

		return bytes;
	}

	public static byte[] toBytesInt(int value) {
		final byte[] bytes   = new byte[4];
		int          counter = value;
		// init bytes in reverse order
		for (int i = bytes.length - 1; i > -1; i--) {
			bytes[i] = (byte) (counter & 0xFF);
			counter >>= 8;
		}

		return bytes;
	}

	public static byte[] toBytesFloat(float value) {
		return toBytesInt(Float.floatToRawIntBits(value));
	}

	public static byte[] toBytesLong(long value) {
		final byte[] bytes   = new byte[8];
		long         counter = value;
		// init bytes in reverse order
		for (int i = bytes.length - 1; i > -1; i--) {
			bytes[i] = (byte) (counter & 0xFF);
			counter >>= 8;
		}

		return bytes;
	}

	public static byte[] toBytesDouble(double value) {
		return toBytesLong(Double.doubleToRawLongBits(value));
	}

	/**
	 * Encodes an unsigned byte (as an int) as a signed two's complement byte.
	 *
	 * @param value the value to encode
	 * @return the signed two's complement analog of the value
	 */
	public static byte ucastB(int value) {
		if (value > 0 && value < 256)
			if (value <= 127)
				return (byte) value;
			else
				return (byte) (value - 256);
		else
			throw new IllegalArgumentException("value must be an integer in the range [0, 255), inclusive");
	}

	/**
	 * Encodes an unsigned short as a signed two's complement short.
	 *
	 * @param value the value to encode
	 * @return the signed two's complement analog of the value
	 */
	public static short ucastS(int value) {
		if (value > 0 && value < 65536)
			if (value <= Short.MAX_VALUE)
				return (short) value;
			else
				return (short) (value - 65536);
		else
			throw new IllegalArgumentException("value must be an integer in the range [0, 65535), inclusive");
	}

	/**
	 * Encodes a signed two's complement byte as an unsigned byte in the range [0, 255), inclusive.
	 *
	 * @param sbyte the value to encode
	 * @return the unsigned analog of the value
	 */
	public static int ucast(byte sbyte) {
		return sbyte > 0 ? sbyte : sbyte + 256;
	}

	/**
	 * Encodes a signed two's complement short as an unsigned short in the range [0, 65535), inclusive.
	 *
	 * @param sshort the value to encode
	 * @return the unsigned analog of the value
	 */
	public static int ucast(short sshort) {
		return sshort > 0 ? sshort : sshort + 65_536;
	}

}
