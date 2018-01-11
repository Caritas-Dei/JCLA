package jcla.compiler;

import jcla.compiler.token.Token;
import jcla.compiler.token.analyzer.LexicalAnalyzer;

import java.util.List;

/**
 * @author link
 */
public class Compiler {

	private final LexicalAnalyzer analyzer = new LexicalAnalyzer();

	public void compile(String code) {
		List<Token> tokens = analyzer.analyze(code);

	}

}
