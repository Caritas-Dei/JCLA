package jcla.compiler;

import jcla.compiler.construct.Construct;
import jcla.compiler.token.analyzer.LexicalAnalyzer;
import jcla.compiler.token.Token;

import java.util.List;

/**
 * @author link
 */
public class Compiler {

	private final LexicalAnalyzer analyzer = new LexicalAnalyzer();

	public List<Construct> compile(String code) {
		List<Token> tokens = analyzer.analyze(code);

		return null;
	}

}
