package jcla.compiler.token.hint;

/**
 * @author link
 */
public enum Hints {
	;

	// naked Tokens
	public static final Hint RESERVED   = new Hint("reserved");
	public static final Hint MODIFIER   = new Hint("modifier");
	public static final Hint DIRECTIVE  = new Hint("directive");
	public static final Hint TYPE       = new Hint("type");
	public static final Hint PRIMITIVE  = new Hint("primitive");
	public static final Hint VISIBILITY = new Hint("access");
	// indicates this Token is a nested Token
	public static final Hint NESTED = new Hint("nested");

	// LITERALS
	public static final Hint INTEGER          = new Hint("integer");
	public static final Hint LONG_INTEGER     = new Hint("long");
	public static final Hint FLOATING_POINT   = new Hint("floating-point");
	public static final Hint DOUBLE_PRECISION = new Hint("double-precision");
	public static final Hint CHARACTER        = new Hint("character");
	public static final Hint STRING           = new Hint("string");

	// LITERAL types
	public static final Hint DECIMAL     = new Hint("decimal");
	public static final Hint HEXADECIMAL = new Hint("hexadecimal");
	public static final Hint OCTAL       = new Hint("octal");
	public static final Hint BINARY      = new Hint("binary");

	// LITERAL suffixes
	public static final Hint LONG_SUFFIX   = new Hint("long_suffix");
	public static final Hint FLOAT_SUFFIX  = new Hint("float_suffix");
	public static final Hint DOUBLE_SUFFIX = new Hint("double_suffix");

	// LITERAL indicators
	public static final Hint BINARY_EXPONENT  = new Hint("binary_power");
	public static final Hint DECIMAL_EXPONENT = new Hint("exponent");

}
