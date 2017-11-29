package jcla.error;

import jcla.classfile.constant.Constant;

/**
 * @author link
 */
public class ClassAttributeInvalidException extends RuntimeException {

	public ClassAttributeInvalidException(Constant.UTF8 attributeName) {
		super("Invalid attribute: \"" + attributeName.getValue() + "\"");
	}

}
