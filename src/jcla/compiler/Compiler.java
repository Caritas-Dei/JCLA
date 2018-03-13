package jcla.compiler;

import jcla.compiler.token.Token;
import jcla.compiler.token.analyzer.LexemeAnalyzer;

import java.util.List;

/**
 * @author link
 */
public class Compiler {

	private final LexemeAnalyzer analyzer = new LexemeAnalyzer();

	public void compile(String code) {
		List<Token> tokens = analyzer.analyze(code);

	}

}
