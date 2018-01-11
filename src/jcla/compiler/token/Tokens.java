package jcla.compiler.token;

import static jcla.compiler.lang.grammar.Grammar.*;

/**
 * @author link
 */
public enum Tokens {
	;

	// Keywords
	public static final Token ABSTRACT     = new Token("abstract", KEYWORD);
	public static final Token ASSERT       = new Token("assert", KEYWORD);
	public static final Token BOOLEAN      = new Token("boolean", KEYWORD);
	public static final Token BREAK        = new Token("break", KEYWORD);
	public static final Token BYTE         = new Token("byte", KEYWORD);
	public static final Token CASE         = new Token("case", KEYWORD);
	public static final Token CATCH        = new Token("catch", KEYWORD);
	public static final Token CHAR         = new Token("char", KEYWORD);
	public static final Token CLASS        = new Token("class", KEYWORD);
	public static final Token CONST        = new Token("const", KEYWORD);
	public static final Token CONTINUE     = new Token("continue", KEYWORD);
	public static final Token DEFAULT      = new Token("default", KEYWORD);
	public static final Token DO           = new Token("do", KEYWORD);
	public static final Token DOUBLE       = new Token("double", KEYWORD);
	public static final Token ELSE         = new Token("else", KEYWORD);
	public static final Token ENUM         = new Token("enum", KEYWORD);
	public static final Token EXTENDS      = new Token("extends", KEYWORD);
	public static final Token FINAL        = new Token("final", KEYWORD);
	public static final Token FINALLY      = new Token("finally", KEYWORD);
	public static final Token FLOAT        = new Token("float", KEYWORD);
	public static final Token FOR          = new Token("for", KEYWORD);
	public static final Token IF           = new Token("if", KEYWORD);
	public static final Token GOTO         = new Token("goto", KEYWORD);
	public static final Token IMPLEMENTS   = new Token("implements", KEYWORD);
	public static final Token IMPORT       = new Token("import", KEYWORD);
	public static final Token INSTANCEOF   = new Token("instanceof", KEYWORD);
	public static final Token INT          = new Token("int", KEYWORD);
	public static final Token INTERFACE    = new Token("interface", KEYWORD);
	public static final Token LONG         = new Token("long", KEYWORD);
	public static final Token NATIVE       = new Token("native", KEYWORD);
	public static final Token NEW          = new Token("new", KEYWORD);
	public static final Token PACKAGE      = new Token("package", KEYWORD);
	public static final Token PRIVATE      = new Token("private", KEYWORD);
	public static final Token PROTECTED    = new Token("protected", KEYWORD);
	public static final Token PUBLIC       = new Token("public", KEYWORD);
	public static final Token RETURN       = new Token("return", KEYWORD);
	public static final Token SHORT        = new Token("short", KEYWORD);
	public static final Token STATIC       = new Token("static", KEYWORD);
	public static final Token STRICTFP     = new Token("strictfp", KEYWORD);
	public static final Token SUPER        = new Token("super", KEYWORD);
	public static final Token SWITCH       = new Token("switch", KEYWORD);
	public static final Token SYNCHRONIZED = new Token("synchronized", KEYWORD);
	public static final Token THIS         = new Token("this", TYPE);
	public static final Token THROW        = new Token("throw", KEYWORD);
	public static final Token THROWS       = new Token("throws", KEYWORD);
	public static final Token TRANSIENT    = new Token("transient", KEYWORD);
	public static final Token TRY          = new Token("try", KEYWORD);
	public static final Token VOID         = new Token("void", KEYWORD);
	public static final Token VOLATILE     = new Token("volatile", KEYWORD);
	public static final Token WHILE        = new Token("while", KEYWORD);
	public static final Token UNDERSCORE   = new Token("_", KEYWORD);

	// Separators
	public static final Token LEFT_PARENTHESIS     = new Token("(", SEPARATOR);
	public static final Token RIGHT_PARENTHESIS    = new Token(")", SEPARATOR);
	public static final Token LEFT_BRACKET         = new Token("{", SEPARATOR);
	public static final Token RIGHT_BRACKET        = new Token("}", SEPARATOR);
	public static final Token LEFT_SQUARE_BRACKET  = new Token("[", SEPARATOR);
	public static final Token RIGHT_SQUARE_BRACKET = new Token("]", SEPARATOR);
	public static final Token SEMICOLON            = new Token(";", SEPARATOR);
	public static final Token COMMA                = new Token(",", SEPARATOR);
	public static final Token DOT                  = new Token(".", SEPARATOR);
	public static final Token DOT_DOT_DOT          = new Token("...", SEPARATOR);
	public static final Token AT                   = new Token("@", SEPARATOR);
	public static final Token COLON_COLON          = new Token("::", SEPARATOR);

