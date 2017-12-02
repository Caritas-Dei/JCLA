package jcla.compiler.token;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
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
		List<Token> tokens = new ArrayList<>();
		// create a buffer for the identifier the size of the input.
		// we don't know if the input is a single token or multiple,
		// so we use input.length()
		char[] in = input.toCharArray();

		StringBuilder buffer = new StringBuilder(in.length);

		// iterating a comment
		boolean comment = false;
		// single-line comment
		boolean singleline = false;
		// strings
		boolean string = false;
		// characters
		boolean character = false;
		// escapes
		boolean escaped = false;
		// literals
		boolean literal = false;

		next_char:
		for (int i = 0; i < in.length; ) {
			char current = in[i];
			switch( current ) {
				case '\\': // escapes
					if (!comment) { // if not in a comment
						if (literal) {// if in a literal
							if (!escaped) { // begin escape if not escaped
								escaped = true;
								// don't add backslash to buffer if this is an escape
							} else { // end escape if escaped
								escaped = false;
								// this is a backslash literal
								buffer.append(current);
							}
						} else { // if outside a literal
							// illegal condition
							// TODO IllegalCharacterException
							//throw new IllegalCharacterException("Illegal character: " + toDebugString(current));
							throw new IllegalArgumentException("Illegal character: " + toDebugString(current));
						}
					}

					// go to next char
					i++;
					continue next_char;
				case 'b':
					if (!comment) {
						if (literal) {
							if (escaped) {
								escaped = false;
								buffer.append('\b');
							} else {
								// add to the buffer, nothing special here
								buffer.append(current);
							}
						} else {
							// add to the buffer, nothing special here
							buffer.append(current);
						}
					}

					// go to next char
					i++;
					continue next_char;
				case 't':
					if (!comment) {
						if (literal) {
							if (escaped) {
								escaped = false;
								buffer.append('\t');
							} else {
								// add to the buffer, nothing special here
								buffer.append(current);
							}
						} else {
							// add to the buffer, nothing special here
							buffer.append(current);
						}
					}

					// go to next char
					i++;
					continue next_char;
				case 'n':
					if (!comment) {
						if (literal) {
							if (escaped) {
								escaped = false;
								buffer.append('\n');
							} else {
								// add to the buffer, nothing special here
								buffer.append(current);
							}
						} else {
							// add to the buffer, nothing special here
							buffer.append(current);
						}
					}

					// go to next char
					i++;
					continue next_char;
				case 'f':
					if (!comment) {
						if (literal) {
							if (escaped) {
								escaped = false;
								buffer.append('\f');
							} else {
								// add to the buffer, nothing special here
								buffer.append(current);
							}
						} else {
							// add to the buffer, nothing special here
							buffer.append(current);
						}
					}

					// go to next char
					i++;
					continue next_char;
				case 'r':
					if (!comment) {
						if (literal) {
							if (escaped) {
								escaped = false;
								buffer.append('\r');
							} else {
								// add to the buffer, nothing special here
								buffer.append(current);
							}
						} else {
							// add to the buffer, nothing special here
							buffer.append(current);
						}
					}

					// go to next char
					i++;
					continue next_char;
				case 'u': // unicode escape or ASCII u char
					if (!comment) {
						if (literal) {
							if (!escaped) {
								// add to the buffer, nothing special here
								buffer.append(current);
							} else {
								escaped = false;
								// process unicode escape and add to buffer
								buffer.append(unicode("\\u" + input.substring(i, i + 4)));
								// skip the unicode sequence in the input for next iteration
								//
								// \ u H H H H .
								//   ^         ^
								//   i       i + 5
								//
								i += 5;
								continue next_char;
							}
						} else {
							// add to the buffer, nothing special here
							buffer.append(current);
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '0': // octal escapes
				case '1':
				case '2':
				case '3':
					if (!comment) {
						if (literal) {
							if (escaped) {
								escaped = false;
								char second = in[i + 1], third = in[i + 2];
								if (isOctalDigit(second)) {
									if (isOctalDigit(third)) {
										buffer.append(octal("\\" + current + second + third));
									} else {
										buffer.append(octal("\\" + current + second));
									}
								} else {
									buffer.append(octal("\\" + current));
								}
							} else {
								// add to the buffer, nothing special here
								buffer.append(current);
							}
						} else {
							// add to the buffer, nothing special here
							buffer.append(current);
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '4':
				case '5':
				case '6':
				case '7':
					if (!comment) {
						if (literal) {
							if (escaped) {
								escaped = false;
								char second = in[i + 1];
								if (isOctalDigit(second)) {
									buffer.append(octal("\\" + current + second));
								} else {
									buffer.append(octal("\\" + current));
								}
							} else {
								// add to the buffer, nothing special here
								buffer.append(current);
							}
						} else {
							// add to the buffer, nothing special here
							buffer.append(current);
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '"': // strings
					if (!comment) {
						if (character) {
							if (escaped)
								escaped = false;
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							if (!string) {
								// begin string literal
								string = true;
								literal = true;
								// tokenize the buffer if not empty
								if (buffer.length() > 0) {
									tokens.add(identify(buffer.toString()));
									buffer.delete(0, buffer.length());
								}
							} else {
								if (escaped) {
									escaped = false;
									// this is a literal quotation mark
									buffer.append(current);
								} else {
									// end string literal
									string = false;
									literal = false;
									tokens.add(string(buffer.toString()));
									buffer.delete(0, buffer.length());
								}
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '\'': // characters
					if (!comment) {
						if (string) {
							if (escaped)
								escaped = false;
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							if (!character) {
								// begin character literal
								character = true;
								literal = true;
								// tokenize the buffer if not empty
								if (buffer.length() > 0) {
									tokens.add(identify(buffer.toString()));
									buffer.delete(0, buffer.length());
								}
							} else {
								if (escaped) {
									escaped = false;
									// this is a literal apostrophe
									buffer.append(current);
								} else {
									// end character literal
									character = false;
									literal = false;
									tokens.add(character(buffer.toString()));
									buffer.delete(0, buffer.length());
								}
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '(': // separators
				case ')':
				case '{':
				case '}':
				case '[':
				case ']':
				case ';':
				case ',':
				case '@':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}
							// tokenize this separator
							tokens.add(identify(String.valueOf(current)));
						}
					}

					i++;
					continue next_char;
				case '.': // complex separators
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '.') {
								if (in[i + 2] == '.') {
									tokens.add(DOT_DOT_DOT);
									// go to next char
									i += 3;
									continue next_char;
								} else {
									// just two consecutive dot separators
									tokens.add(DOT);
									tokens.add(DOT);

									i += 2;
									continue next_char;
								}
							} else {
								tokens.add(DOT);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case ':': // complex separator or simple operator
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == ':') {
								tokens.add(COLON_COLON);
								// go to next char
								i += 2;
								continue next_char;
							} else {
								tokens.add(COLON);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '=': // simple and complex operators
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '=') {
								tokens.add(EQUALS);
								// go to next char
								i += 2;
								continue next_char;
							} else {
								tokens.add(ASSIGNMENT);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '+':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '+') {
								tokens.add(INCREMENT);

								// go to next char
								i += 2;
								continue next_char;
							} else if (second == '=') {
								tokens.add(INCREMENT_ASSIGN);

								// go to next char
								i += 2;
								continue next_char;
							} else {
								tokens.add(ADD);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '-':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '-') {
								tokens.add(DECREMENT);

								// go to next char
								i += 2;
								continue next_char;
							} else if (second == '=') {
								tokens.add(DECREMENT_ASSIGN);

								// go to next char
								i += 2;
								continue next_char;
							} else if (second == '>') {
								tokens.add(LAMBDA);

								// go to next char
								i += 2;
								continue next_char;
							} else {
								tokens.add(SUBTRACT);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '*': // multiply or traditional comment indicator
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '=') {
								tokens.add(MULTIPLY_ASSIGN);

								// go to next char
								i += 2;
								continue next_char;
							} else {
								tokens.add(MULTIPLY);
							}
						}
					} else {
						// terminate traditional comment
						if (!singleline && in[i + 1] == '/') {
							comment = false;
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '/': // divide or comment indicator
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							switch( second ) {
								case '/': // single-line (EOL) comment start
									singleline = true;
									comment = true;
									// go to next char
									break;
								case '*': // begin traditional (multi-line) comment start
									comment = true;
									// go to next char
									break;
								case '=': // divide assign token
									tokens.add(DIVIDE_ASSIGN);
									// go to next char
									break;
								default:  // just a divide token
									tokens.add(DIVIDE);
									// go to next char
									i++;
									continue next_char;
							}

							// go to next char
							i += 2;
							continue next_char;
						}
					}


					// go to next char
					i++;
					continue next_char;
				case '&':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '=') {
								tokens.add(AND__ASIGN);

								// go to next char
								i += 2;
								continue next_char;
							} else if (second == '&') {
								tokens.add(AND);

								// go to next char
								i += 2;
								continue next_char;
							} else {
								// just an ampersand
								tokens.add(AMPERSAND);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '|':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '=') {
								tokens.add(OR__ASSIGN);

								// go to next char
								i += 2;
								continue next_char;
							} else if (second == '|') {
								tokens.add(OR);

								// go to next char
								i += 2;
								continue next_char;
							} else {
								tokens.add(PIPE);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '^':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '=') {
								tokens.add(XOR__ASSIGN);

								// go to next char
								i += 2;
								continue next_char;
							} else {
								tokens.add(CARET);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '%':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '=') {
								tokens.add(MODULO__ASSIGN);

								// go to next char
								i += 2;
								continue next_char;
							} else {
								tokens.add(PERCENT_SIGN);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '<':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '<') {
								if (in[i + 2] == '=') {
									tokens.add(LEFT_SHIFT__ASSIGN);
									// go to next char
									i += 3;
									continue next_char;
								} else {
									tokens.add(LEFT_SHIFT);
									// go to next char
									i += 2;
									continue next_char;
								}
							} else if (second == '=') {
								tokens.add(LESS_THAN_OR_EQUAL);
								// go to next char
								i += 2;
								continue next_char;
							} else {
								tokens.add(LESS_THAN);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '>':
					// this character has the greatest possible complexity
					// for lexical analysis because of type contexts
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '>') {
								// check if this is a type context (no parsing or parse errors checked)
								// predicate:
								// last token is:
								//  - an IDENTIFIER or ? symbol
								// second to last token is:
								//  - a < symbol or , symbol or . symbol
								//
								// explanation:
								//  - a type context cannot have literals as type identifiers
								//  - a type context must have one or more type arguments, hence the comma
								//  - a type context must have a valid type which usually is in a package, hence the dot
								//  - a type context must be surrounded by angle brackets
								Token  last         = tokens.get(tokens.size() - 1);
								String secondToLast = tokens.get(tokens.size() - 2).getSymbol();
								// first check if last token is an IDENTIFIER
								if ((last.getTag() == IDENTIFIER || last.getSymbol().equals("?")) && (secondToLast.equals("<") || secondToLast.equals(",") || secondToLast.equals("."))) {
									// offset from the current index into 'in'
									int offset = 0;

									while( in[i + offset] == '>' ) {
										tokens.add(GREATER_THAN);
										offset++;
									}

									// go to next char
									// example:
									// > > > > >   a
									// 0 1 2 3 4 5 6
									// ^         ^
									//i + offset + 1 = i + 1
									//          i + offset + 1 = i + 5
									i += (offset + 1);
									continue next_char;
								} else { // assume not in a type context, check for operators
									char third = in[i + 2];

									if (third == '>') {
										if (in[i + 3] == '=') {
											tokens.add(UNSIGNED_RIGHT_SHIFT__ASSIGN);
											// go to next char
											i += 4;
											continue next_char;
										} else {
											tokens.add(UNSIGNED_RIGHT_SHIFT);
											// go to next char
											i += 3;
											continue next_char;
										}
									} else if (third == '=') {
										tokens.add(RIGHT_SHIFT__ASSIGN);
										// go to next char
										i += 3;
										continue next_char;
									} else {
										tokens.add(RIGHT_SHIFT);
									}
								}
							} else if (second == '=') {
								tokens.add(GREATER_THAN_OR_EQUAL);
								// go to next char
								i += 2;
							} else {
								tokens.add(GREATER_THAN);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '!':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							char second = in[i + 1];

							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}

							if (second == '=') {
								tokens.add(NOT_EQUAL);
								// go to next char
								i += 2;
								continue next_char;
							} else {
								tokens.add(EXCLAMATION);
							}
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '~':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							tokens.add(TILDA);
						}
					}

					// go to next char
					i++;
					continue next_char;
				case '?':
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							tokens.add(QUESTION_MARK);
						}
					}

					// go to next char
					i++;
					continue next_char;
				case CR: // carriage return (\ r)
				case LF: // line feed (\ n)
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						}
					} else {
						if (singleline) {
							singleline = false;
							comment = false;
						}
					}

					// go to next char
					i++;
					continue next_char;
				case FF: // form feed (\ f)
				case HT: // tab (\ t)
				case SP: // space ( )
					if (!comment) {
						if (literal) {
							// add to the buffer, nothing special here
							buffer.append(current);
						} else {
							// tokenize the buffer if not empty
							if (buffer.length() > 0) {
								tokens.add(identify(buffer.toString()));
								buffer.delete(0, buffer.length());
							}
						}
					}

					i++;
					continue next_char;
				default:
					if (!comment) {
						if (literal) {
							if (escaped) {
								throw new IllegalArgumentException("Illegal character for escape: " + toDebugString(current));
							} else {
								buffer.append(current);
							}
						} else {
							buffer.append(current);
						}
					}
					// go to next char
					i++;
			}
		}

		return tokens;
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
			// separators
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
			// operators
			case "=":
				return ASSIGNMENT;
			case ">":
				return GREATER_THAN;
			case "<":
				return LESS_THAN;
			case "!":
				return EXCLAMATION;
			case "~":
				return TILDA;
			case "?":
				return QUESTION_MARK;
			case ":":
				return COLON;
			case "->":
				return LAMBDA;
			case "==":
				return EQUALS;
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
				return AMPERSAND;
			case "|":
				return PIPE;
			case "^":
				return CARET;
			case "%":
				return PERCENT_SIGN;
			case "<<":
				return LEFT_SHIFT;
			case ">>":
				return RIGHT_SHIFT;
			case ">>>":
				return UNSIGNED_RIGHT_SHIFT;
			case "+=":
				return INCREMENT_ASSIGN;
			case "-=":
				return DECREMENT_ASSIGN;
			case "*=":
				return MULTIPLY_ASSIGN;
			case "/=":
				return DIVIDE_ASSIGN;
			case "&=":
				return AND__ASIGN;
			case "|=":
				return OR__ASSIGN;
			case "^=":
				return XOR__ASSIGN;
			case "%=":
				return MODULO__ASSIGN;
			case "<<=":
				return LEFT_SHIFT__ASSIGN;
			case ">>=":
				return RIGHT_SHIFT__ASSIGN;
			case ">>>=":
				return UNSIGNED_RIGHT_SHIFT__ASSIGN;
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
				if (!Character.isDigit(first) && first != '.' && first != '\'' && first != '"')
					return new Token(input, IDENTIFIER);

				return new Token(input, LITERAL, NUMBER);
		}
	}

	public Token string(String literal) {
		// -all escapes have been pre-processed by analyzer before-hand
		// -all conditions must have been met before-hand
		// therefore: create token directly
		return new Token(literal, LITERAL, STRING);
	}

	public Token character(String literal) {
		// -all escapes have been pre-processed by analyzer before-hand
		// -some conditions were met before-hand
		// therefore: check:
		//      - not empty
		//      - correct length
		switch( literal.length() ) {
			case 0: // empty
				// illegal condition
				// TODO EmptyCharacterLiteralException
				throw new IllegalArgumentException("Character literal cannot be empty");
			case 1: // exactly one char (as usual)
				return new Token(literal, LITERAL, CHARACTER);
			default:
				// illegal condition (too many character
				// TODO CharacterLiteralException
				throw new IllegalArgumentException("Character literal can only have one character: " + toDebugString(literal));
		}
	}

	private static char unicode(String literal) {
		if (literal.length() != 6 || !literal.substring(0, 2).equalsIgnoreCase("\\u"))
			throw new IllegalArgumentException("Invalid unicode escape sequence: " + toDebugString(literal));

		return (char) Integer.decode("0x" + literal.substring(2)).intValue();
	}

	private static char octal(String literal) {
		if (literal.length() > 4 || !isOctalNumber(literal.substring(1, 4)))
			throw new IllegalArgumentException("Invalid octal escape sequence: " + toDebugString(literal));

		return (char) Integer.decode("0" + literal.substring(1, 4)).intValue();
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


	private static String toDebugString(char c) {
		switch( c ) {
			case '\b':
				return "\\b";
			case '\t':
				return "\\t";
			case '\n':
				return "\\n";
			case '\f':
				return "\\f";
			case '\r':
				return "\\r";
			case '\u0000':
				return "NUL";
			case '\u0001':
				return "SOH";
			case '\u0002':
				return "STX";
			case '\u0003':
				return "ETX";
			case '\u0004':
				return "EOT";
			case '\u0005':
				return "ENQ";
			case '\u0006':
				return "ACK";
			case '\u0007':
				return "BEL";
			case '\u000B':
				return "VT";
			case '\u000E':
				return "SO";
			case '\u000F':
				return "SI";
			case '\u0010':
				return "DLE";
			case '\u0011':
				return "DC1";
			case '\u0012':
				return "DC2";
			case '\u0013':
				return "DC3";
			case '\u0014':
				return "DC4";
			case '\u0015':
				return "NAK";
			case '\u0016':
				return "SYN";
			case '\u0017':
				return "ETB";
			case '\u0018':
				return "CAN";
			case '\u0019':
				return "EM";
			case '\u001A':
				return "SUB";
			case '\u001B':
				return "ESC";
			case '\u001C':
				return "FS";
			case '\u001D':
				return "GS";
			case '\u001E':
				return "RS";
			case '\u001F':
				return "US";
			case '\u0020':
				return "SP";
			case '\u007f':
				return "DEL";
			default:
				return isPrintable(c) ? String.valueOf(c) : "\\u" + Integer.toHexString(c);
		}
	}

	private static String toDebugString(String s) {
		StringBuilder buffer = new StringBuilder(s.length());

		for (char c : s.toCharArray()) {
			buffer.append(toDebugString(c));
		}

		return buffer.toString();
	}

	private static boolean isPrintable(char c) {
		Character.UnicodeBlock block = Character.UnicodeBlock.of(c);
		return (!Character.isISOControl(c)) &&
				c != KeyEvent.CHAR_UNDEFINED &&
				block != null &&
				block != Character.UnicodeBlock.SPECIALS;
	}


}
