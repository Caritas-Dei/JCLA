package jcla.compiler.token;

import java.util.LinkedList;
import java.util.List;

import static jcla.compiler.token.Tokens.*;
import static jcla.compiler.token.hint.Hints.*;
import static jcla.compiler.token.tag.Tags.*;

/**
 * @author link
 */
public final class LexicalAnalyzer {

	// special characters
	private static final char SP = ' ';
	private static final char BS = '\b';
	private static final char HT = '\t';
	private static final char LF = '\n';
	private static final char FF = '\f';
	private static final char CR = '\r';

	/**
	 * Analyzes the given String character by character to create Tokens. If the input was a comment, a List with only
	 * null is returned.
	 *
	 * @param input the input to analyze
	 * @return a list of tokens from the input, or null if the input was a comment
	 */
	public List<Token> analyze(String input) {
		List<Token> result = new LinkedList<>();
		// create a buffer for the identifier the size of the input.
		// we don't know if the input is a single token or multiple,
		// so we use input.length()
		char[] in = input.toCharArray();

		StringBuilder buffer = new StringBuilder(in.length);

		// single line comment
		boolean singleline = false;
		// multi line comment
		boolean multiline = false;
		// strings
		boolean string = false;
		// characters
		boolean character = false;
		// escapes
		boolean escape = false;

		char previous = 0;

		for (char current : in) {
			switch( current ) {
				// the unicode escape
				case 'u':
					if (escape) {
						buffer.append('\\');
					}
					buffer.append('u');
					break;
				// basic escapes
				case '\\':
					if (!singleline && !multiline && previous != '\\' && (string || character)) {
						escape = true;
					}
					break;
				// characters
				case '\'':
					if (!singleline && !multiline && !string) {
						if (!escape)
							character = !character;
						else {
							buffer.append('\'');
							escape = false;
							break;
						}

						if (!character) { // end character
							result.add(literalChar(buffer.toString()));
							buffer.delete(0, buffer.length());
						} else { // begin character
							// tokenize the buffer if it is occupied
							if (buffer.length() > 0)
								result.add(identify(buffer.toString()));
							buffer.delete(0, buffer.length());
						}
					}
					break;
				// strings
				case '"':
					if (!singleline && !multiline && !character) {
						if (!escape)
							string = !string;
						else {
							buffer.append('"');
							escape = false;
							break;
						}

						if (!string) { // end string
							result.add(literalString(buffer.toString()));
							buffer.delete(0, buffer.length());
						} else { // begin string
							// tokenize the buffer if it is occupied
							if (buffer.length() > 0)
								result.add(identify(buffer.toString()));
							buffer.delete(0, buffer.length());
						}
					}
					break;
				// special separators (single char)
				case '(':
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
				case ',':
					if (!singleline && !multiline && !string && !character) {
						// tokenize the buffer if it is occupied
						if (buffer.length() > 0)
							result.add(identify(buffer.toString()));
						buffer.delete(0, buffer.length());
						result.add(identify(String.valueOf(current)));
					}

					break;

				// comments
				case '/':
					if (!string && !character) {
						if (previous == '/')
							singleline = true;
						else if (previous == '*') {
							multiline = false;
						}
					}

					break;
				case '*':
					if (!string && !character) {
						if (previous == '/') {
							multiline = true;
						}
					}

					break;
				// white space
				case CR:
				case LF:
					if (!string && !character)
						singleline = false;
				case HT:
				case FF:
					if (string || character)
						buffer.append(current);
					break;
				case SP:
					if (buffer.length() > 0 && !singleline && !multiline && !string && !character) {
						result.add(identify(buffer.toString()));
					}
					buffer.delete(0, buffer.length());

					break;
				default:
					if (!singleline && !multiline)
						buffer.append(current);
			}

			previous = current;
		}

		return result;
	}

