package jcla.compiler;

import jcla.compiler.lang.production.token.Token;
import jcla.compiler.lang.production.token.analyzer.LexemeAnalyzer;

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
