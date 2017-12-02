package jcla.compiler.token.hint;

/**
 * @author link
 */
public enum Hints {
	;

	// all Tokens
	public static final Hint RESERVED   = new Hint("reserved");
	public static final Hint MODIFIER   = new Hint("modifier");
	public static final Hint TYPE       = new Hint("type");
	public static final Hint DIRECTIVE  = new Hint("directive");
	public static final Hint PRIMITIVE  = new Hint("primitive");
	public static final Hint VISIBILITY = new Hint("access");

	// LITERALS
	public static final Hint CHARACTER = new Hint("character");
	public static final Hint STRING    = new Hint("string");
	public static final Hint NUMBER    = new Hint("number");

}