	/**
	 * Takes a single input as a String and identifies the token contained in it. If there are multiple tokens in the
	 * String, the first token will be returned.
	 *
	 * @param input the input to analyze
	 * @return a token representing the first token in the input
	 */
	public Token identify(String input) {
		// Tokens
		//
		// -- identifier
		//    IdentifierChars, !Literal
		//
		// -- keyword
		//    finite set of character permutations (keywords)
		//
		// -- literal
		//    IntegerLiteral, FloatingPointLiteral,
		//    BooleanLiteral, CharacterLiteral,
		//    StringLiteral, NullLiteral
		//
		// -- separator
		//    ( , ) , { , } , [ , ] , ; , . , ... , @ , ::
		//
		// -- operator
		//    =   >   <   !   ~   ?   :   ->
		//    ==  >=  <=  !=  &&  ||  ++  --
		//	  +   -   *   /   &   |   ^   %   <<   >>   >>>
		//    +=  -=  *=  /=  &=  |=  ^=  %=  <<=  >>=  >>>=

		switch( input ) {
			case "abstract":
				return ABSTRACT;
			case "assert":
				return ASSERT;
			case "boolean":
				return BOOLEAN;
			case "break":
				return BREAK;
			case "byte":
				return BYTE;
			case "case":
				return CASE;
			case "catch":
				return CATCH;
			case "char":
				return CHAR;
			case "class":
				return CLASS;
			case "const":
				return CONST;
			case "continue":
				return CONTINUE;
			case "default":
				return DEFAULT;
			case "do":
				return DO;
			case "double":
				return DOUBLE;
			case "else":
				return ELSE;
			case "enum":
				return ENUM;
			case "extends":
				return EXTENDS;
			case "final":
				return FINAL;
			case "finally":
				return FINALLY;
			case "float":
				return FLOAT;
			case "for":
				return FOR;
			case "if":
				return IF;
			case "goto":
				return GOTO;
			case "implements":
				return IMPLEMENTS;
			case "import":
				return IMPORT;
			case "instanceof":
				return INSTANCEOF;
			case "int":
				return INT;
			case "interface":
				return INTERFACE;
			case "long":
				return LONG;
			case "native":
				return NATIVE;
			case "new":
				return NEW;
			case "package":
				return PACKAGE;
			case "private":
				return PRIVATE;
			case "protected":
				return PROTECTED;
			case "public":
				return PUBLIC;
			case "return":
				return RETURN;
			case "short":
				return SHORT;
			case "static":
				return STATIC;
			case "strictfp":
				return STRICTFP;
			case "super":
				return SUPER;
			case "switch":
				return SWITCH;
			case "synchronized":
				return SYNCHRONIZED;
			case "this":
				return THIS;
			case "throw":
				return THROW;
			case "throws":
				return THROWS;
			case "transient":
				return TRANSIENT;
			case "try":
				return TRY;
			case "void":
				return VOID;
			case "volatile":
				return VOLATILE;
			case "while":
				return WHILE;
			case "_":
				return UNDERSCORE;
			case "(":
				return LEFT_PARENTHESIS;
			case ")":
				return RIGHT_PARENTHESIS;
			case "{":
				return LEFT_BRACKET;
			case "}":
				return RIGHT_BRACKET;
			case "[":
				return LEFT_SQUARE_BRACKET;
			case "]":
				return RIGHT_SQUARE_BRACKET;
			case ";":
				return SEMICOLON;
			case ",":
				return COMMA;
			case ".":
				return DOT;
			case "...":
				return DOT_DOT_DOT;
			case "@":
				return AT;
			case "::":
				return COLON_COLON;
			case "=":
				return EQUALS;
			case ">":
				return GREATER_THAN;
			case "<":
				return LESS_THAN;
			case "!":
				return NOT;
			case "~":
				return INVERT;
			case "?":
				return TERNARY_BODY;
			case ":":
				return TERNARY_TAIL;
			case "->":
				return LAMBDA;
			case "==":
				return COMPARISON;
			case ">=":
				return GREATER_THAN_OR_EQUAL;
			case "<=":
				return LESS_THAN_OR_EQUAL;
			case "!=":
				return NOT_EQUAL;
			case "&&":
				return AND;
			case "||":
				return OR;
			case "++":
				return INCREMENT;
			case "--":
				return DECREMENT;
			case "+":
				return ADD;
			case "-":
				return SUBTRACT;
			case "*":
				return MULTIPLY;
			case "/":
				return DIVIDE;
			case "&":
				return BITWISE_AND;
			case "|":
				return BITWISE_OR;
			case "^":
				return BITWISE_XOR;
			case "%":
				return MODULO;
			case "<<":
				return LEFT_BITSHIFT;
			case ">>":
				return RIGHT_BITSHIFT;
			case ">>>":
				return UNSIGNED_RIGHT_BITSHIFT;
			case "+=":
				return INCREMENT_ASSIGN;
			case "-=":
				return DECREMENT_ASSIGN;
			case "*=":
				return MULTIPLY_ASSIGN;
			case "/=":
				return DIVIDE_ASSIGN;
			case "&=":
				return BITWISE_AND_ASSIGN;
			case "|=":
				return BITWISE_OR_ASSIGN;
			case "^=":
				return BITWISE_XOR_ASSIGN;
			case "%=":
				return MODULO_ASSIGN;
			case "<<=":
				return LEFT_BITSHIFT_ASSIGN;
			case ">>=":
				return RIGHT_BITSHIFT_ASSIGN;
			case ">>>=":
				return UNSIGNED_RIGHT_BITSHIFT_ASSIGN;
			// Literals and Identifiers
			case "null":
				return NULL;
			case "true":
				return TRUE;
			case "false":
				return FALSE;
			default:
				// identifiers cannot start with a digit, dot, apostrophe, or quotation mark.
				char first = input.charAt(0);
				if (!Character.isDigit(first) || first != '.' || first != '\'' || first != '"')
					return new Token(input, IDENTIFIER);

				// pre-processing to determine indices for
				// various number formats

				// hexadecimal index
				int x;
				if ((x = input.indexOf('x')) == -1)
					x = input.indexOf('X');

				// binary index
				int b;
				if ((b = input.indexOf('b')) == -1)
					b = input.indexOf('B');


				// dot index
				int dot = input.indexOf('.');

				int lastIndex = input.length() - 1;
				char last = input.charAt(lastIndex);

				boolean suffix = last == 'f' || last == 'f' || last == 'd' || last == 'D';
				boolean float32 = dot > -1 && (last == 'f' || last == 'F');
				boolean float64 = !float32 || (last == 'd' && last == 'D');
				// strictly integral (no suffix)
				boolean int32 = !float32 && !float64;
				boolean int64 = !int32 && (last == 'l' || last == 'L');

				if (int64) { // long
					if (x == 1) { // hexadecimal
						if (areHexDigits(input.substring(2, lastIndex - 1))) { // validation
							return new Token(input, LITERAL, LONG_INTEGER, HEXADECIMAL, LONG_SUFFIX);
						} else { // has an x, but not valid hex
							throw new IllegalArgumentException("Invalid hexadecimal long literal: " + input);
						}
					} else if (b == 1) { // binary
						if (isBinaryValue(input.substring(2, lastIndex - 1))) { // validation
							return new Token(input, LITERAL, LONG_INTEGER, BINARY, LONG_SUFFIX);
						} else {
							throw new IllegalArgumentException("Invalid binary long literal: " + input);
						}
					} else { // octal or decimal
						if (isDecimal(input.substring(0, lastIndex - 1))) {
							return new Token(input, LITERAL, LONG_INTEGER, DECIMAL, LONG_SUFFIX);
						} else if (isOctalNumber(input.substring(0, lastIndex - 1))) {
							return new Token(input, LITERAL, LONG_INTEGER, OCTAL, LONG_SUFFIX);
						} else {
							// TODO InvalidFormatException
							throw new IllegalArgumentException("Invalid long literal: " + input);
						}
					}
				} else if (int32) { // int or less
					if (x == 1) { // hexadecimal
						if (areHexDigits(input.substring(2, lastIndex))) { // validation
							return new Token(input, LITERAL, INTEGER, HEXADECIMAL);
						} else { // has an x, but not valid hex
							throw new IllegalArgumentException("Invalid hexadecimal integer literal: " + input);
						}
					} else if (b == 1) { // binary
						if (isBinaryValue(input.substring(2, lastIndex))) { // validation
							return new Token(input, LITERAL, INTEGER, BINARY);
						} else {
							throw new IllegalArgumentException("Invalid binary integer literal: " + input);
						}
					} else { // octal or decimal
						if (isDecimal(input.substring(0, lastIndex))) {
							return new Token(input, LITERAL, INTEGER, DECIMAL);
						} else if (isOctalNumber(input.substring(0, lastIndex))) {
							return new Token(input, LITERAL, INTEGER, OCTAL);
						} else {
							// TODO InvalidFormatException
							throw new IllegalArgumentException("Invalid integer literal: " + input);
						}
					}
				} else if (float64) { // double
					if (x == 1) { // hexadecimal
						if (areHexDigits(input.substring(2, suffix ? lastIndex - 1 : lastIndex))) { // validation
							// index of BinaryExponentIndicator
							int p;
							if ((p = input.lastIndexOf('p')) == -1)
								p = input.lastIndexOf('P');

							if (p > 2) { // binary exponent
								return suffix ? new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, DOUBLE_SUFFIX, HEXADECIMAL, BINARY_EXPONENT) : new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, HEXADECIMAL, BINARY_EXPONENT);
							} else if (p == -1) {
								return suffix ? new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, DOUBLE_SUFFIX, HEXADECIMAL) : new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, HEXADECIMAL);
							} else {
								throw new IllegalArgumentException("Invalid hexadecimal double literal: " + input);
							}
						} else { // has an x, but not valid hex
							throw new IllegalArgumentException("Invalid hexadecimal double literal: " + input);
						}
					} else { // decimal
						if (isDecimal(input.substring(0, lastIndex))) {
							// index of ExponentIndicator
							int e;
							if ((e = input.lastIndexOf('e')) == -1)
								e = input.lastIndexOf('E');

							if (e > 2) { // decimal exponent
								return suffix ? new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, DOUBLE_SUFFIX, DECIMAL, DECIMAL_EXPONENT) : new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, DECIMAL, DECIMAL_EXPONENT);
							} else if (e == -1) {
								return suffix ? new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, DOUBLE_SUFFIX, DECIMAL) : new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, DECIMAL);
							} else {
								throw new IllegalArgumentException("Invalid decimal double literal: " + input);
							}
						} else {
							// TODO InvalidFormatException
							throw new IllegalArgumentException("Invalid decimal double literal: " + input);
						}
					}
				} else if (float32) {
					if (x == 1) { // hexadecimal
						if (areHexDigits(input.substring(2, suffix ? lastIndex - 1 : lastIndex))) { // validation
							// index of BinaryExponentIndicator
							int p;
							if ((p = input.lastIndexOf('p')) == -1)
								p = input.lastIndexOf('P');

							if (p > 2) { // binary exponent
								return new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, DOUBLE_SUFFIX, HEXADECIMAL, BINARY_EXPONENT);
							} else if (p == -1) {
								return new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, DOUBLE_SUFFIX, HEXADECIMAL);
							} else {
								throw new IllegalArgumentException("Invalid hexadecimal float literal: " + input);
							}
						} else { // has an x, but not valid hex
							throw new IllegalArgumentException("Invalid hexadecimal float literal: " + input);
						}
					} else { // decimal
						if (isDecimal(input.substring(0, lastIndex))) {
							// index of ExponentIndicator
							int e;
							if ((e = input.lastIndexOf('e')) == -1)
								e = input.lastIndexOf('E');

							if (e > 2) { // decimal exponent
								return new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, DOUBLE_SUFFIX, DECIMAL, DECIMAL_EXPONENT);
							} else if (e == -1) {
								return new Token(input, LITERAL, FLOATING_POINT, DOUBLE_PRECISION, DOUBLE_SUFFIX, DECIMAL);
							} else {
								throw new IllegalArgumentException("Invalid decimal float literal: " + input);
							}
						} else {
							// TODO InvalidFormatException
							throw new IllegalArgumentException("Invalid decimal float literal: " + input);
						}
					}
				} else {
					// determined not a KEYWORD, LITERAL, or IDENTIFIER; invalid token
					throw new IllegalArgumentException("Invalid token: " + input);
				}
		}
	}

	public Token literalString(String literal) {
		char[] in = literal.toCharArray();

		if (in.length < 1)
			return new Token("\"\"", LITERAL, STRING);

		StringBuilder buffer = new StringBuilder(literal.length());

		char previous = '\0', current;

		for (int i = 0; i < in.length; i++) {
			current = in[i];

			switch( current ) {
				case '\\': // offset: i + 0
					if (previous == '\\') {
						buffer.append(current);
					} else {
						char next = in[i + 1];
						switch( next ) {
							case 'u': // offset: i + 1
								String four = literal.substring(i + 2, i + 6);
								if (areHexDigits(four))
									buffer.append(unicodeEscape("\\u" + four));
								else
									throw new IllegalArgumentException("Invalid unicode escape sequence: " + current + next + four);

								// skip
								i += 5;
								previous = '\0';
								break;
							case '0':
							case '1':
							case '2':
							case '3':
								String three = literal.substring(i + 1, i + 3);
								if (isOctalNumber(three)) {
									buffer.append(unicode(Integer.decode("0" + three)));
									// skip
									i += 3;
									previous = '\0';
								} else {
									String two = three.substring(0, 2);
									if (isOctalNumber(two)) {
										buffer.append(unicode(Integer.decode(two)));
										// skip
										i += 2;
										previous = '\0';
									} else {
										buffer.append(unicode(Integer.decode(String.valueOf(next))));
										// skip
										i++;
										previous = '\0';
									}
								}
								break;
							case '4':
							case '5':
							case '6':
							case '7':
								buffer.append(unicode(Integer.decode(String.valueOf(next))));
								// skip
								i += 1;
								previous = '\0';
								break;
							case 'b':
								buffer.append(BS);
								// skip
								i += 1;
								previous = '\0';
								break;
							case 't':
								buffer.append(HT);
								// skip
								i += 1;
								previous = '\0';
								break;
							case 'n':
								buffer.append(LF);
								// skip
								i += 1;
								previous = '\0';
								break;
							case 'f':
								buffer.append(FF);
								// skip
								i += 1;
								previous = '\0';
								break;
							case 'r':
								buffer.append(CR);
								// skip
								i += 1;
								previous = '\0';
								break;
							case '"':
								buffer.append('"');
								// skip
								i += 1;
								previous = '\0';
								break;
							case '\'':
								buffer.append('\'');
								// skip
								i += 1;
								previous = '\0';
								break;
							case '\\':
								buffer.append('\\');
								// skip
								i += 1;
								previous = '\0';
								break;
							default:
								throw new IllegalArgumentException("Invalid escape sequence in String literal: " + current + next);
						}
					}
					break;
				default:
					buffer.append(current);
					previous = current;
			}
		}

		return new Token("\"" + buffer.toString() + "\"", LITERAL, STRING);
	}

	public Token literalChar(String literal) {
		switch( literal.length() ) {
			case 3: // '.'
				return new Token('\'' + literal + '\'', LITERAL, CHARACTER);
			case 4: // '\.'
				if (literal.charAt(1) != '\\')
					throw new IllegalArgumentException("Invalid character literal sequence: " + literal);

				switch( literal.charAt(2) ) {
					case '0':
						return new Token("'\0'", LITERAL, CHARACTER);
					case '1':
						return new Token("'\1'", LITERAL, CHARACTER);
					case '2':
						return new Token("'\2'", LITERAL, CHARACTER);
					case '3':
						return new Token("'\3'", LITERAL, CHARACTER);
					case '4':
						return new Token("'\4'", LITERAL, CHARACTER);
					case '5':
						return new Token("'\5'", LITERAL, CHARACTER);
					case '6':
						return new Token("'\6'", LITERAL, CHARACTER);
					case '7':
						return new Token("'\7'", LITERAL, CHARACTER);
					case 'b':
						return new Token("'\b'", LITERAL, CHARACTER);
					case 't':
						return new Token("'\t'", LITERAL, CHARACTER);
					case 'n':
						return new Token("'\n'", LITERAL, CHARACTER);
					case 'f':
						return new Token("'\f'", LITERAL, CHARACTER);
					case 'r':
						return new Token("'\r'", LITERAL, CHARACTER);
					case '"':
						return new Token("'\"'", LITERAL, CHARACTER);
					case '\'':
						return new Token("'''", LITERAL, CHARACTER);
					case '\\':
						return new Token("'\\'", LITERAL, CHARACTER);
					default:
						throw new IllegalArgumentException("Invalid escape sequence in character literal: " + literal);
				}
			case 5: // '\0.'
				if (isOctalNumber(literal.substring(2, 3)))
					return new Token('\'' + String.valueOf(unicode(Integer.decode("0" + literal.substring(2, 3)))) + '\'', LITERAL, CHARACTER);
				throw new IllegalArgumentException("Invalid character literal sequence: " + literal);
			case 6: // '\0..'
				if (isOctalNumber(literal.substring(2, 4)))
					return new Token('\'' + String.valueOf(unicode(Integer.decode("0" + literal.substring(2, 4)))) + '\'', LITERAL, CHARACTER);
				throw new IllegalArgumentException("Invalid character literal sequence: " + literal);
			case 8: // ' \ u . . . . '
				return new Token('\'' + String.valueOf(unicodeEscape(literal.substring(2, 6))) + '\'', LITERAL, CHARACTER);
			default:
				throw new IllegalArgumentException("Invalid character literal sequence: " + literal);
		}
	}

	private static char unicode(int offset) {
		return (char) offset;
	}

	private static char unicodeEscape(String escapeLiteral) {
		char first = escapeLiteral.charAt(0), second = escapeLiteral.charAt(1);

		// error if not starting with \ and u
		if (first != '\\' || second != 'u')
			throw new IllegalArgumentException("Invalid unicode escape sequence : " + escapeLiteral);

		return unicode(Integer.decode("0x" + escapeLiteral.substring(2, 6)));
	}

	private static boolean hasFloatSuffix(String s) {
		char last = s.charAt(s.length() - 1);
		return last == 'f' || last == 'F';
	}

	private static boolean hasDoubleSuffix(String s) {
		char last = s.charAt(s.length() - 1);
		return last == 'd' || last == 'D';
	}

	private static boolean hasLongSuffix(String s) {
		char last = s.charAt(s.length() - 1);
		return last == 'L' || last == 'l';
	}

	private static boolean hasSuffix(String s) {
		return hasFloatSuffix(s) || hasDoubleSuffix(s) || hasLongSuffix(s);
	}

	private static boolean isBinaryDigit(char c) {
		return c == '0' || c == '1';
	}

	private static boolean isBinaryDigitOrUnderscore(char c) {
		return c == '_' || isBinaryDigit(c);
	}

	/**
	 * Checks if the given sequence is a valid BinaryIntegerLiteral production as defined by JLS&sect;3.10.1.
	 *
	 * @param s the sequence to process
	 * @return true iff the string is a valid binary literal
	 */
	private static boolean isBinaryNumber(String s) {
		char[] in    = s.toCharArray();
		char   first = in[0], second = in[1];

		// false if first two characters are not 0x or 0X
		if (first != '0' && (second != 'b' || second != 'B'))
			return false;

		// cannot start or end with an underscore
		if (in[2] == '_' || in[in.length - 1] == '_')
			return false;

		for (int i = 3; i < in.length - (hasSuffix(s) ? 2 : 1); i++) {
			if (!isBinaryDigitOrUnderscore(in[i]))
				return false;
		}

		return true;
	}

	/**
	 * Checks if the given sequence consists entirely of binary digits and underscores
	 *
	 * @param s the sequence to process
	 * @return true iff the string consists entirely of binary digits and underscores
	 */
	private static boolean isBinaryValue(String s) {
		char[] in = s.toCharArray();

		// cannot start or end with an underscore
		if (in[0] == '_' || in[in.length - 1] == '_')
			return false;

		for (char c : in) {
			if (!isBinaryDigitOrUnderscore(c))
				return false;
		}

		return true;
	}

	private static boolean isOctalDigit(char c) {
		switch( c ) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
				return true;
			default:
				return false;
		}
	}

	private static boolean isOctalDigitOrUnderscore(char c) {
		return c == '_' || isOctalDigit(c);
	}

	private static boolean isOctalNumber(String s) {
		char[] in = s.toCharArray();

		for (char c : in) {
			if (!isOctalDigitOrUnderscore(c))
				return false;
		}

		return true;
	}

	private static boolean isZeroToThree(char c) {
		switch( c ) {
			case '0':
			case '1':
			case '2':
			case '3':
				return true;
			default:
				return false;
		}
	}


	private static boolean isDigitOrUnderscore(char c) {
		return c == '_' || Character.isDigit(c);
	}

	private static boolean isDecimal(String s) {
		char[] in = s.toCharArray();

		for (char c : in) {
			if (!isDigitOrUnderscore(c))
				return false;
		}

		return true;
	}


	private static boolean isHexDigit(char c) {
		switch( c ) {
			case '0':
			case '1':
			case '2':
			case '3':
			case '4':
			case '5':
			case '6':
			case '7':
			case '8':
			case '9':
			case 'a':
			case 'b':
			case 'c':
			case 'd':
			case 'e':
			case 'f':
			case 'A':
			case 'B':
			case 'C':
			case 'D':
			case 'F':
				return true;
			default:
				return false;
		}
	}

	private static boolean isHexDigitOrUnderscore(char c) {
		return c == '_' || isHexDigit(c);
	}

	/**
	 * Checks if the given sequence is a valid HexDigits production according to JLS&sect;3.10.1.
	 *
	 * @param s the sequence to process
	 * @return true iff the sequence consists entirely of valid hexadecimal digits; false otherwise
	 */
	private static boolean areHexDigits(String s) {
		char[] in = s.toCharArray();

		for (char c : in) {
			if (!isHexDigitOrUnderscore(c))
				return false;
		}

		return true;
	}

	/**
	 * Checks if the given sequence is a valid HexSignificand production according to JLS&sect;3.10.1.
	 *
	 * @param s the sequence to process
	 * @return true iff the sequence is a valid hexadecimal sequence
	 */
	private static boolean isHexadecimal(String s) {
		char[] in    = s.toCharArray();
		char   first = in[0], second = in[1];

		// false if first two characters are not 0x or 0X
		if (first != '0' && (second != 'x' || second != 'X'))
			return false;

		// cannot start or end with an underscore
		if (in[2] == '_' || in[in.length - 1] == '_')
			return false;

		for (int i = 3; i < in.length - 1; i++) {
			if (!isHexDigitOrUnderscore(in[i]))
				return false;
		}

		return true;
	}

	private static boolean isHexadecimalFloat(String s) {
		char[] in    = s.toCharArray();
		char   first = in[0], second = in[1];

		// false if first two characters are not 0x or 0X
		if (first != '0' && (second != 'x' || second != 'X'))
			return false;

		// false if there is no dot
		if (!s.contains("."))
			return false;

		for (int i = 2; i < in.length; i++) {
			char c = in[i];
			if (!isHexDigitOrUnderscore(c) && c != '.')
				return false;
		}

		return true;
	}

}
