package jcla.compiler.lang.production.token;

/**
 * @author link
 */
public enum Tokens {
	;

	// Keywords
	public static final Keyword ABSTRACT = new Keyword("abstract");
	public static final Keyword ASSERT = new Keyword("assert");
	public static final Keyword BOOLEAN = new Keyword("boolean");
	public static final Keyword BREAK = new Keyword("break");
	public static final Keyword BYTE = new Keyword("byte");
	public static final Keyword CASE = new Keyword("case");
	public static final Keyword CATCH = new Keyword("catch");
	public static final Keyword CHAR = new Keyword("char");
	public static final Keyword CLASS = new Keyword("class");
	public static final Keyword CONST = new Keyword("const");
	public static final Keyword CONTINUE = new Keyword("continue");
	public static final Keyword DEFAULT = new Keyword("default");
	public static final Keyword DO = new Keyword("do");
	public static final Keyword DOUBLE = new Keyword("double");
	public static final Keyword ELSE = new Keyword("else");
	public static final Keyword ENUM = new Keyword("enum");
	public static final Keyword EXTENDS = new Keyword("extends");
	public static final Keyword FINAL = new Keyword("final");
	public static final Keyword FINALLY = new Keyword("finally");
	public static final Keyword FLOAT = new Keyword("float");
	public static final Keyword FOR = new Keyword("for");
	public static final Keyword IF = new Keyword("if");
	public static final Keyword GOTO = new Keyword("goto");
	public static final Keyword IMPLEMENTS = new Keyword("implements");
	public static final Keyword IMPORT = new Keyword("import");
	public static final Keyword INSTANCEOF = new Keyword("instanceof");
	public static final Keyword INT = new Keyword("int");
	public static final Keyword INTERFACE = new Keyword("interface");
	public static final Keyword LONG = new Keyword("long");
	public static final Keyword NATIVE = new Keyword("native");
	public static final Keyword NEW = new Keyword("new");
	public static final Keyword PACKAGE = new Keyword("package");
	public static final Keyword PRIVATE = new Keyword("private");
	public static final Keyword PROTECTED = new Keyword("protected");
	public static final Keyword PUBLIC = new Keyword("public");
	public static final Keyword RETURN = new Keyword("return");
	public static final Keyword SHORT = new Keyword("short");
	public static final Keyword STATIC = new Keyword("static");
	public static final Keyword STRICTFP = new Keyword("strictfp");
	public static final Keyword SUPER = new Keyword("super");
	public static final Keyword SWITCH = new Keyword("switch");
	public static final Keyword SYNCHRONIZED = new Keyword("synchronized");
	public static final Keyword THIS = new Keyword("this");
	public static final Keyword THROW = new Keyword("throw");
	public static final Keyword THROWS = new Keyword("throws");
	public static final Keyword TRANSIENT = new Keyword("transient");
	public static final Keyword TRY = new Keyword("try");
	public static final Keyword VOID = new Keyword("void");
	public static final Keyword VOLATILE = new Keyword("volatile");
	public static final Keyword WHILE = new Keyword("while");
	public static final Keyword UNDERSCORE = new Keyword("_");

	// Separators
	public static final Separator LEFT_PARENTHESIS = new Separator("(");
	public static final Separator RIGHT_PARENTHESIS = new Separator(")");
	public static final Separator LEFT_BRACKET = new Separator("{");
	public static final Separator RIGHT_BRACKET = new Separator("}");
	public static final Separator LEFT_SQUARE_BRACKET = new Separator("[");
	public static final Separator RIGHT_SQUARE_BRACKET = new Separator("]");
	public static final Separator SEMICOLON = new Separator(";");
	public static final Separator COMMA = new Separator(",");
	public static final Separator DOT = new Separator(".");
	public static final Separator DOT_DOT_DOT = new Separator("...");
	public static final Separator AT = new Separator("@");
	public static final Separator COLON_COLON = new Separator("::");

	// Operators
	public static final Operator EQUAL_SIGN = new Operator("=");
	public static final Operator GREATER_THAN = new Operator(">");
	public static final Operator LESS_THAN = new Operator("<");
	public static final Operator EXCLAMATION_POINT = new Operator("!");
	public static final Operator TILDA = new Operator("~");
	public static final Operator QUESTION_MARK = new Operator("?");
	public static final Operator COLON = new Operator(":");
	public static final Operator LAMBDA = new Operator("->");
	public static final Operator EQUALS = new Operator("==");
	public static final Operator GREATER_THAN_OR_EQUAL = new Operator(">=");
	public static final Operator LESS_THAN_OR_EQUAL = new Operator("<=");
	public static final Operator NOT_EQUAL = new Operator("!=");
	public static final Operator CONDITIONAL_AND = new Operator("&&");
	public static final Operator CONDITIONAL_OR = new Operator("||");
	public static final Operator INCREMENT = new Operator("++");
	public static final Operator DECREMENT = new Operator("--");
	public static final Operator ADD = new Operator("+");
	public static final Operator SUBTRACT = new Operator("-");
	public static final Operator MULTIPLY = new Operator("*");
	public static final Operator DIVIDE = new Operator("/");
	public static final Operator AMPERSAND = new Operator("&");
	public static final Operator PIPE = new Operator("|");
	public static final Operator CARET = new Operator("^");
	public static final Operator PERCENT_SIGN = new Operator("%");
	public static final Operator LEFT_SHIFT = new Operator("<<");
	public static final Operator RIGHT_SHIFT = new Operator(">>");
	public static final Operator UNSIGNED_RIGHT_SHIFT = new Operator(">>>");
	public static final Operator INCREMENT_ASSIGN = new Operator("+=");
	public static final Operator DECREMENT_ASSIGN = new Operator("-=");
	public static final Operator MULTIPLY_ASSIGN = new Operator("*=");
	public static final Operator DIVIDE_ASSIGN = new Operator("/=");
	public static final Operator AND_EQUALS = new Operator("&=");
	public static final Operator OR_EQUALS = new Operator("|=");
	public static final Operator XOR_EQUALS = new Operator("^=");
	public static final Operator MODULO_EQUALS = new Operator("%=");
	public static final Operator LEFT_SHIFT_EQUALS = new Operator("<<=");
	public static final Operator RIGHT_SHIFT_EQUALS = new Operator(">>=");
	public static final Operator UNSIGNED_RIGHT_SHIFT_EQUALS = new Operator(">>>=");

	// Literals
	public static final Literal NULL = new Literal("null");
	public static final Literal TRUE = new Literal("true");
	public static final Literal FALSE = new Literal("false");

	public static final Literal EMPTY_STRING = new Literal("");

	// Common Integer Literals
	public static final Literal ZERO = new Literal("0");
	public static final Literal ONE = new Literal("1");
	public static final Literal TWO = new Literal("2");
	public static final Literal THREE = new Literal("3");
	public static final Literal FOUR = new Literal("4");
	public static final Literal FIVE = new Literal("5");
	public static final Literal SIX = new Literal("6");
	public static final Literal SEVEN = new Literal("7");
	public static final Literal EIGHT = new Literal("8");
	public static final Literal NINE = new Literal("9");

}
