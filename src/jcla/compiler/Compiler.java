package jcla.compiler;

import jcla.compiler.analyzer.LexemeAnalyzer;
import jcla.lang.production.java.token.Token;

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
