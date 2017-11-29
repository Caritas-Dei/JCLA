package jcla.compiler.lang;

import jcla.compiler.construct.Construct;

import java.util.List;

/**
 * @author link
 */
public class Document {

	private final List<Construct> lines;

	public Document(List<Construct> lines) {
		this.lines = lines;
	}

}
