package jcla.compiler.token.hint;

/**
 * @author link
 */
public final class Hint {

	private final String name;

	public Hint(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
