package jcla.compiler.token;

import static jcla.compiler.token.hint.Hints.*;
import static jcla.compiler.token.tag.Tags.*;

/**
 * @author link
 */
public enum Tokens {
	;

	// Keywords
	public static final Token ABSTRACT     = new Token("abstract", KEYWORD, MODIFIER);
	public static final Token ASSERT       = new Token("assert", KEYWORD, DIRECTIVE);
	public static final Token BOOLEAN      = new Token("boolean", KEYWORD, MODIFIER, TYPE, PRIMITIVE);
	public static final Token BREAK        = new Token("break", KEYWORD, DIRECTIVE);
	public static final Token BYTE         = new Token("byte", KEYWORD, MODIFIER, TYPE, PRIMITIVE);
	public static final Token CASE         = new Token("case", KEYWORD, DIRECTIVE);
	public static final Token CATCH        = new Token("catch", KEYWORD, DIRECTIVE);
	public static final Token CHAR         = new Token("char", KEYWORD, MODIFIER, TYPE, PRIMITIVE);
	public static final Token CLASS        = new Token("class", KEYWORD, MODIFIER, TYPE);
	public static final Token CONST        = new Token("const", KEYWORD, MODIFIER, RESERVED);
	public static final Token CONTINUE     = new Token("continue", KEYWORD, DIRECTIVE);
	public static final Token DEFAULT      = new Token("default", KEYWORD, DIRECTIVE);
	public static final Token DO           = new Token("do", KEYWORD, DIRECTIVE);
	public static final Token DOUBLE       = new Token("double", KEYWORD, MODIFIER, TYPE, PRIMITIVE);
	public static final Token ELSE         = new Token("else", KEYWORD, DIRECTIVE);
	public static final Token ENUM         = new Token("enum", KEYWORD, MODIFIER, TYPE);
	public static final Token EXTENDS      = new Token("extends", KEYWORD, DIRECTIVE);
	public static final Token FINAL        = new Token("final", KEYWORD, MODIFIER);
	public static final Token FINALLY      = new Token("finally", KEYWORD, DIRECTIVE);
	public static final Token FLOAT        = new Token("float", KEYWORD, MODIFIER, TYPE, PRIMITIVE);
	public static final Token FOR          = new Token("for", KEYWORD, DIRECTIVE);
	public static final Token IF           = new Token("if", KEYWORD, DIRECTIVE);
	public static final Token GOTO         = new Token("goto", KEYWORD, DIRECTIVE, RESERVED);
	public static final Token IMPLEMENTS   = new Token("implements", KEYWORD, DIRECTIVE);
	public static final Token IMPORT       = new Token("import", KEYWORD, DIRECTIVE);
	public static final Token INSTANCEOF   = new Token("instanceof", KEYWORD, DIRECTIVE);
	public static final Token INT          = new Token("int", KEYWORD, MODIFIER, TYPE, PRIMITIVE);
	public static final Token INTERFACE    = new Token("interface", KEYWORD, MODIFIER, TYPE);
	public static final Token LONG         = new Token("long", KEYWORD, MODIFIER, TYPE, PRIMITIVE);
	public static final Token NATIVE       = new Token("native", KEYWORD, MODIFIER);
	public static final Token NEW          = new Token("new", KEYWORD, DIRECTIVE);
	public static final Token PACKAGE      = new Token("package", KEYWORD, DIRECTIVE);
	public static final Token PRIVATE      = new Token("private", KEYWORD, MODIFIER, VISIBILITY);
	public static final Token PROTECTED    = new Token("protected", KEYWORD, MODIFIER, VISIBILITY);
	public static final Token PUBLIC       = new Token("public", KEYWORD, MODIFIER, VISIBILITY);
	public static final Token RETURN       = new Token("return", KEYWORD, DIRECTIVE);
	public static final Token SHORT        = new Token("short", KEYWORD, MODIFIER, TYPE, PRIMITIVE);
	public static final Token STATIC       = new Token("static", KEYWORD, MODIFIER);
	public static final Token STRICTFP     = new Token("strictfp", KEYWORD, MODIFIER, DIRECTIVE);
	public static final Token SUPER        = new Token("super", KEYWORD, MODIFIER, TYPE);
	public static final Token SWITCH       = new Token("switch", KEYWORD, DIRECTIVE);
	public static final Token SYNCHRONIZED = new Token("synchronized", KEYWORD, MODIFIER, DIRECTIVE);
	public static final Token THIS         = new Token("this", KEYWORD, TYPE);
	public static final Token THROW        = new Token("throw", KEYWORD, DIRECTIVE);
	public static final Token THROWS       = new Token("throws", KEYWORD, DIRECTIVE);
	public static final Token TRANSIENT    = new Token("transient", KEYWORD, MODIFIER);
	public static final Token TRY          = new Token("try", KEYWORD, DIRECTIVE);
	public static final Token VOID         = new Token("void", KEYWORD, MODIFIER);
	public static final Token VOLATILE     = new Token("volatile", KEYWORD, MODIFIER);
	public static final Token WHILE        = new Token("while", KEYWORD, DIRECTIVE);
	public static final Token UNDERSCORE   = new Token("_", KEYWORD, RESERVED);

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
	public static final Token ASSIGNMENT    = new Token("=", OPERATOR);
	public static final Token GREATER_THAN          = new Token(">", OPERATOR);
	public static final Token LESS_THAN             = new Token("<", OPERATOR);
	public static final Token EXCLAMATION           = new Token("!", OPERATOR);
	public static final Token TILDA                 = new Token("~", OPERATOR);
	public static final Token QUESTION_MARK         = new Token("?", OPERATOR);
	public static final Token COLON                 = new Token(":", OPERATOR);
	public static final Token LAMBDA                = new Token("->", OPERATOR);
	public static final Token EQUALS                = new Token("==", OPERATOR);
	public static final Token GREATER_THAN_OR_EQUAL = new Token(">=", OPERATOR);
	public static final Token LESS_THAN_OR_EQUAL    = new Token("<=", OPERATOR);
	public static final Token NOT_EQUAL             = new Token("!=", OPERATOR);
	public static final Token AND                   = new Token("&&", OPERATOR);
	public static final Token OR                    = new Token("||", OPERATOR);
	public static final Token INCREMENT               = new Token("++", OPERATOR);
	public static final Token DECREMENT               = new Token("--", OPERATOR);
	public static final Token ADD                     = new Token("+", OPERATOR);
	public static final Token SUBTRACT                = new Token("-", OPERATOR);
	public static final Token MULTIPLY                = new Token("*", OPERATOR);
	public static final Token DIVIDE                  = new Token("/", OPERATOR);
	public static final Token AMPERSAND            = new Token("&", OPERATOR);
	public static final Token PIPE                 = new Token("|", OPERATOR);
	public static final Token CARET                = new Token("^", OPERATOR);
	public static final Token PERCENT_SIGN         = new Token("%", OPERATOR);
	public static final Token LEFT_SHIFT                     = new Token("<<", OPERATOR);
	public static final Token RIGHT_SHIFT                    = new Token(">>", OPERATOR);
	public static final Token UNSIGNED_RIGHT_SHIFT           = new Token(">>>", OPERATOR);
	public static final Token INCREMENT_ASSIGN               = new Token("+=", OPERATOR);
	public static final Token DECREMENT_ASSIGN               = new Token("-=", OPERATOR);
	public static final Token MULTIPLY_ASSIGN                = new Token("*=", OPERATOR);
	public static final Token DIVIDE_ASSIGN                  = new Token("/=", OPERATOR);
	public static final Token AND__ASIGN                   = new Token("&=", OPERATOR);
	public static final Token OR__ASSIGN                   = new Token("|=", OPERATOR);
	public static final Token XOR__ASSIGN                  = new Token("^=", OPERATOR);
	public static final Token MODULO__ASSIGN               = new Token("%=", OPERATOR);
	public static final Token LEFT_SHIFT__ASSIGN           = new Token("<<=", OPERATOR);
	public static final Token RIGHT_SHIFT__ASSIGN          = new Token(">>=", OPERATOR);
	public static final Token UNSIGNED_RIGHT_SHIFT__ASSIGN = new Token(">>>=", OPERATOR);

	// Literals
	public static final Token NULL  = new Token("null", LITERAL);
	public static final Token TRUE  = new Token("true", LITERAL);
	public static final Token FALSE = new Token("false", LITERAL);

	// Numbers
	public static final Token ZERO  = new Token("0", LITERAL, NUMBER);
	public static final Token ONE   = new Token("1", LITERAL, NUMBER);
	public static final Token TWO   = new Token("2", LITERAL, NUMBER);
	public static final Token THREE = new Token("3", LITERAL, NUMBER);
	public static final Token FOUR  = new Token("4", LITERAL, NUMBER);
	public static final Token FIVE  = new Token("5", LITERAL, NUMBER);
	public static final Token SIX   = new Token("6", LITERAL, NUMBER);
	public static final Token SEVEN = new Token("7", LITERAL, NUMBER);
	public static final Token EIGHT = new Token("8", LITERAL, NUMBER);
	public static final Token NINE  = new Token("9", LITERAL, NUMBER);

}
