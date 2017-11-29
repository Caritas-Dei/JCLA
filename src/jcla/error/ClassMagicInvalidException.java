package jcla.error;

/**
 * @author link
 */
public class ClassMagicInvalidException extends RuntimeException {

	public ClassMagicInvalidException(int magic) {
		super(Integer.toHexString(magic));
	}

}