	// Operators
	public static final Token EQUAL_SIGN            = new Token("=", OPERATOR);
	public static final Token GREATER_THAN          = new Token(">", OPERATOR);
	public static final Token LESS_THAN             = new Token("<", OPERATOR);
	public static final Token EXCLAMATION_POINT     = new Token("!", OPERATOR);
	public static final Token TILDA                 = new Token("~", OPERATOR);
	public static final Token QUESTION_MARK         = new Token("?", OPERATOR);
	public static final Token COLON                 = new Token(":", OPERATOR);
	public static final Token LAMBDA                = new Token("->", OPERATOR);
	public static final Token EQUALS                = new Token("==", OPERATOR);
	public static final Token GREATER_THAN_OR_EQUAL = new Token(">=", OPERATOR);
	public static final Token LESS_THAN_OR_EQUAL    = new Token("<=", OPERATOR);
	public static final Token NOT_EQUAL             = new Token("!=", OPERATOR);
	public static final Token CONDITIONAL_AND       = new Token("&&", OPERATOR);
	public static final Token CONDITIONAL_OR        = new Token("||", OPERATOR);
	public static final Token INCREMENT             = new Token("++", OPERATOR);
	public static final Token DECREMENT             = new Token("--", OPERATOR);
	public static final Token ADD                   = new Token("+", OPERATOR);
	public static final Token SUBTRACT              = new Token("-", OPERATOR);
	public static final Token MULTIPLY              = new Token("*", OPERATOR);
	public static final Token DIVIDE                = new Token("/", OPERATOR);
	public static final Token AMPERSAND                    = new Token("&", OPERATOR);
	public static final Token PIPE                         = new Token("|", OPERATOR);
	public static final Token CARET                        = new Token("^", OPERATOR);
	public static final Token PERCENT_SIGN                 = new Token("%", OPERATOR);
	public static final Token LEFT_SHIFT                   = new Token("<<", OPERATOR);
	public static final Token RIGHT_SHIFT                  = new Token(">>", OPERATOR);
	public static final Token UNSIGNED_RIGHT_SHIFT         = new Token(">>>", OPERATOR);
	public static final Token INCREMENT_ASSIGN             = new Token("+=", OPERATOR);
	public static final Token DECREMENT_ASSIGN             = new Token("-=", OPERATOR);
	public static final Token MULTIPLY_ASSIGN              = new Token("*=", OPERATOR);
	public static final Token DIVIDE_ASSIGN                = new Token("/=", OPERATOR);
	public static final Token AND_EQUALS                  = new Token("&=", OPERATOR);
	public static final Token OR_EQUALS                   = new Token("|=", OPERATOR);
	public static final Token XOR_EQUALS                  = new Token("^=", OPERATOR);
	public static final Token MODULO_EQUALS               = new Token("%=", OPERATOR);
	public static final Token LEFT_SHIFT_EQUALS           = new Token("<<=", OPERATOR);
	public static final Token RIGHT_SHIFT_EQUALS          = new Token(">>=", OPERATOR);
	public static final Token UNSIGNED_RIGHT_SHIFT_EQUALS = new Token(">>>=", OPERATOR);

	// Literals
	public static final Token NULL  = new Token("null", NULL_LITERAL);
	public static final Token TRUE  = new Token("true", BOOLEAN_LITERAL);
	public static final Token FALSE = new Token("false", BOOLEAN_LITERAL);

	public static final Token EMPTY_STRING = new Token("", STRING_LITERAL);

	// Common Integer Literals
	public static final Token ZERO  = new Token("0", INTEGER_LITERAL);
	public static final Token ONE   = new Token("1", INTEGER_LITERAL);
	public static final Token TWO   = new Token("2", INTEGER_LITERAL);
	public static final Token THREE = new Token("3", INTEGER_LITERAL);
	public static final Token FOUR  = new Token("4", INTEGER_LITERAL);
	public static final Token FIVE  = new Token("5", INTEGER_LITERAL);
	public static final Token SIX   = new Token("6", INTEGER_LITERAL);
	public static final Token SEVEN = new Token("7", INTEGER_LITERAL);
	public static final Token EIGHT = new Token("8", INTEGER_LITERAL);
	public static final Token NINE  = new Token("9", INTEGER_LITERAL);

}
