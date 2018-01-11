package jcla.compiler.lang;

import jcla.compiler.lang.grammar.Grammar;

import java.util.List;

/**
 * @author link
 */
public class Document {

	private final List<Grammar> lines;

	public Document(List<Grammar> lines) {
		this.lines = lines;
	}

}
